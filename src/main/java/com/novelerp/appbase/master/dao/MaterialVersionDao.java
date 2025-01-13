package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.entity.MaterialVersion;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface MaterialVersionDao extends CommonDao<MaterialVersion, MaterialVersionDto> {
	
	public List<MaterialVersion> getMaterialVersionList();
	public List<MaterialVersion> getMaterialVersionById(Long id);
	/**
	 * @param materialId
	 * @return
	 */
	public String getMaterialVersionByMaterial();

}
