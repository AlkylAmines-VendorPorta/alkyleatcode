/**
 * @author Ankush
 */
package com.novelerp.core.thread;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novelerp.core.service.MailService;

public class EmailThread implements Runnable {

	MimeMessage message;
	
	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);
	
	public EmailThread(MimeMessage message){
		this.message= message;
	}
	
	@Override
	public void run() {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			LOG.error("EXCEPTION", e);
			e.printStackTrace();
		}

	}

}
