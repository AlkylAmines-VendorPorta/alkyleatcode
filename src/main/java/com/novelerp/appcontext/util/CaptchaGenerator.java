package com.novelerp.appcontext.util;

import org.springframework.stereotype.Component;

@Component
public class CaptchaGenerator {


	public String generateCaptchaText(int captchaLength) {

		String saltChars = "1234567890";
		StringBuffer captchaStrBuffer = new StringBuffer();
		java.util.Random rnd = new java.util.Random();
		// build a random captchaLength chars salt
		while (captchaStrBuffer.length() < captchaLength) {
			int index = (int) (rnd.nextFloat() * saltChars.length());
			captchaStrBuffer.append(saltChars.substring(index, index + 1));
			//captchaStrBuffer.append(" ");
		}
		return captchaStrBuffer.toString().trim();
	}
}
