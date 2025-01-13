package com.novelerp.appcontext.service.impl;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.novelerp.appbase.master.dto.SFTPUploadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.eat.dto.TAHDRDto;

@Service("sessionUserContext")
public class ContextServiceImpl implements ContextService{

	private static final Logger log = LoggerFactory.getLogger(ContextServiceImpl.class);
	
/*	@Autowired
	private HttpSession session;
*/	
	public static HttpSession getNewSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); /* true = allow create */
	}
	
	public static HttpSession getCurrentSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession();
	}

	
	@Override
	public void contextInitializer(UserDto userDto) {
		log.info("Loading user context");
		HttpSession session=  getNewSession();
		ContextDto context =  new ContextDto(userDto);
		session.setAttribute("context", context);
	}

	/*@Override
	public void contextInitializer(UserDto userDto,UserSessionDto userSession) {
		log.info("Loading user context");
		HttpSession session=  getNewSession();
		ContextDto context =  new ContextDto(userDto,userSession);
		session.setAttribute("context", context);
	}
	*/
	@Override
	public ContextDto getContext() {
		return (ContextDto) getCurrentSession().getAttribute("context");
	}

	@Override
	public Set<RoleDto> getRoles(){
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getRoles();
	}

	@Override
	public UserDto getUser() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getUserDto();
	}

	@Override
	public BPartnerDto getPartner() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getPartnerDto();
	}

	@Override
	public UserDetailsDto getUserDetails() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getUserDetailsDto();
	}

	@Override
	public RoleDto getDefaultRole(){
		Set<RoleDto> roles =  getRoles();
		if(!CommonUtil.isCollectionEmpty(roles)){
			return roles.iterator().next();
		}
		return null;
	}
	@Override
	public void setSFTPRequiredInfo(BPartnerDto dto,String docType,TAHDRDto tahdr) {
		HttpSession session=ContextServiceImpl.getCurrentSession();
		session.removeAttribute("fileUpload");
		SFTPUploadDto uploadDto=new SFTPUploadDto();
		uploadDto.setPartner(dto);
		/*uploadDto.setTahdr(tahdr);*/
		if(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT.equals(docType)){
			uploadDto.setAttachmentType(AppBaseConstant.PROCUREMENT_TENDER_ATTACHMENT);
		}else if(AppBaseConstant.TENDER_TYPE_CODE_WORKS.equals(docType)){
			uploadDto.setAttachmentType(AppBaseConstant.WORKS_TENDER_ATTACHMENT);
		}else if(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD.equals(docType)){
			uploadDto.setAttachmentType(AppBaseConstant.FORWARD_AUCTION_ATTACHMENT);
		}else if(AppBaseConstant.DOCUMENT_TYPE_QUICK_RFQ.equals(docType)){
			uploadDto.setAttachmentType(AppBaseConstant.DOCUMENT_TYPE_QUICK_RFQ);
		}else if(AppBaseConstant.DOCUMENT_TYPE_RFQ.equals(docType)){
			uploadDto.setAttachmentType(AppBaseConstant.DOCUMENT_TYPE_RFQ);
		}else{
			uploadDto.setAttachmentType(docType);
		}
		session.setAttribute("fileUpload",uploadDto);		
	}

	@Override
	public void setRoles(Set<RoleDto> roleSet) {
		// TODO Auto-generated method stub
		
	}
}
