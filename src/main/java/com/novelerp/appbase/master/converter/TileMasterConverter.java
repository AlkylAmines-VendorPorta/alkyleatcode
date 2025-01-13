package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.entity.TileMaster;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class TileMasterConverter extends CustomDozerConverter<TileMaster, TileMasterDto> implements ObjectConverter<TileMaster, TileMasterDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public TileMasterDto convertEntityToDto(TileMaster entity, Class<TileMasterDto> dto) {
		if(entity == null){
			return null;
		}
		TileMasterDto tileListDto = new TileMasterDto();
		tileListDto.setCode(entity.getCode());
		tileListDto.setParentId(entity.getParentId());
		tileListDto.setName(entity.getName());
		tileListDto.setTileMasterId(entity.getTileMasterId());
		tileListDto.setCreated(entity.getCreated());
		tileListDto.setUpdated(entity.getUpdated());
		tileListDto.setIsActive(entity.getIsActive());
		tileListDto.setDescription(entity.getDescription());
		tileListDto.setPartner(getParter(entity));
		tileListDto.setEntity(entity.getEntity());
		tileListDto.setCountWhereCondition(entity.getCountWhereCondition());
		tileListDto.setElementId(entity.getElementId());
		tileListDto.setEntity(entity.getEntity());
		tileListDto.setIsWorkflow(entity.getIsWorkflow());
		if(entity.getParentTile()!=null){
			TileMasterDto tile = new TileMasterDto();
			tile.setName(entity.getParentTile().getName());
			tile.setTileMasterId(entity.getParentTile().getParentId());
			tileListDto.setParentTile(tile);
		}
		
		return tileListDto;
	}
	
	private BPartnerDto getParter(TileMaster entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}
}
