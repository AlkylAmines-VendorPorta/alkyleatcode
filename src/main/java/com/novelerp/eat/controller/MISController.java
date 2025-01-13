package com.novelerp.eat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.eat.dto.MISDto;
import com.novelerp.eat.service.MISService;

@Controller
public class MISController {

	@Autowired
	private MISService misService;
	private static final Logger log = LoggerFactory.getLogger(MISController.class);

	@RequestMapping(value = "/fetchMISData")
	public @ResponseBody Boolean fetchMISData() {
		try {
			List<MISDto> list = misService.readMisExcel();
			for (MISDto misDto : list) {
				misService.save(misDto);
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
