package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Component
public class MaterialComponent {

	@Autowired
	private MaterialService materialService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public MaterialDto getMaterialByCode(String materialCode){
		return materialService.findDto("getMatrialByCode", AbstractContextServiceImpl.getParamMap("materialCode", materialCode));
	}
	
}
