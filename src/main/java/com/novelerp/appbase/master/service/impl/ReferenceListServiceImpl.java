/**
 * @author Ankush
 */
package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.ReferenceListConverter;
import com.novelerp.appbase.master.dao.ReferenceListDao;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.entity.ReferenceList;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class ReferenceListServiceImpl extends AbstractContextServiceImpl<ReferenceList, ReferenceListDto> implements ReferenceListService {

	@Autowired
	private ReferenceListDao referenceListDao;
	@Autowired
	private ReferenceListConverter referenceListConverter;
	@PostConstruct
	public void init(){
		super.init(ReferenceListServiceImpl.class, referenceListDao, ReferenceList.class, ReferenceListDto.class);
		/*setObjectConverter(referenceListConverter);*/
		setByPassProxy(true);
		
	}

	/*@Override*/
	public List<ReferenceListDto> getReferenceList(String referenceCode) {
		List<ReferenceList> referenceList=referenceListDao.getReferenceListByType(referenceCode);
		return getDtoList(referenceList);
	}

	@Override
	public Map<String, String> getReferenceListMap(String refCode) {
		List<ReferenceListDto> list=getReferenceList(refCode);
		Map<String, String> refList= new LinkedHashMap<>();
		if(!list.isEmpty()){
			for(ReferenceListDto refListDto:list){
				refList.put(refListDto.getCode(), refListDto.getName());
			}
		}
		return refList;
	}
	
	@Override
	public Map<String,ReferenceListDto> getRefListCollection(String refCode){
		List<ReferenceListDto> list=getReferenceList(refCode);
		Map<String, ReferenceListDto> refList= new HashMap<>();
		if(!list.isEmpty()){
			for(ReferenceListDto refListDto:list){
				refList.put(refListDto.getCode(), refListDto);
			}
		}
		return refList;
	}

	@Override
	@Transactional
	public boolean deleteReferenceList(Long referenceListId) {
		// TODO Auto-generated method stub
		return deleteById(referenceListId);
	}

	
}