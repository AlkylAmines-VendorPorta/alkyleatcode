package com.novelerp.eat.service;

import java.util.Map;

public interface SMSService {

	public Boolean sendSMS(String mnumber, String templateid, Map<String, String> params);

}
