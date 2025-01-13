package com.novelerp.eat.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;

public class PR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PRDto pr = new PRDto();
		UserDto reqB = new UserDto();
		pr.setRequestedBy(reqB);
		PRLineDto prLine = new PRLineDto();
		UserDto desiredVendor = new UserDto();
		UserDto fixedVendor = new UserDto();
		prLine.setDesiredVendor(desiredVendor);
		prLine.setFixedVendor(fixedVendor);
		PRLineDto prServiceLine = new PRLineDto();
		List<PRLineDto> prServiceLines= new ArrayList<>();
		prServiceLines.add(prServiceLine);
		prLine.setPrServiceLines(prServiceLines);
		List<PRLineDto> prLines= new ArrayList<>();
		prLines.add(prLine);
		pr.setPrLines(prLines);
		try {
			String json = CommonUtil.getObjectJson(pr);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
