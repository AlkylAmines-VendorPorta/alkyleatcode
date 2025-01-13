/*package com.novelerp.core.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.novelerp.core.dto.FileVerificationDto;
import com.novelerp.core.service.FileVerificationService;

@Service
public class FileVerificationServiceImpl implements FileVerificationService {

	private static final Logger LOG = LoggerFactory.getLogger(FileVerificationServiceImpl.class);
	
	
	public FileVerificationDto verifyFile(File file) {
		try {
			return verifySignedFile(file);
		} catch (Exception ex) {
			return new FileVerificationDto(false);
		}
	}

	public FileVerificationDto verifyFile(File file, long vendorid) {
		try {
			return verifySignedFile(file, vendorid);
		} catch (Exception ex) {
			return new FileVerificationDto(false);
		}
	}


	private FileVerificationDto verifySignedFile(File f)
			throws CertificateExpiredException, CertificateNotYetValidException, IOException, CMSException,
			NoSuchAlgorithmException, NoSuchProviderException, CertStoreException {

		FileVerificationDto fileVerificationDto = new FileVerificationDto();
		List<String> message = new ArrayList<>();
		boolean verifySignedFile = true;

		FileInputStream content = new FileInputStream(f);;
		int sizecontent =  (int) f.length();
		byte[] buffer = new byte[sizecontent];
		content.read(buffer);

		CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
		byte[] signedContent = (byte[]) cpb.getContent();
		CMSSignedData s = new CMSSignedData(signedContent);

		CertStore certs = null;
		Security.addProvider(new BouncyCastleProvider());
		certs = s.getCertificatesAndCRLs("Collection", "BC");

		SignerInformationStore signers = s.getSignerInfos();

		Collection c = signers.getSigners();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			SignerInformation signer = (SignerInformation) it.next();

			Collection certCollection = certs.getCertificates(signer.getSID());
			Iterator certIt = certCollection.iterator();
			X509Certificate cert = (X509Certificate) certIt.next();

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

			String dateInString = "20-Mar-2014";
			Date dateCheck1 = null;
			try {
				dateCheck1 = formatter.parse(dateInString);
				LOG.info("check dt = " + dateCheck1);
			} catch (Exception de) {
				de.printStackTrace();
			}

			Date startdate = null;
			startdate = cert.getNotBefore();
			LOG.info("start date = " + startdate);

			String certifi = cert.toString();
			LOG.info(certifi);

			char class3 = certifi.charAt(certifi.indexOf("2.16.356.100.2.") + 15);
			LOG.info("Class = " + class3);

			String companyName = cert.getIssuerDN().toString();
			LOG.info("company name = " + companyName);
			boolean safeScrypt = companyName.toString().indexOf("SafeScrypt") != -1;
			boolean nCodeSolutions = companyName.toString().indexOf("(n)Code Solutions") != -1;
			boolean eMudhra = companyName.toString().indexOf("e-Mudhra") != -1;
			boolean tCS = companyName.toString().indexOf("TCS") != -1;

			if (startdate != null) {
				if (class3 == '3') {
					if (startdate.before(dateCheck1)) {
						if ((tCS) || (safeScrypt) || (nCodeSolutions) || (eMudhra)) {
							LOG.info("Class " + class3
									+ " of tcs or safeScrypt or nCodeSolutions or eMudhra before 20-Mar-2014 company name : "
									+ companyName.toString());
						} else {
							String strmessage = " Class 3 Digital Certificate should valid by CA only.";
							LOG.info("Before date Class 3 Digital Certificate should valid by CA only.");
							message.add(strmessage);
							verifySignedFile = false;
						}
					} else if (startdate.after(dateCheck1)) {
						if ((safeScrypt) || (nCodeSolutions) || (eMudhra)) {
							LOG.info("Class " + class3
									+ " of safeScrypt or nCodeSolutions or eMudhra after 20-Mar-2014 company name : "
									+ companyName.toString());
						} else {
							String strmessage = "Class 3 Digital Certificate should valid by CA only.";
							LOG.info("After date Class 3 Digital Certificate should valid by CA only.");
							message.add(strmessage);
							verifySignedFile = false;
						}
					} else {
						String strmessage = "Digital Certificate should valid by CA only.";
						LOG.info("Digital Certificate should valid by CA only.");
						message.add(strmessage);
						verifySignedFile = false;
					}

				} *//**
					 * else if (class3 == '2') { if
					 * (startdate.before(dateCheck1)) { if (tCS) {
					 * LOG.info("Class " + class3 + " of tcs before
					 * 20-Mar-2014 company name : " + companyName.toString()); }
					 * else { String strmessage = "Class 2 Digital Certificate
					 * should valid by CA only."; LOG.info("Before
					 * date Class 2 Digital Certificate should valid by CA
					 * only."); message.add(strmessage); verifySignedFile =
					 * false; } } else { String strmessage = "Class 2 document
					 * is tampered."; LOG.info("Class 2 Digital
					 * Certificate should valid by CA only.");
					 * message.add(strmessage);
					 * 
					 * verifySignedFile = false; }
					 * 
					 * }
					 *//*

			} else {
				String strmessage = "The document is tampered.";

				message.add(strmessage);
				verifySignedFile = false;
			}

			if (signer.verify(cert, "BC")) {
				AttributeTable attr = signer.getSignedAttributes();
				if (attr == null)
					continue;
				Attribute t = attr.get(CMSAttributes.signingTime);
				if (t == null)
					continue;
				Time time = Time.getInstance(t.getAttrValues().getObjectAt(0));
				TimeZone tz = TimeZone.getTimeZone("GMT");
				DateFormat dfGMT = DateFormat.getDateTimeInstance(2, 2);
				dfGMT.setTimeZone(tz);
				String strmessage = "Name: " + getCN(cert.getSubjectDN().getName()) + "," + getRegisterationCode(cert.getSubjectDN().getName()) + " ==> Signed On: " + dfGMT.format(time.getDate());
				LOG.info("-212121212121--->" + strmessage);

				message.add(strmessage);
				LOG.info("---->" + strmessage);
				cert.checkValidity(time.getDate());
			} else {
				content.close();
				verifySignedFile = false;
			}
		}
		content.close();

		fileVerificationDto.setMessage(message);
		fileVerificationDto.setVerifySignedFile(verifySignedFile);
		return fileVerificationDto;
	}
	
	
	
	
	private FileVerificationDto verifySignedFile(File f, long pVendorid)throws FileNotFoundException, IOException, NoSuchAlgorithmException, CMSException, NoSuchProviderException,
			CertStoreException, CertificateNotYetValidException, CertificateExpiredException {
		
		FileVerificationDto fileVerificationDto = new FileVerificationDto();
		List<String> message = new ArrayList<>();
		boolean verifySignedFile = true;
		
		FileInputStream content = new FileInputStream(f);;
		int sizecontent = 0;
		sizecontent = (int) f.length();

		byte[] buffer = new byte[sizecontent];
		content.read(buffer);

		CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
		byte[] signedContent = (byte[]) cpb.getContent();
		CMSSignedData s = new CMSSignedData(signedContent);

		CertStore certs = null;
		Security.addProvider(new BouncyCastleProvider());
		certs = s.getCertificatesAndCRLs("Collection", "BC");

		SignerInformationStore signers = s.getSignerInfos();
		boolean isvendorvalid = false;
		boolean isuservalid = false;

		ArrayList rec = new ArrayList();
		String vendordiginame = null;
		String orgname = null;

		Collection c = signers.getSigners();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			SignerInformation signer = (SignerInformation) it.next();

			Collection certCollection = certs.getCertificates(signer.getSID());
			Iterator certIt = certCollection.iterator();
			X509Certificate cert = (X509Certificate) certIt.next();

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			LOG.info("Vendor id is:-- = " + pVendorid);

			String dateInString = "20-Mar-2014";
			Date dateCheck1 = null;
			try {
				dateCheck1 = formatter.parse(dateInString);
				LOG.info("check dt = " + dateCheck1);
			} catch (Exception de) {
				de.printStackTrace();
			}

			Date startdate = null;
			startdate = cert.getNotBefore();
			LOG.info("start date = " + startdate);

			String certifi = cert.toString();
			LOG.info(certifi);

			char class3 = certifi.charAt(certifi.indexOf("2.16.356.100.2.") + 15);

			String companyName = cert.getIssuerDN().toString();
			LOG.info("company name = " + companyName);
			LOG.info("Name = " + cert.getSubjectDN().getName());
			boolean safeScrypt = companyName.toString().indexOf("SafeScrypt") != -1;
			boolean nCodeSolutions = companyName.toString().indexOf("(n)Code Solutions") != -1;
			boolean eMudhra = companyName.toString().indexOf("e-Mudhra") != -1;
			boolean tCS = companyName.toString().indexOf("TCS") != -1;

			if (startdate != null) {
				if (class3 == '3') {
					if (startdate.before(dateCheck1)) {
						if ((tCS) || (safeScrypt) || (nCodeSolutions) || (eMudhra)) {
							LOG.info("Class " + class3
									+ " of tcs or safeScrypt or nCodeSolutions or eMudhra before 20-Mar-2014 company name : "
									+ companyName.toString());
						} else {
							String strmessage = " Class 3 Digital Certificate should valid by CA only.";
							LOG.info("Before date Class 3 Digital Certificate should valid by CA only.");
							message.add(strmessage);
							verifySignedFile =  false;
						}
					} else if (startdate.after(dateCheck1)) {
						if ((safeScrypt) || (nCodeSolutions) || (eMudhra)) {
							LOG.info("Class " + class3
									+ " of safeScrypt or nCodeSolutions or eMudhra after 20-Mar-2014 company name : "
									+ companyName.toString());
						} else {
							String strmessage = "Class 3 Digital Certificate should valid by CA only.";
							LOG.info("After date Class 3 Digital Certificate should valid by CA only.");
							message.add(strmessage);
							verifySignedFile =  false;
						}
					} else {
						String strmessage = "Digital Certificate should valid by CA only.";
						LOG.info("Digital Certificate should valid by CA only.");
						message.add(strmessage);
						verifySignedFile =  false;
					}

				} else if (class3 == '2') {
					if (startdate.before(dateCheck1)) {
						if (tCS) {
							LOG.info("Class " + class3 + " of tcs before 20-Mar-2014 company name : "
									+ companyName.toString());
						} else {
							String strmessage = "Class 2 Digital Certificate should valid by CA only.";
							LOG.info("Before date Class 2 Digital Certificate should valid by CA only.");
							message.add(strmessage);
							verifySignedFile =  false;
						}
					} else {
						String strmessage = "Class 2 document is tampered.";
						LOG.info("Class 2 Digital Certificate should valid by CA only.");
						message.add(strmessage);

						verifySignedFile =  false;
					}

				}

			} else {
				String strmessage = "The document is tampered.";
				message.add(strmessage);

				verifySignedFile =  false;
			}

			if (signer.verify(cert, "BC")) {
				AttributeTable attr = signer.getSignedAttributes();

				if (attr == null)
					continue;
				Attribute t = attr.get(CMSAttributes.signingTime);

				if (t == null)
					continue;
				LOG.info("Name = " + cert.getSubjectDN().getName());

				String lUserFullName = getCN(cert.getSubjectDN().getName());
				*//**if (checkUser(lUserFullName)) {
					try {
						String certorgname = getRegisterationCode(cert.getSubjectDN().getName());
						System.out.print("Certificate owner name : " + certorgname);

						isvendorvalid = true;
					} catch (Exception e) {
						LOG.info("Vendor Level  " + e);
					}

				} else {
					String strmessage = "The document is tampered.";
					message.add(strmessage);
					verifySignedFile =  false;
				}*//*
				Time time = Time.getInstance(t.getAttrValues().getObjectAt(0));
				TimeZone tz = TimeZone.getTimeZone("GMT");
				DateFormat dfGMT = DateFormat.getDateTimeInstance(2, 2);
				dfGMT.setTimeZone(tz);
				String strmessage = "Name: " + getCN(cert.getSubjectDN().getName()) + "," + getRegisterationCode(cert.getSubjectDN().getName()) + " ==> Signed On: " + dfGMT.format(time.getDate());
				LOG.info("str msg = " + strmessage);
				message.add(strmessage);
				cert.checkValidity(time.getDate());
			} else {
				content.close();

			}
		}
		
		
		fileVerificationDto.setMessage(message);
		fileVerificationDto.setVerifySignedFile(verifySignedFile);
		return fileVerificationDto;
	}
	
private String getCN(String signature) {
		String[] strParams = signature.split(",");
		for (int i = 0; i < strParams.length; i++) {
			if (strParams[i].startsWith("CN")) {
				return strParams[i].substring(3);
			}
		}
		return "";
	}

	private String getRegisterationCode(String signature) {
		String str = signature;

		String[] strParams = signature.split(",");
		LOG.info("Str para = " + strParams.length);
		for (int i = 0; i < strParams.length; i++) {
			LOG.info("Param = " + strParams[i]);
			if (strParams[i].startsWith("O=")) {
				return strParams[i].substring(2);
			}
		}
		return "";
	}

	public static void main(String args[]) throws Exception {
		File file = new File("C:\\Users\\goreak-cont\\Downloads\\output.pdf");
		FileVerificationServiceImpl ver = new FileVerificationServiceImpl();
		FileVerificationDto result = ver.verifyFile(file);
		System.out.println(result.isVerifySignedFile());

	}

}
*/