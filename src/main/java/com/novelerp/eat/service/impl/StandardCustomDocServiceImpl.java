/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.StandardCustomDocDao;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.StandardCustomDoc;
import com.novelerp.eat.service.StandardCustomDocService;
import com.novelerp.eat.service.TAHDRDetailService;

@Service
public class StandardCustomDocServiceImpl extends AbstractContextServiceImpl<StandardCustomDoc, StandardCustomDocDto>
		implements StandardCustomDocService {

	@Autowired
	private StandardCustomDocDao standardCustomDocDao;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@PostConstruct
	void init(){
		super.init(StandardCustomDoc.class, standardCustomDocDao, StandardCustomDoc.class, StandardCustomDocDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto save(TAHDRDetailDto tahdrDetail,TAHDRDetailDto tahdrDetailDto){
		ResponseDto response=new ResponseDto();
		Set<StandardCustomDocDto> stdCstDocSet= new HashSet<>();
		for(StandardCustomDocDto stdCstDoc: tahdrDetail.getStandardCustomDoc()){
			stdCstDoc.setTahdrDetail(tahdrDetail);
			if(null==stdCstDoc.getStandardCustomDocId() || !(stdCstDoc.getStandardCustomDocId()>0)){
				stdCstDoc=super.save(stdCstDoc);
			}else if(stdCstDoc.getIsActive().equals("Y")){
				stdCstDoc=super.updateDto(stdCstDoc);
				Map<String, Object> tenderDoc = new HashMap<>();
				if(tahdrDetailDto!=null){
				if(tahdrDetailDto.getTenderDoc()!=null && tahdrDetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
					tenderDoc.put("tenderDoc", null);
					tahdrDetailService.updateByJpql(tenderDoc, "tahdrDetailId",tahdrDetail.getTahdrDetailId());
				}
				}
			}else{
				super.deleteById(stdCstDoc.getStandardCustomDocId());
			}
			stdCstDocSet.add(stdCstDoc);
		}
		tahdrDetail.setStandardCustomDoc(stdCstDocSet);
		response.setHasError(false);
		response.setMessage("Record Saved");
		tahdrDetail.setResponse(response);
		return tahdrDetail;
	}
	
	@Override
	public List<StandardCustomDocDto> getDocs(Long tahdrId){
		String query = standardCustomDocDao.getQueryForDocs();
		Map<String,Object> params = getParamMap("tahdrId", tahdrId);
		return findDtosByQuery(query, params);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.StandardCustomDocService#copyStdCustDocsToNewVersion(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyStdCustDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId) {
		return standardCustomDocDao.copyStdCustDocsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
	}
	
}
