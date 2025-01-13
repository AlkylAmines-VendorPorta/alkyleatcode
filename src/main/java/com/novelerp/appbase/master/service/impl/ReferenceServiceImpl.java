package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.ReferenceDao;
import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.entity.Reference;
import com.novelerp.appbase.master.service.ReferenceService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class ReferenceServiceImpl extends AbstractContextServiceImpl<Reference, ReferenceDto> implements ReferenceService {

	
	@Autowired
	private ReferenceDao referenceDao;
	
	@PostConstruct
	public void init() {
		super.init(ReferenceServiceImpl.class, referenceDao, Reference.class, ReferenceDto.class);
		setByPassProxy(true);
	}

	@Override
	public Map<String, ReferenceDto> getReferenceMap() {
		List<ReferenceDto> references = findAll();
		return loadMap(references);
	}
	
	public Map<String, ReferenceDto> loadMap(List<ReferenceDto> references){
		Map<String, ReferenceDto> referenceMap = new HashMap<>();
		for(ReferenceDto reference: references){
			referenceMap.put(reference.getValue(),reference);
		}
		return referenceMap;
	}
	

	@Override
	public List<ReferenceDto> getReference(Long userID) {
		List<ReferenceDto> referenceList  = new ArrayList<>();
		try{
			List<Reference> references =  referenceDao.getUserReferences(userID);
			referenceList = getDtoList(references);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return referenceList;
	}

	@Override
	public List<ReferenceDto> getAllReferences(String reference) {
		List<ReferenceDto> referenceList  = new ArrayList<>();
		List<Reference> references =null;
		try{
			if(reference.equals("SYSADM"))
			{
				references =  referenceDao.findAll(" where value<>'SYSADM' ",null);
			}else
				references =  referenceDao.findAll(" where isadmin='N' or value='FINADM' ",null);
			referenceList = getDtoList(references);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return referenceList;
	}

	@Override
	public List<ReferenceDto> getUserReferences(Long userID) {
		List<ReferenceDto> referenceList  = new ArrayList<>();
		try{
			List<Reference> references =  referenceDao.getUserReferences(userID);
			referenceList = getDtoList(references);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return referenceList;
	}

	@Override
	public ReferenceDto getReferenceById(Long referenceId) {
		ReferenceDto reference  = new ReferenceDto();
		try{
			Reference references =  referenceDao.findOne(referenceId);
			reference = getDto(references);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return reference;
	}

	@Override
	@Transactional
	public boolean deleteReference(Long referenceId) {
		// TODO Auto-generated method stub
		return deleteById(referenceId);
	}
}