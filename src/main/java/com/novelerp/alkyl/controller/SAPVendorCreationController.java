package com.novelerp.alkyl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.document.sap.rfc.functions.ZvendorPortal_Zvendorportal_Client;

@Controller
@RequestMapping("/rest")
public class SAPVendorCreationController {

	@Autowired
	private ZvendorPortal_Zvendorportal_Client webClient; 
	
	@GetMapping(value="/createVendor")
	public void createVendor(){
		webClient.createVendor(null);
	}
	
	
}
