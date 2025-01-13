/**
 * @author Ankush
 */

package com.novelerp.eat.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.TAHDRMaterialDao;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;
import com.novelerp.eat.service.TAHDRMaterialService;

@Service
public class TAHDRMaterialServiceImpl extends AbstractContextServiceImpl<TAHDRMaterial, TAHDRMaterialDto> implements TAHDRMaterialService{

	@Autowired
	private TAHDRMaterialDao tahdrMaterialDao;
	
	
	@Autowired
	private MaterialService materialService;
	
	@PostConstruct
	private void init() {
		super.init(TAHDRMaterialServiceImpl.class, tahdrMaterialDao, TAHDRMaterial.class, TAHDRMaterialDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional
	public TAHDRMaterialDto saveTAHDRMaterial(TAHDRMaterialDto tahdrMaterial) {
		if(null!=tahdrMaterial.getTahdrMaterialId() && tahdrMaterial.getTahdrMaterialId()>0){
			tahdrMaterial=super.updateDto(tahdrMaterial);
		}else{
			tahdrMaterial=super.save(tahdrMaterial);
		}
		return tahdrMaterial;
	}

	@Override
	@Transactional
	public boolean updateTAHDRMaterial(TAHDRMaterialDto tahdrMaterial) {
		tahdrMaterial.setMaterial(materialService.getMaterial(tahdrMaterial.getMaterial().getMaterialId()));
		boolean flag = update(tahdrMaterial);
		return flag;
	}
	
	@Override
	public List<TAHDRMaterialDto> getTahdrMaterialList(Long tahdrDetailId) {
		List<TAHDRMaterial> materialList=tahdrMaterialDao.getTahdrMaterialList(tahdrDetailId);
		return getDtoList(materialList);
	}
	
	@Override
	public List<TAHDRMaterialDto> getTahdrMaterialListByTahdrId(Long tahdrId) {
		List<TAHDRMaterial> materialList=tahdrMaterialDao.getTahdrMaterialListByTahdrId(tahdrId);
		return getDtoList(materialList);
	}
	
	@Override
	public boolean isItemAlreadyExist(TAHDRMaterialDto material){
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("materialId", material.getMaterial().getMaterialId());
		map.put("tahdrDetailId", material.getTahdrDetail().getTahdrDetailId());
		/*map.put("materialTypeCode", material.getMaterialTypeCode());*/
		return !findDtos("checkExistingMaterialQuery",map).isEmpty();
	}

	@Override
	public TAHDRMaterialDto getTahdrMaterialById(Long tahdrMaterialId) {
		TAHDRMaterial tahdrMaterial=tahdrMaterialDao.getTahdrMaterialById(tahdrMaterialId);
		return getDto(tahdrMaterial);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TAHDRMaterialService#deleteTAHDRMaterial(com.novelerp.eat.dto.TAHDRMaterialDto)
	 */
	@Override
	@Transactional
	public boolean deleteTAHDRMaterial(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TAHDRMaterialService#copyTahdrMaterialsToNewVersion(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyTahdrMaterialsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId) {
		return tahdrMaterialDao.copyTahdrMaterialsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
	}

	@Override
	public TAHDRMaterialDto updateTahdrMaterialQty(Long balanceQty) {
		TAHDRMaterial dto=new TAHDRMaterial();
		if(balanceQty!=null){
			dto=tahdrMaterialDao.updateTahdrMaterialQuantity(balanceQty);
		}
		return getDto(dto);
	}
	@Override
	public boolean updateBaseRate(Long tahdrId){
		int count=tahdrMaterialDao.updateBaseRate(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	public long getTotalMaterialItemsCount(Long tahdrDetailId) {
		long totalCount;
		totalCount =tahdrMaterialDao.getMaterialItemTotalCountQry(tahdrDetailId);
		return totalCount;
	}
}

