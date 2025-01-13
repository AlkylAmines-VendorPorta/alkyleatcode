package com.novelerp.appbase.master.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

/*@Controller*/
public class ItemGroupController {
	/*@Autowired
	private ItemGroupService itemGroupService;*/
	
	@RequestMapping(value = {"/itemGroup"}, method = RequestMethod.GET)
	public ModelAndView itemGroup() {
		System.out.println("..ItemGroupController-itemGroup()");
		return new ModelAndView("itemGroup");
	}
	
	/*@RequestMapping(value = "/getItemGroupList/{index}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String,Object> getItemGroupList(@PathVariable("index") int index) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<ItemGroupDto> itemGroupList = itemGroupService.getItemGroupList();
		map.put("DATA", itemGroupList);
		return map;
	}*/
}
