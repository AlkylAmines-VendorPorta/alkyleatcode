package com.novelerp.core.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.core.dto.MailDto;
/**
 * 
 * @author Administrator
 *
 */
public interface MailService {
    public void sendEmail(MailDto mailDto);
    
    public boolean sendEmailWithResult(MailDto mailDto) ;
    public void sentMailByTemplate(MailTemplateDto dto,Map<String,Object> map,String emailId);

	/**
	 * @param mailDto
	 * @param isAsync
	 * @return
	 */
	public boolean sendEmailWithResult(MailDto mailDto, boolean isAsync);

	/**
	 * @param mailDto
	 * @param isAsync
	 */
	public void sendEmail(MailDto mailDto, boolean isAsync);
	
	/**
	 * @param mailDto
	 * @param isAsync
	 * send mail to single emailId
	 */
	public boolean sendSingleEmailWithResult(MailDto mailDto);
	
	/**
	 * @param mailDto
	 * @param isAsync
	 * @return
	 */
	public void sendSingleEmailWithResult(MailDto mailDto, boolean isAsync);

	/**
	 * @param dto
	 * @param map
	 * @param emailId
	 * @param async
	 */
	public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, String emailId, boolean async);
	
	/**
	 * @param dto
	 * @param map
	 * @param listToEmailIds
	 * @param listCCEmailIds
	 * @param async
	 */
	public void sentMailByTemplateAsync(MailTemplateDto dto, Map<String, Object> map, List<String> toEmailIds, List<String> ccEmailIds, boolean async);

}
