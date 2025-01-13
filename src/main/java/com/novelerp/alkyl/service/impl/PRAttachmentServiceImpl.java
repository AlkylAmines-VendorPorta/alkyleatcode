package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dao.PRAttachmentDao;
import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.entity.PRAttachment;
import com.novelerp.alkyl.service.PRAttachmentService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.exception.CustomException;

@Service
public class PRAttachmentServiceImpl extends AbstractContextServiceImpl<PRAttachment, PRAttachmentDto> implements PRAttachmentService{

	@Autowired
	private PRAttachmentDao prAttachmentDao;
	
	@Autowired
	private PRComponent prComponent;
	
	@PostConstruct
	public void init(){
		super.init(PRAttachmentService.class, prAttachmentDao, PRAttachment.class, PRAttachmentDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRAttachmentDto> save(List<PRAttachmentDto> prAttSet){
		List<PRAttachmentDto> resDto=new ArrayList<>();
		if(prAttSet==null){
			return new ArrayList<>();
		}
		
		for(PRAttachmentDto dto:prAttSet){
			
			if(dto==null){
				throw new CustomException("Empty Doc in Upload");
			}
			
			if(dto.getPr()==null){
				throw new CustomException("Empty PR in DOC Upload");
			}
			
			PRAttachmentDto oldDto = prComponent.findPRAttById(dto.getPrAttachmentId());
			
			if(oldDto!=null){
				dto = copyNewDtoToOld(dto, oldDto);
			}
			
			if(dto.getPrAttachmentId()==null){
				dto = super.save(dto);
			}else{
				dto = super.updateDto(dto);
			}
			
			resDto.add(dto);

		}
			
		return resDto;
	}
	
	private PRAttachmentDto copyNewDtoToOld(PRAttachmentDto newDto, PRAttachmentDto oldDto){
		
		oldDto.setCode(newDto.getCode());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setPr(newDto.getPr());
		oldDto.setName(newDto.getName());
		oldDto.setIstc(newDto.getIstc());
		oldDto.setAttachment(newDto.getAttachment());
		
		return oldDto;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRAttachmentDto> savePRDoc(List<PRAttachmentDto> prAttSet){
		List<PRAttachmentDto> resDto=new ArrayList<>();
		if(prAttSet==null){
			return new ArrayList<>();
		}
		
		for(PRAttachmentDto dto:prAttSet){
			
			if(dto==null){
				throw new CustomException("Empty Doc in Upload");
			}
			
			if(dto.getPr()==null){
				throw new CustomException("Empty PR in DOC Upload");
			}
			
			//PRAttachmentDto oldDto = prComponent.findPRAttById(dto.getPrAttachmentId());
			
//			PRAttachmentDto oldDto1 = prComponent.findPRAttByattId(dto.getAttachment().getAttachmentId());
			
			PRAttachmentDto oldDto1 = prComponent.findPRAttByattId(dto.getAttachment().getFileName());
		
			if(oldDto1!=null) {
			          if(oldDto1.getAttachment().getFileName().equals(dto.getAttachment().getFileName())) {
				
				                dto = copyNewDtoToOld(dto, oldDto1);
				                dto = super.updateDto(dto);
			          }
			}
			else {
				dto = super.save(dto);
			}
			
//			if(oldDto1!=null){
//				dto = copyNewDtoToOld(dto, oldDto1);
//			}
//			else {
//				dto = super.save(dto);
//			}
			
//			if(dto.getPrAttachmentId()==null){
//				dto = super.save(dto);
//			}else{
//				dto = super.updateDto(dto);
//			}
			
			resDto.add(dto);

		}
			
		return resDto;
	}
	
}
