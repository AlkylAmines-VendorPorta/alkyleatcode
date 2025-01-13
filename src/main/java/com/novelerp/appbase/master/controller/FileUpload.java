package com.novelerp.appbase.master.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;

@Controller
public class FileUpload {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value="/uploadAttachment",method = RequestMethod.POST)
	public @ResponseBody String doUpload(@RequestParam("file") MultipartFile multipartFile,HttpSession session) {
		/*List<Attachment> attachments=null;*/
		try {
			AttachmentDto attach=new AttachmentDto();
			attach.setMedia(multipartFile.getBytes());
			attach.setFileName(multipartFile.getOriginalFilename());
			//attachmentService.save(attach);
			session.setAttribute("Attachment", attach);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return "Success";
	}

}
