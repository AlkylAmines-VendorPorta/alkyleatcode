/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;

public interface TechnicalBidDao extends CommonDao<TechnicalBid, TechnicalBidDto> {
	
	public int updateTechnicalBidStatus(String status,Long itemBidId);

}
