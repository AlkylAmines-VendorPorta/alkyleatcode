package com.novelerp.appbase.master.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.dto.TilesRecordCountDto;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.service.TileRecordCountService;

@Controller
public class TileRecordCountController {

	@Autowired
	private TileRecordCountService tileRecordCountService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@RequestMapping(value = { "/getTilesCounter" }, method = RequestMethod.GET)
	public @ResponseBody TilesRecordCountDto getTilesCounter(HttpServletRequest httpServletRequest) {
		TilesRecordCountDto countDto = new TilesRecordCountDto();
		RoleDto role = contextService.getDefaultRole();
		if(role!=null){
			Long roleId=role.getRoleId();
			List<TileMasterDto> list = roleAccessMasterService.getTilesDetailListByRoleId(roleId);
			countDto = tileRecordCountService.getTilesRecordCountsByTile(list);
		}
		return countDto;
	}
	
	@RequestMapping(value= {"/tahdrRecordCount/{tileId}"},method =RequestMethod.POST)
	public @ResponseBody TilesRecordCountDto auctiontiles(@PathVariable("tileId") Long tileId)
	{
		TilesRecordCountDto countDto = new TilesRecordCountDto();
		Set<RoleDto> role=contextService.getRoles();
		if(role!=null)
		{
			Long roleId=role.iterator().next().getRoleId();
			Map<String , Object> param = AbstractContextServiceImpl.getParamMap("roleId", roleId);
			param.put("tileId", tileId);
			List<RoleAccessMasterDto> list = roleAccessMasterService.findDtos("getSubTilesByRoleIdAndTileIDQuery",param);
			countDto = tileRecordCountService.getTilesRecordCounts(list);
		}
		return countDto;
	}
	@RequestMapping(value= {"/workflowRecordCount"},method =RequestMethod.POST)
	public @ResponseBody TilesRecordCountDto workflowtiles()
	{
		TilesRecordCountDto countDto = new TilesRecordCountDto();
		Set<RoleDto> role=contextService.getRoles();
		if(role!=null)
		{
			Long roleId=role.iterator().next().getRoleId();
			Map<String , Object> param = AbstractContextServiceImpl.getParamMap("roleId", roleId);
			/*param.put("tileId", tileId);*/
			List<RoleAccessMasterDto> list = roleAccessMasterService.findDtos("getSubTilesForWorkflowQuery",param);
			UserDto userDto=contextService.getUser();
			Map<String , Object> params = AbstractContextServiceImpl.getParamMap("userId", userDto.getUserId());
			countDto = tileRecordCountService.getWorkflowTilesRecordCounts(list,params);
		}
		return countDto;
	}
	
	@RequestMapping(value= {"/dashBoardRecordCount"},method =RequestMethod.GET)
	public @ResponseBody TilesRecordCountDto dashBoardRecordCount()
	{
		TilesRecordCountDto countDto = new TilesRecordCountDto();
		TilesRecordCountDto countDto2 = new TilesRecordCountDto();

		RoleDto role = contextService.getDefaultRole();
		if(role!=null){
			Long roleId=role.getRoleId();
			Map<String , Object> param = AbstractContextServiceImpl.getParamMap("roleId", roleId);
			Map<String , Object> param2 = AbstractContextServiceImpl.getParamMap("roleID", roleId);
			List<Long> tilesIds = new ArrayList<>();
			if(!role.getValue().equalsIgnoreCase("VENADM")){
				tilesIds.add(2L);
			}
			tilesIds.add(3L);
			tilesIds.add(4L);
			param.put("tileId", tilesIds);
			List<RoleAccessMasterDto> list = roleAccessMasterService.findDtos("getSubTilesByRoleIdAndTileIDQuery",param);
			List<RoleAccessMasterDto> list2 = roleAccessMasterService.findDtos("getTilesByRoleIdQuery",param2);
			List<TileMasterDto> list3 = roleAccessMasterService.getTilesDetailListByRoleId(roleId);
			list.addAll(list2);
			countDto = tileRecordCountService.getTilesRecordCounts(list);
			countDto2 = tileRecordCountService.getTilesRecordCountsByTile(list3);
			countDto.addCount(countDto2.getTileCountMap());
		}
		return countDto;
	}

}
