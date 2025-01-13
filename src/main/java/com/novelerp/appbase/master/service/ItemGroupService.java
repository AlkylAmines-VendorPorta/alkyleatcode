package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.ItemGroupDto;
import com.novelerp.appbase.master.entity.ItemGroup;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

public interface ItemGroupService extends CommonService<ItemGroup, ItemGroupDto> {
	public List<ItemGroupDto> getItemGroupList();
	public ItemGroupDto getItemGroup(Long partnerId);
	/*public ResponseDto addIsStd(IsStdDto dto);
	public ResponseDto editIsStd(IsStdDto dto);
	public ResponseDto deleteIsStd(Long id);*/
}

