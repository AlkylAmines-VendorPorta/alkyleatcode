package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface GtpParameterDao extends CommonDao<GtpParameter, GtpParameterDto> {

	public List<GtpParameter> getGtpParameterList();
	public List<GtpParameter> getGtpParameterById(Long id);
	/**
	 * @return
	 */
	public String getGtpParameterListByMaterial();
	
	public int deleteCopiedGTP(Long materialId);
	public String getAllGtpParameterQuery(String searchMode, String searchCalue);
	public String getAllGtpParameterCountQuery(String searchColumn, String searchValue);
}
