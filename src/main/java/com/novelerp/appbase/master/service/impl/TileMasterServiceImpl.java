package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.TileMasterConverterPlain;
import com.novelerp.appbase.master.dao.TileMasterDao;
import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.entity.TileMaster;
import com.novelerp.appbase.master.service.TileMasterService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class TileMasterServiceImpl extends AbstractServiceImpl<TileMaster, TileMasterDto> implements TileMasterService {

	@Autowired
	private TileMasterDao tileMasterDao;
	
	@Autowired
	private TileMasterConverterPlain tileMasterConverter;
	
	@PostConstruct
	private void init() {
		super.init(TileMasterServiceImpl.class, tileMasterDao, TileMaster.class, TileMasterDto.class);
		setObjectConverter(tileMasterConverter);
		
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<TileMasterDto> getTileMasterList()
	{
		List<TileMasterDto> tileMasterList=new ArrayList<TileMasterDto>();
		List<TileMaster> tileMasterEntity= tileMasterDao.findAll(" where is_summary='Y' and isactive='Y'", "sequenceNo asc");
		if(!tileMasterEntity.isEmpty()){
			for(TileMaster TileMaster:tileMasterEntity){
				TileMasterDto tileMasterDto=tileMasterConverter.convertEntityToDto(TileMaster, TileMasterDto.class);
				tileMasterList.add(tileMasterDto);
			}
		}
		return tileMasterList;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<TileMasterDto> getSubTileMasterList(){
		List<TileMasterDto> tileMasterList=new ArrayList<TileMasterDto>();
		List<TileMaster> tileMasterEntity= tileMasterDao.findAll(" where is_summary='N' and sequence_no=0 and isactive='Y'", "parentId asc");
		if(!tileMasterEntity.isEmpty()){
			for(TileMaster TileMaster:tileMasterEntity){
				TileMasterDto tileMasterDto=tileMasterConverter.convertEntityToDto(TileMaster, TileMasterDto.class);
				tileMasterList.add(tileMasterDto);
			}
		}
		return tileMasterList;
	}
	

}
