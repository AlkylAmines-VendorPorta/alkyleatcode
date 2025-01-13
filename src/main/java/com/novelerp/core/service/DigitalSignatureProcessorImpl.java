package com.novelerp.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.appbase.master.service.impl.SignatureServiceImpl;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.DigitalRDNInfo;
import com.novelerp.core.dto.DigitalSignatureInfo;
import com.novelerp.core.service.impl.MD5Security;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class DigitalSignatureProcessorImpl implements DigitialSignatureProcessor{

	private Logger log = LoggerFactory.getLogger(DigitalSignatureProcessorImpl.class);
	
	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService; 
	
	@Override
	public DigitalSignatureInfo getDigitalSignatureInfo(File file)  {
		FileInputStream content = null;
		CMSSignedData signedData =null;
		SignerInformationStore signers =null;
		try{
			content = new FileInputStream(file);
			int sizecontent =  (int) file.length();
			byte[] buffer = new byte[sizecontent];
			content.read(buffer);
			CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
			byte[] signedContent = (byte[]) cpb.getContent();
			signedData = new CMSSignedData(signedContent);

			Security.addProvider(new BouncyCastleProvider());
			signers = signedData.getSignerInfos();
			return parseStore(signers, signedData);
		}catch (Exception e) {
			log.error("ERROR", e);
		}finally {
			try{
				content.close();
			}catch (Exception e) {
				log.error("ERROR", e);
			}
		}
		return null;
	}
	
	@Override
	public DigitalSignatureInfo getDigitalSignatureInfo(File file,String isEncrypted)  {
		FileInputStream content = null;
		CMSSignedData signedData =null;
		SignerInformationStore signers =null;
		BPartnerDto partner=contextService.getPartner();
		try{
			content = new FileInputStream(file);
			int sizecontent =  (int) file.length();
			byte[] buffer = new byte[sizecontent];
			content.read(buffer);
			if("Y".equals(isEncrypted)){
				buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
			}
			CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
			byte[] signedContent = (byte[]) cpb.getContent();
			signedData = new CMSSignedData(signedContent);

			Security.addProvider(new BouncyCastleProvider());
			signers = signedData.getSignerInfos();
			return parseStore(signers, signedData);
		}catch (Exception e) {
			log.error("ERROR", e);
		}finally {
			try{
				content.close();
			}catch (Exception e) {
				log.error("ERROR", e);
			}
		}
		return null;
	}
	
	@Override
	public DigitalSignatureInfo getDigitalSignatureInfo(byte[] buffer,String isEncrypted)  {
		CMSSignedData signedData =null;
		SignerInformationStore signers =null;
		BPartnerDto partner=contextService.getPartner();
		try{
			if("Y".equals(isEncrypted)){
				buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
			}
			CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
			byte[] signedContent = (byte[]) cpb.getContent();
			signedData = new CMSSignedData(signedContent);

			Security.addProvider(new BouncyCastleProvider());
			signers = signedData.getSignerInfos();
			return parseStore(signers, signedData);
		}catch (Exception e) {
			log.error("ERROR", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private DigitalSignatureInfo parseStore(SignerInformationStore signers,CMSSignedData signedData) throws Exception{
		if(signedData==null || signers ==null){
			return null;
		}
		Store<X509CertificateHolder> x509Store = signedData.getCertificates();
		Iterator<SignerInformation> signerIterator = signers.iterator();
		Collection<X509CertificateHolder> holders =null;
		while(signerIterator.hasNext()){
			SignerInformation signer =  signerIterator.next();
			holders = x509Store.getMatches(signer.getSID());
		}
		return parseHolders(holders);
	}
	
	private DigitalSignatureInfo parseHolders(Collection<X509CertificateHolder> holders) throws Exception{
		Iterator<X509CertificateHolder> holderIterator = holders.iterator();
		DigitalSignatureInfo signatureInfo = new DigitalSignatureInfo();
		while(holderIterator.hasNext()){
			X509CertificateHolder holder =  holderIterator.next();
/*			Extensions extentions = holder.getExtensions();
*/
			X500Name issuer= holder.getIssuer();
/*			parseExtenstions(extentions);*/
			X500Name subject = holder.getSubject();
			
			signatureInfo.setNotBefore(holder.getNotBefore());
			signatureInfo.setNotAfter(holder.getNotAfter());
			
			DigitalRDNInfo issuerInfo = getRDNInfo(issuer);
			DigitalRDNInfo subjectInfo = getRDNInfo(subject);
			boolean validToday = holder.isValidOn(new Date());
			
			signatureInfo.setIssuer(issuerInfo);
			signatureInfo.setSubject(subjectInfo);
			signatureInfo.setValidToday(validToday);
			X509Certificate cert=new JcaX509CertificateConverter().setProvider("BC").getCertificate(holder);
			signatureInfo.setX509cert(cert);
		}
		return signatureInfo;
		
	}
	
	private DigitalRDNInfo getRDNInfo(X500Name x500Name) throws Exception{
		String subjectInfo = x500Name.toString().replaceAll("=", "\":\"");
		subjectInfo=subjectInfo.replaceAll("\\\\,", "#%%#");
		subjectInfo=	subjectInfo.replaceAll(",", "\",\"");
		subjectInfo=subjectInfo.replace("\\","\\\\");
		subjectInfo=subjectInfo.replace("#%%#","\\\\,");
		StringBuilder x500Info = new StringBuilder();
		x500Info.append("{\"")
		 	.append(subjectInfo)
		 		.append("\"}");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(x500Info.toString().getBytes(), DigitalRDNInfo.class);
	}
	
		@Override
		public List<DigitalSignatureInfo> getDigitalSignatureInfoList(byte[] buffer,String isEncrypted) {
			/*FileInputStream content = null;*/
			CMSSignedData signedData =null;
			SignerInformationStore signers =null;
			BPartnerDto partner=contextService.getPartner();
			try{
				if("Y".equals(isEncrypted)){
					buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
				}
				/*content = new FileInputStream(file);
				int sizecontent =  (int) file.length();
				byte[] buffer = new byte[sizecontent];
				content.read(buffer);*/
				CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
				byte[] signedContent = (byte[]) cpb.getContent();
				signedData = new CMSSignedData(signedContent);
	            Security.addProvider(new BouncyCastleProvider());
				signers = signedData.getSignerInfos();
				return parseStoreForCoSigner(signers, signedData);
			}catch (Exception e) {
				log.error("ERROR", e);
			}finally {
				try{
					/*content.close();*/
				}catch (Exception e) {
					log.error("ERROR", e);
				}
			}
			return null;
		}
		
	@Override
	public List<DigitalSignatureInfo> getDigitalSignatureInfoList(File file) {
		FileInputStream content = null;
		CMSSignedData signedData =null;
		SignerInformationStore signers =null;
		try{
			content = new FileInputStream(file);
			int sizecontent =  (int) file.length();
			byte[] buffer = new byte[sizecontent];
			content.read(buffer);
			CMSProcessableByteArray cpb = new CMSProcessableByteArray(buffer);
			byte[] signedContent = (byte[]) cpb.getContent();
			signedData = new CMSSignedData(signedContent);
            Security.addProvider(new BouncyCastleProvider());
			signers = signedData.getSignerInfos();
			return parseStoreForCoSigner(signers, signedData);
		}catch (Exception e) {
			log.error("ERROR", e);
		}finally {
			try{
				content.close();
			}catch (Exception e) {
				log.error("ERROR", e);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<DigitalSignatureInfo> parseStoreForCoSigner(SignerInformationStore signers,CMSSignedData signedData) throws Exception{
		if(signedData==null || signers ==null){
			return null;
		}
		Store<X509CertificateHolder> x509Store = signedData.getCertificates();
		Iterator<SignerInformation> signerIterator = signers.iterator();
		Collection<X509CertificateHolder> holders =null;
		List<X509CertificateHolder> holderList=new ArrayList<>();
		while(signerIterator.hasNext()){
			SignerInformation signer =  signerIterator.next();
			holders = x509Store.getMatches(signer.getSID());
			X509CertificateHolder cert = (X509CertificateHolder)holders.iterator().next();
			holderList.add(cert);
		}
		return parseHoldersForCoSign(holderList);
	}
	private List<DigitalSignatureInfo> parseHoldersForCoSign(List<X509CertificateHolder> holders) throws Exception{
		Iterator<X509CertificateHolder> holderIterator = holders.iterator();
		List<DigitalSignatureInfo> signaturesInfo=new ArrayList<>();
		while(holderIterator.hasNext()){
			DigitalSignatureInfo signatureInfo = new DigitalSignatureInfo();
			X509CertificateHolder holder =  holderIterator.next();
			X500Name issuer= holder.getIssuer();
			X500Name subject = holder.getSubject();
			
			signatureInfo.setNotBefore(holder.getNotBefore());
			signatureInfo.setNotAfter(holder.getNotAfter());
			
			DigitalRDNInfo issuerInfo = getRDNInfo(issuer);
			DigitalRDNInfo subjectInfo = getRDNInfo(subject);
			boolean validToday = holder.isValidOn(new Date());
			
			signatureInfo.setIssuer(issuerInfo);
			signatureInfo.setSubject(subjectInfo);
			signatureInfo.setValidToday(validToday);
			X509Certificate cert=new JcaX509CertificateConverter().setProvider("BC").getCertificate(holder);
			signatureInfo.setX509cert(cert);
			signaturesInfo.add(signatureInfo);
		}
		return signaturesInfo;
	}
    public static void main(String[] args) throws Exception {
		
    	/*testSignedData();*/
    	SignatureServiceImpl signatureService=new SignatureServiceImpl();
    	File file = new File("D:\\DTC_1543991611896.pdf.sig");
		List <DigitalSignatureInfo> signers=new DigitalSignatureProcessorImpl().getDigitalSignatureInfoList(file);
		UserDetailsDto ud=new UserDetailsDto();
		ud.setFirstName("Narendra");
		ud.setMiddleName("Singh");
		ud.setLastName("Ranawat");
		for(DigitalSignatureInfo signer:signers){
			/*signatureService.validateSignature(signer, ud);*/
		}
	}
    
    public static void testSignedData() throws Exception{
    	String s = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEE1YwMTg5NDAxXkJDVFBHNDkzNlAAAAAAAACggDCCBt8wggXHoAMCAQICBiUW1CyB/DANBgkqhkiG9w0BAQsFADCB3TELMAkGA1UEBhMCSU4xJjAkBgNVBAoTHVZlcmFzeXMgVGVjaG5vbG9naWVzIFB2dCBMdGQuMR0wGwYDVQQLExRDZXJ0aWZ5aW5nIEF1dGhvcml0eTEPMA0GA1UEERMGNDAwMDI1MRQwEgYDVQQIEwtNYWhhcmFzaHRyYTESMBAGA1UECRMJVi5TLiBNYXJnMTIwMAYDVQQzEylPZmZpY2UgTm8uIDIxLCAybmQgRmxvb3IsIEJoYXZuYSBCdWlsZGluZzEYMBYGA1UEAxMPVmVyYXN5cyBDQSAyMDE0MB4XDTE4MDkwNzEwMjc1MFoXDTIxMDkwNjA0NTIyNlowggHCMSgwJgYJKoZIhvcNAQkBFhlzdWphdGFkZXNhaUBtYWhhZGlzY29tLmluMQswCQYDVQQGEwJJTjEUMBIGA1UECBMLTWFoYXJhc2h0cmExLTArBgNVBAkTJFBSQUtBU0hHQUQsQU5BTlQgS0FORUtBUiBST0FELEJBTkRSQTFDMEEGA1UEChM6TUFIQVJBU0hUUkEgU1RBVEUgRUxFQ1RSSUNJVFkgRElTVFJJQlVUSU9OIENPTVBBTlkgTElNSVRFRDFJMEcGA1UEBRNANzhkMTJkNjEwNjM4NGM5ZWNkOGFkMzM0OWRhOTdjYWE1NTQ2MDQzNWE0OWUxMjdmOTRkMmRhOTMwMjQ1ODJhYjFIMEYGA1UEAxM/RFMgTUFIQVJBU0hUUkEgU1RBVEUgRUxFQ1RSSUNJVFkgRElTVFJJQlVUSU9OIENPTVBBTlkgTElNSVRFRCAxMUkwRwYDVQQUE0AzNTNlMTU0N2VmZjk4NTE4ZTJiNWFlZTA3YWQyNjgwZDZkYjRmMDFjMGMwZTM1MTc0MWU5YjhiZTNiNGU2YjRhMR8wHQYDVQQLExZJTkZPUk1BVElPTiBURUNITk9MT0dZMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh3luykNvdoyi+qYgZUjOFCU0LYmRMpVXvubxcTACG9FobSOHoPjUlwRs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";
    	
    	byte[] signedContent = Base64.decode(s.getBytes());
    	CMSSignedData signedData = new CMSSignedData(signedContent);

		Security.addProvider(new BouncyCastleProvider());
	    SignerInformationStore	signers = signedData.getSignerInfos();
	    DigitalSignatureInfo d = new DigitalSignatureProcessorImpl().parseStore(signers, signedData);
    }
}
