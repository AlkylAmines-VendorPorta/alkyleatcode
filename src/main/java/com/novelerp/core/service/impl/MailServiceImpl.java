package com.novelerp.core.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.thread.EmailThread;
import com.novelerp.core.util.AppPropertyUtil;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private AppPropertyUtil appPropertyUtil;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

	@Override
	public void sendEmail(MailDto mailDto) {
		sendMail(mailDto);
	}
	
	@Override
	public boolean sendEmailWithResult(MailDto mailDto) {
		return sendMailWithResult(mailDto);
	}
	@Override
	public boolean sendSingleEmailWithResult(MailDto mailDto) {
			return sendSingleMailWithResult(mailDto);
	}

	@Override
	public void sendSingleEmailWithResult(MailDto mailDto, boolean isAsync) {
		if(isAsync){
			try{
				startThread(getMimeMessageForSingleMail(mailDto));
			}catch(Exception e){
				LOG.error(e.getMessage());
			}
		}else{
			sendSingleMail(mailDto);
		}
	}
	
	@Override
	public void sendEmail(MailDto mailDto, boolean isAsync) {
		if(isAsync){
			try{
				startThread(getMimeMessage(mailDto));
			}catch(Exception e){
				LOG.error(e.getMessage());
			}
		}else{
			sendMail(mailDto);
		}
	}
	
	@Override
	public boolean sendEmailWithResult(MailDto mailDto, boolean isAsync) {
		if(isAsync){
			try{
				startThread(getMimeMessage(mailDto));
				return true;
			}catch(Exception e){
				LOG.error(e.getMessage());
				return false;
			}
		}else{
			return sendMailWithResult(mailDto);
		}
	}
	
	private void startThread(MimeMessage message){
		EmailThread emailThread=new EmailThread(message);
		Thread t=new Thread(emailThread);
		t.start();
	}
	
	private void sendMail(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");

		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			for (String to : mailDto.getRecipientList()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			for (String to : mailDto.getCcList()) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
			}
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			LOG.info("Email Sent Successfully!! ....");
		} catch (MessagingException e) {
			LOG.error("EXCEPTION", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("EXCEPTION", e);
		}catch (Exception e) {
			LOG.error("EXCEPTION", e);
		}
	}
	
	private boolean sendMailWithResult(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			for (String to : mailDto.getRecipientList()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			for (String to : mailDto.getCcList()) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
			}
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			LOG.info("EMail Sent Successfully!! ....");
			return true;
		} catch (MessagingException e) {
			LOG.error("EXCEPTION", e);
			return false;
		} catch (UnsupportedEncodingException e) {
			LOG.error("EXCEPTION", e);
			return false;
		}
	}

	private Properties getMailProperties() {


		//props.put("mail.smtp.starttls.enable",tls);
		/*String host = appPropertyUtil.getProperty("mail.smtp.host");*/
		String host =sysConfiguratorService.getPropertyConfigurator("mail.smtp.host");
		/*String auth = appPropertyUtil.getProperty("mail.smtp.auth");*/
		String auth =sysConfiguratorService.getPropertyConfigurator("mail.smtp.auth");
		/*String port = appPropertyUtil.getProperty("mail.smtp.port");*/
		String port =sysConfiguratorService.getPropertyConfigurator("mail.smtp.port");
		String tls = appPropertyUtil.getProperty("mail.smtp.starttls.enable");
		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.starttls.enable","true");
		//If SMTP server uses SSL
		/*props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");*/
		return props;
	}

	private Session getSessionInstance(Properties props) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		/*final String password = appPropertyUtil.getProperty("mail.password");*/
		final String password =sysConfiguratorService.getPropertyConfigurator("mail.password");
		 Authenticator auth = new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication("portal@alkylamines.com", "Aacl*2020");
				//	return new javax.mail.PasswordAuthentication("alkylapps@alkylamines.com", "SpineisSpine");
				}
		 };
		return Session.getInstance(props, auth);
	}

	@Override
	public void sentMailByTemplate(MailTemplateDto dto, Map<String, Object> map, String emailId) {
		/*MailDto mailDto=new MailDto();
		List<String> emails=new ArrayList<>();
		String content=dto.getMailTemplate();
		for(Map.Entry<String, Object> entry : map.entrySet()){
			String key = entry.getKey();
			String value = (String) entry.getValue();
			content=content.replace(key, value);
		}
		mailDto.setMailContent(content);
		mailDto.setSubject(dto.getSubject());
		emails.add(emailId);
		mailDto.setRecipientList(emails);
		sendMail(mailDto);*/
	}
	
	@Override
	//public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, String emailId,boolean async) {
	public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, String emailId,boolean async) {
		
		MailDto mailDto=new MailDto();
		List<String> emails=new ArrayList<>();
		List<String> ccEmailIds=new ArrayList<>();
		String content=dto.getMailTemplate();
		for(Map.Entry<String, Object> entry : map.entrySet()){
			String key = entry.getKey();
			String value = (String) entry.getValue();
			content=content.replace(key, value);
		}
		mailDto.setMailContent(content);
		mailDto.setSubject(dto.getSubject());
		emails.add(emailId);
		ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
		ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM_1);
		mailDto.setRecipientList(emails);
		mailDto.setCcList(ccEmailIds);
		sendEmail(mailDto,async);
	}
	
	private MimeMessage getMimeMessageForSingleMail(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			String to= mailDto.getSingleRecipient();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			for (String toc : mailDto.getCcList()) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(toc));
			}
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			/*Transport.send(message);*/
			return message;
			/*LOG.info("EMail Sent Successfully!! ....");*/
		} catch (Exception e) {
			LOG.error("EXCEPTION", e);
			return null;
		}
	}
	
	private MimeMessage getMimeMessage(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
	//	final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		final String from ="portal@alkylamines.com";
	//	MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
//		BodyPart messageBodyPart = new MimeBodyPart();
		 MimeBodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			for (String to : mailDto.getRecipientList()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			if(mailDto.getSingleRecipient()!=null){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDto.getSingleRecipient()));
			}
			for (String to : mailDto.getCcList()) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
			}
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			
			if(null!=mailDto.getFilePath() && null!=mailDto.getFileName()){
				attachmentBodyPart.setContent(mailDto.getMailContent(), "application/pdf; charset=utf-8");
				String filename = mailDto.getFileName();
				String filePath=mailDto.getFilePath();

				 String path=filePath+filename;
				
				attachmentBodyPart.attachFile(new File(path));
	            DataSource source = new FileDataSource(path);
	            
	            attachmentBodyPart.setDataHandler(new DataHandler(source));
	            attachmentBodyPart.setFileName(filename);
	            multipart.addBodyPart(attachmentBodyPart);
//			DataSource source = new FileDataSource(mailDto.getFilePath());
//	         messageBodyPart.setDataHandler(new DataHandler(source));
//	         messageBodyPart.setFileName(mailDto.getFileName());
			}
			
			
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			/*Transport.send(message);*/
			return message;
			/*LOG.info("EMail Sent Successfully!! ....");*/
		} catch (Exception e) {
			LOG.error("EXCEPTION", e);
			return null;
		}
	}
	private boolean sendSingleMailWithResult(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal"));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			String to =mailDto.getSingleRecipient();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			LOG.info("EMail Sent Successfully!! ....");
			return true;
		} catch (MessagingException e) {
			LOG.error("EXCEPTION", e);
			return false;
		} catch (UnsupportedEncodingException e) {
			LOG.error("EXCEPTION", e);
			return false;
		}
	}
	
	private void sendSingleMail(MailDto mailDto) {
		/*final String from = appPropertyUtil.getProperty("mail.username");*/
		final String from =sysConfiguratorService.getPropertyConfigurator("mail.username");
		MimeMessage message = new MimeMessage(getSessionInstance(getMailProperties()));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject(mailDto.getSubject(), "text/html");
			String to =mailDto.getSingleRecipient();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			messageBodyPart.setContent(mailDto.getMailContent(), "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			LOG.info("EMail Sent Successfully!! ....");
		} catch (MessagingException e) {
			LOG.error("EXCEPTION", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("EXCEPTION", e);
		}
	}
	
	public static void main(String args[]){
		MailServiceImpl ms=new MailServiceImpl(); 
		MailDto mailDto=new MailDto();
		
		final String from = "noreply@novelerp.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.novelerp.com");
		props.put("mail.smtp.ssl.trust", "mail.novelerp.com");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.starttls.enable",true);

		final String password = "Novel@123";
		 Authenticator auth = new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(from, password);
				}
		 };		
		
		MimeMessage message = new MimeMessage(Session.getInstance(props, auth));
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from, "Alkyl Amines Portal "));
			message.setSentDate(new Date());
			message.setSubject("Test Mail", "text/html");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("ankush.a@novelerp.com"));
			messageBodyPart.setContent("This is a test mail", "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			LOG.info("EMail Sent Successfully!! ....");
		} catch (MessagingException e) {
			LOG.error("EXCEPTION", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("EXCEPTION", e);
		}
	}
	
	//public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, List<String> toEmailIds,List<String> ccEmailIds,boolean async) {
	public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, List<String> toEmailIds,List<String> ccEmailIds,boolean async) {	
	MailDto mailDto=new MailDto();
		String content=dto.getMailTemplate();
		for(Map.Entry<String, Object> entry : map.entrySet()){
			String key = entry.getKey();
			String value = (String) entry.getValue();
			content=content.replace(key, value);
		}
		mailDto.setMailContent(content);
		mailDto.setSubject(dto.getSubject());
		mailDto.setRecipientList(toEmailIds);
		mailDto.setCcList(ccEmailIds);
		if(null!=dto.getFileName() && null!=dto.getFilePath()){
		mailDto.setFileName(dto.getFileName());
		mailDto.setFilePath(dto.getFilePath());
		}
		sendEmail(mailDto,async);
	}
	
}
