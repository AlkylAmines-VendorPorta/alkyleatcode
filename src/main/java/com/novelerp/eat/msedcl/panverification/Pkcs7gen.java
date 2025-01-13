package com.novelerp.eat.msedcl.panverification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.core.util.AppPropertyUtil;


@Component
public class Pkcs7gen {
	
	@Autowired
	private AppPropertyUtil propertyUtil;

	/*@SuppressWarnings("unchecked")
	public byte[] generateSignature(String password, String panno) throws Exception {

		char[] pwd = password.toCharArray();
		String userId = propertyUtil.getProperty("eat.pan.verification.userId");
		
		// byte[] dataToSign= "V0180901^ACLPC3993R".getBytes();
		String data = userId + "^" + panno;
		byte[] dataToSign = data.getBytes();

		KeyStore keystore = KeyStore.getInstance("jks");
		InputStream input = new FileInputStream("C:\\Users\\HP\\Desktop\\PAN verification\\oupt.jks");
		try {
			keystore.load(input, pwd);
		} catch (IOException e) {
		}
		Enumeration e = keystore.aliases();
		String alias = "";
		if (e != null) {
			while (e.hasMoreElements()) {
				String n = (String) e.nextElement();
				if (keystore.isKeyEntry(n)) {
					alias = n;
				}
			}
		}
		PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, pwd);
		X509Certificate myPubCert = (X509Certificate) keystore.getCertificate(alias);

		CMSSignedDataGenerator sgen = new CMSSignedDataGenerator();
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		sgen.addSigner(privateKey, myPubCert, CMSSignedDataGenerator.DIGEST_SHA1);
		sgen.addSignerInfoGenerator(new SignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build()
				, arg1));
		Certificate[] certChain = keystore.getCertificateChain(alias);
		ArrayList certList = new ArrayList();
		for (int i = 0; i < certChain.length; i++) {
			certList.add(certChain[i]);
		}
		sgen.addCertificatesAndCRLs(CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC"));
		CMSSignedData csd = sgen.generate(new CMSProcessableByteArray(dataToSign), true, "BC");
		byte[] signedData = csd.getEncoded();
		byte[] signedData64 = Base64.encode(signedData);
		return signedData64;
	}*/
	
	public byte[] generateP7B(String userId, String password, String panno) {
		try {
			char[] pwd = password.toCharArray();
			//String userId = propertyUtil.getProperty("eat.pan.verification.userId");
			Security.addProvider(new BouncyCastleProvider());		
			// byte[] dataToSign= "V0180901^ACLPC3993R".getBytes();
			String data = userId + "^" + panno;
			byte[] dataToSign = data.getBytes();

			KeyStore keystore = KeyStore.getInstance("jks");
			InputStream input = new FileInputStream("C:\\Users\\HP\\Desktop\\PAN verification\\oupt.jks");
			try {
				keystore.load(input, pwd);
			} catch (IOException e) {
			}
			Enumeration e = keystore.aliases();
			String alias = "";
			if (e != null) {
				while (e.hasMoreElements()) {
					String n = (String) e.nextElement();
					if (keystore.isKeyEntry(n)) {
						alias = n;
					}
				}
			}
			PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, pwd);
			X509Certificate myPubCert = (X509Certificate) keystore.getCertificate(alias);

			
			List<X509CertificateHolder> certChain = new ArrayList<X509CertificateHolder>();
			X509CertificateHolder holder = new X509CertificateHolder(myPubCert.getEncoded());
			certChain.add(holder);

			Store certs = new JcaCertStore(certChain);

			CMSSignedDataGenerator cmsSignedDataGenerator = new CMSSignedDataGenerator();
			ContentSigner sha1Signer = new JcaContentSignerBuilder( "SHA1withRSA").setProvider(BouncyCastleProvider.PROVIDER_NAME).build(privateKey);

			cmsSignedDataGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
					new JcaDigestCalculatorProviderBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build())
			.build(sha1Signer, holder));
			
			cmsSignedDataGenerator.addCertificates(certs);

			CMSTypedData chainMessage = new CMSProcessableByteArray(dataToSign);
			CMSSignedData sigData = cmsSignedDataGenerator.generate(chainMessage, false);

			byte[] signedData = sigData.getEncoded();
			byte[] signedData64 = Base64.encode(signedData);
			System.out.println(new String(signedData64));
			return signedData64;
			
		} catch(Exception e) {
			throw new RuntimeException("Error while generating certificate chain: " + e.getMessage(), e);
		}
	}
	public static void main(String f[]){
		Pkcs7gen pkcs7gen = new Pkcs7gen();
		//pkcs7gen.generateP7B("abc1234", "ACLPC3993R");
	}
}

