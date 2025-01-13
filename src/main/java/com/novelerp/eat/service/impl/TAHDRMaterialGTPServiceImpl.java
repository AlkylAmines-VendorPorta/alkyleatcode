/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.TAHDRMaterialGTPDao;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.entity.TAHDRMaterialGTP;
import com.novelerp.eat.service.TAHDRMaterialGTPService;

@Service
public class TAHDRMaterialGTPServiceImpl extends AbstractContextServiceImpl<TAHDRMaterialGTP, com.novelerp.eat.dto.TAHDRMaterialGTPDto>
		implements TAHDRMaterialGTPService {

	@Autowired
	private TAHDRMaterialGTPDao materialGtpDao;
	
	@PostConstruct
	private void init() {
		super.init(TAHDRMaterialGTPServiceImpl.class,materialGtpDao,TAHDRMaterialGTP.class,TAHDRMaterialGTPDto.class);
		/*setObjectConverter(materialGtpConverter);*/
		setByPassProxy(true);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TAHDRMaterialGTPService#saveTahdrMaterialGtpList()
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<TAHDRMaterialGTPDto> saveTahdrMaterialGtpList(List<TAHDRMaterialGTPDto> gtpList) {
		if(gtpList.isEmpty()){
			return null;
		}
		List<TAHDRMaterialGTPDto> tahdrGtpList= new ArrayList<>();
		for(TAHDRMaterialGTPDto gtp:gtpList){
			if(gtp.getTahdrMaterialGtpId()!=null && gtp.getTahdrMaterialGtpId()>0 && gtp.getIsAdded().equals("N")){
				if(deleteById(gtp.getTahdrMaterialGtpId()))
					gtp.setTahdrMaterialGtpId(null);				
			}else if(gtp.getTahdrMaterialGtpId()!=null && gtp.getTahdrMaterialGtpId()>0 && gtp.getIsAdded().equals("Y")){
				gtp=updateDto(gtp);
			}else if(gtp.getTahdrMaterialGtpId()==null && gtp.getIsAdded().equals("Y")){
				gtp=save(gtp);
			}
			tahdrGtpList.add(gtp);
		}
		return tahdrGtpList; 
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyTahdrMaterialGTPToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		return materialGtpDao.copyTahdrMaterialGTPToNewVersion(newTahdrDetailId, oldTahdrDetailId);
	}
}
