/**
 * @author Ankush
 */
package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.entity.Reference;
import com.novelerp.core.dao.CommonDao;

public interface ReferenceDao extends CommonDao<Reference,ReferenceDto>
{

	public List<Reference> getUserReferences(Long userID);
}