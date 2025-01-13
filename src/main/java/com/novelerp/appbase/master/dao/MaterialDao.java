package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface MaterialDao extends CommonDao<Material, MaterialDto> {
	
	public List<Material> getMaterialList();
	public String getMaterialListQuery();
	public List<Material> getMaterialById(Long id);
	public List<Material> getMaterialBySubGroupId(Long id);
	public String getItemSearchQuery(MaterialSearchDto material,boolean createdCheck);
	public String getMaterialListQuery(String searchColumn, String searchValue);
	public String getMaterialListQueryCountQry(String searchColumn,String searchValue);
	public String getQueryForTradingMaterial(MaterialSearchDto dto);
}
