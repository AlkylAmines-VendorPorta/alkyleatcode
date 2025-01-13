package com.novelerp.core.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.common.validation.CommonValidations;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.core.dto.CommonDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.entity.PO;
import com.novelerp.core.service.ORMUtil;
import com.novelerp.core.util.ServiceConstant;

/**
 * Abstract service implementation for basic CRUD operations. User must manually set created and updated.
 * @author Vivek Birdi
 * @param <E> - Class annotated with javax.persistence.Entity and extending PO class
 * @param <D> - Class extending CommonDto
 */
public abstract class AbstractServiceImpl <E extends PO, D extends CommonDto> {
	
	private Class<E> entityClass ;
	private Class<D> dtoClass;
	protected Logger log; 
	private CommonDao<E,D> dao;
	
	private boolean byPassProxy;
	protected void init(Class<?> serviceClass, CommonDao<E,D> dao
			,Class<E> entityClass, Class<D> dtoClass){
		log = LoggerFactory.getLogger(serviceClass);
		this.entityClass= entityClass;
		this.dtoClass=dtoClass;
		this.dao =dao;
	}

	protected void setByPassProxy(boolean byPassProxy){
		this.byPassProxy = byPassProxy;
	}
	
	@Autowired
	@Qualifier("AUTO_DOZER_CONVERTER")
	private ObjectConverter<E,D> converter;
	
	@Autowired
	@Qualifier(ServiceConstant.ORM_UTIL_HIBERNATE)
	private ORMUtil ormUtil;

	/**
	 * Set custom converter
	 * @param converter
	 */
	protected void setObjectConverter(ObjectConverter<E,D> converter){
		this.converter = converter;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public E create(E entity){
		return dao.create(entity);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean create(D dto){
		boolean saved =true;
		try{
			if(!beforeSave(dto)){
				log.error("Before save failed..");
				return false;
			}
			E entity = getEntity(dto);
			create(entity);
			if(!afterSave(dto)){
				log.error("After save failed");
			}
		}catch (Exception e) {
			log.error("EXCEPTION", e);
			saved =false;
		}
		return saved;
	}
	
	public boolean beforeSave(D dto){		
		return true;
	}
	
	public boolean afterSave(D dto){
		return true;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public D save(D dto){

		if(!beforeSave(dto)){
			log.error("Before save failed..");
			ResponseDto response =  getResponseWithErrorSingle("Before Save failed");
			dto.setResponse(response);
			return dto;
		}
		try{
			E entity = getEntity(dto);
			entity = create(entity);
			dto = getDto(entity);
			if(!afterSave(dto)){
				log.error("After save failed");
			}
			ResponseDto response = new ResponseDto(false, "Record Saved");
			dto.setResponse(response);
		}catch (Throwable e) {
			ResponseDto response =  getResponseWithErrorSingle(e.getMessage());
			dto.setResponse(response);
			log.error("EXCEPTION", e);
			throw e;
			
		}				
		return dto;
	}
	
	/**
	 * Get entity object from dto  
	 * @param dto
	 * @return Entity object
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public  E getEntity(D dto){
		return converter.getEntityFromDto(dto,entityClass);
	}
	
	/**
	 * get list of All Dtos with default converters.
	 * @return List of Dtos
	 */
	public List<D> findAll(){
		return findAll(null);
	}
	
	/**
	 * Get List of all dtos with custom converter
	 * @param currentConverter
	 * @return List of Dtos
	 */
	public List<D> findAll(ObjectConverter<E,D> currentConverter){
		List<E> entityList = null;
		try{
			entityList = dao.findAll();
		}catch (Exception e) {
			log.error("Exception while fetching records", e);
		}
		return getDtoList(entityList,currentConverter);
	}
	
	/**
	 * get Dto Object
	 * @param entity
	 * @return Dto object from entity object
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public D getDto(E entity){
		return 	getDto(entity, null);
	}
	
	/**
	 * get Dto Object
	 * @param entity
	 * @param currentConverter
	 * @return Dto object from entity object
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public D getDto(E entity, ObjectConverter<E, D> currentConverter){	
		if(entity==null){
			return null;
		}
		if(byPassProxy){
			E target = getTargetEntity(entity);
			return converter.getDtoFromTargetEntity(target, dtoClass);
		}
		
		if(currentConverter != null){
			return currentConverter.getDtoFromEntity(entity, dtoClass);
		}
		return 	converter.getDtoFromEntity(entity, dtoClass);
	}

	
	/**
	 * Get List of entity
	 * @param entityList
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public  List<D> getDtoList(List<E> entityList){
		List<D> dtoList =  new ArrayList<>();
		if (CommonValidations.isEmpty(entityList)){
			return dtoList;
		}
		for(E entity: entityList){
			D dto = getDto(entity);
			if(dto !=null){
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * Get List of entity
	 * @param entityList
	 * @param currentConverter
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public  List<D> getDtoList(List<E> entityList, ObjectConverter<E, D> currentConverter){
		List<D> dtoList =  new ArrayList<>();
		if (CommonUtil.isListEmpty(entityList)){
			return dtoList;
		}
		for(E entity: entityList){
			D dto = getDto(entity,currentConverter);
			if(dto !=null){
				dtoList.add(dto);
			}
		}
		return dtoList;
	}


	
	/**
	 * update Entity
	 * @param entity
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public E updateEntity(E entity) {
		return dao.update(entity);
	}

	
	
	/**
	 * Update
	 * @param dto
	 * @return true if updated, false otherwise. 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(D dto){
		boolean updated =false;		
		try{
			E entity =  getEntity(dto);
			updateEntity(entity);
			updated = true;
		}catch (Throwable e) {
			log.error("Exception", e);
			throw e;
		}
		return updated;
	}
	
	/**
	 * Update
	 * @param dto
	 * @return true if updated, false otherwise. 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public D updateDto (D dto){
		try{
			E entity =  getEntity(dto);
			entity = 	updateEntity(entity);
/*			dto = getDto(entity);*/
			ResponseDto response = new ResponseDto(false, "Record updated.");
			dto.setResponse(response);
		}catch (Throwable e) {
			ResponseDto response =  getResponseWithErrorSingle(e.getMessage());
			dto.setResponse(response);
			log.error("EXCEPTION", e);
			throw e;
		}				
		return dto;
	}
	

		
	/**
	 * Delete the entity
	 * @param entity
	 * @return true if deleted successfully, false otherwise
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(E entity) {
		try{
			dao.delete(entity);
		}catch (Throwable e) {
			log.error("Exception", e);
			throw e;
		}
		return true;
	}

	/**
	 * delete Entity of matching entity
	 * @param entityId
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteById(final Long entityId) {
		E entity = null;
		try{
			entity = findOne(entityId);
			delete(entity);
		}catch (Throwable e) {
			log.error("Exception", e);
			throw e;
		}
		return true;
	}
	
	/**
	 * find entity by matching id
	 * @param id
	 * @return entity if found, null otherwise
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public E findOne(final Long id) {
		E entity =null;
		try{
			entity = dao.findOne(id);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return entity;
	}
	
	/**
	 * Find Dto by ID
	 * @param id
	 * @return
	 */	
	@Transactional(propagation=Propagation.SUPPORTS)
	public D findDto(final Long id){
		E entity = findOne(id);
		if(entity == null){
			return null;
		}
		return getDto(entity);
	}

	/**
	 * Find Dtos where
	 * @param where
	 * @param params
	 * @param orderBy
	 * @return List of Dtos
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> find(final String where, Map<String, Object> params, String orderBy){
		return find(where, params, orderBy, null);
	}
	

	
	/**
	 * Find Dtos where
	 * @param where
	 * @param params
	 * @param orderBy
	 * @param currentConverter
	 * @return List of Dtos
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> find(final String where, Map<String, Object> params, String orderBy, ObjectConverter<E, D> currentConverter){
		List<E> entityList = findEntities(where, params, orderBy);		
		return getDtoList(entityList,currentConverter);
	}
	
	/**
	 * @param queryString
	 * @param params
	 * @return List of Dto
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtosByQuery(final String query, Map<String, Object> params){
		List<E> entityList = findEntities(query, params);		
		return getDtoList(entityList);
		
	}
	
	/**
	 * 
	 * @param queryString
	 * @param params
	 * @param currentConverter
	 * @return List of Dto
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtosByQuery(final String query, Map<String, Object> params, ObjectConverter<E, D> currentConverter){
		List<E> entityList = findEntities(query, params);		
		return getDtoList(entityList, currentConverter);		
	}


	
	/**
	 * 
	 * @param queryMethodName defined in daoImpl
	 * @param params
	 * @return List of Dto
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtos(final String queryMethodName, Map<String, Object> params){
		List<E> entityList = findEntitiesByMethod(queryMethodName, params);		
		return getDtoList(entityList);
		
	}
	
	/**
	 * 
	 * @param queryMethodName defined in daoImpl
	 * @param params
	 * @param currentConverter
	 * @return List of Dto
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtos(final String queryMethodName, Map<String, Object> params, ObjectConverter<E, D> currentConverter){
		List<E> entityList = findEntitiesByMethod(queryMethodName, params);		
		return getDtoList(entityList, currentConverter);
		
	}

	
	/**
	 * find list of entities
	 * @param where
	 * @param params
	 * @param orderBy
	 * @return List<E>
	 */	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<E> findEntities(final String where, Map<String, Object> params, String orderBy){
		List<E> entityList = new ArrayList<>();
		try{
			entityList =dao.find(where, params, orderBy);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return entityList;
	}
	/**
	 * 
	 * @param queryMethodName from Dao Impl
	 * @param params
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<E> findEntitiesByMethod(final String queryMethodName, Map<String, Object> params){
		String query = invokeQueryMethod(dao, queryMethodName);
		return findEntities(query,params);
	}


	
	/**
	 * 
	 * @param queryMethodName from Dao Impl
	 * @param params
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<E> findEntities(final String query, Map<String, Object> params){
		List<E> entityList = new ArrayList<>();
		try{
			entityList =dao.getEntities(query, params);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return entityList;
	}

	public D findOne(String whereClause, Map<String, Object> params, String orderBy){		
		D dto = null;
		try{
			E entity =dao.findOne(whereClause, params, orderBy);
			dto = getDto(entity);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return dto;
	}
	
	/**
	 * find single dto with given query
	 * @param queryString
	 * @param params
	 * @return
	 */
	public D findDtoByQuery(String query, Map<String, Object> params){		
		D dto = null;
		E entity = findEntity(query, params);
		dto = getDto(entity);
		return dto;
	}
	
	/**
	 * find single dto with given query
	 * @param queryString
	 * @param params
	 * @param currentConverter
	 * @return
	 */
	public D findDtoByQuery(final String query, Map<String, Object> params, ObjectConverter<E, D> currentConverter){		
		D dto = null;
		E entity = findEntity(query, params);
		dto = getDto(entity, currentConverter);
		return dto;
	}

	
	/**
	 * find single dto with given query
	 * @param queryMethodName in dao impl
	 * @param params
	 * @return
	 */
	public D findDto(final String queryMethodName, Map<String, Object> params){		
		D dto = null;
		E entity = findEntityByMethod(queryMethodName, params);
		dto = getDto(entity);
		return dto;
	}
	
	/**
	 * find single dto with given query
	 * @param queryMethodName in dao impl
	 * @param params
	 * @param currentConverter
	 * @return
	 */
	public D findDto(final String queryMethodName, Map<String, Object> params, ObjectConverter<E, D> currentConverter){		
		D dto = null;
		E entity = findEntity(queryMethodName, params);
		dto = getDto(entity, currentConverter);
		return dto;
	}

	
	/**
	 * Find single enity with given query
	 * @param queryMethodName from Dao Impl
	 * @param params
	 * @return
	 */
	public E findEntityByMethod(String queryMethodName, Map<String, Object> params){
		String query = invokeQueryMethod(dao, queryMethodName);
		return findEntity(query, params);
	}

	
	/**
	 * Find single enity with given query
	 * @param queryMethodName from Dao Impl
	 * @param params
	 * @return
	 */
	public E findEntity(String query, Map<String, Object> params){
		E entity = null;
		try{
			entity =dao.getSingleEntity(query, params);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return entity;
	}
	
	private ResponseDto getResponseWithErrorSingle(String errorMessage){
		ResponseDto response = new ResponseDto(true, errorMessage);
		return response;
	}
	
	/**
	 * Get Map object from key value pair
	 * @param key
	 * @param value
	 * @return Hashmap
	 */
	public static Map<String, Object> getParamMap(String key, Object value) {
		Map<String, Object> params= new HashMap<>();		
		params.put(key, value);
		return params;
	}
	
	public String invokeQueryMethod(Object obj, String methodName){
		Class<?> c = AopUtils.getTargetClass(obj);
		Advised advised = (Advised) obj;
		String result = null;
		try{
			Object target =  advised.getTargetSource().getTarget();
			Class<?> [] args = (Class[])null;
			Object []params = (Object[]) null;
			Method method =  c.getMethod(methodName, args);
			result = (String) method.invoke(target, params);
		}catch (Exception e) {
			log.error("Problem in invoking method", e);
		}
		return result;
	}
	
	/**
	 * Get Set of entity
	 * @param entitySet
	 * @param currentConverter
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public Set<D> getDtoSet(Set<E> entitySet, ObjectConverter<E, D> currentConverter){
		Set<D> dtoSet =  new HashSet<>();
		if (entitySet.isEmpty()){
			return dtoSet;
		}
		for(E entity: entitySet){
			D dto = getDto(entity,currentConverter);
			if(dto !=null){
				dtoSet.add(dto);
			}
		}
		return dtoSet;
		
	}
	
	/**
	 * get target Entity from Proxy Object
	 * @param entity
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	private E getTargetEntity(E entity){
		if(entity == null){
			return null;
		}
		
		E targetEntity = (E) ormUtil.deProxy(entity);
		return targetEntity;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateColumns(Map<String, Object> propertyValueMap,
			String idColumn,Long id){
		
		int updated= 0;
		try{
			updated = dao.updateColumns(propertyValueMap, idColumn, id);
		}catch (Exception e) {
			log.error("Error while update", e);
		}
		return updated;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn,Long id){
		
		int updated= 0;
		try{
			updated = dao.updateByJpql(propertyValueMap, idColumn, id);
		}catch (Exception e) {
			log.error("Error while update", e);
		}
		return updated;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateByJpql(Map<String, Object> propertyValueMap,String idColumn,Long id,String className){
		
		int updated= 0;
		try{
			updated = dao.updateByJpql(propertyValueMap, idColumn, id,className);
		}catch (Exception e) {
			log.error("Error while update", e);
		}
		return updated;
	}
	
	/**
	 * @param whereClause 
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return List of Dto
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtos(final String whereClause, Map<String, Object> params, int pageNumber, int pageSize, String orderBy){
		List<E> entityList = dao.find(whereClause, params, pageNumber, pageSize, orderBy);	
		return getDtoList(entityList);
		
	}
	/**
	 * @return Total records count in table
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public long getRecordCount(){
		return dao.getRecordCount();
	}
	
	/**
	 * @enter prama where condition
	 * @return Total records count in table with condition
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public long getRecordCount(String whereCOndition, Map<String, Object> params){
		return dao.getRecordCount(whereCOndition,params);
	}
	/**
	 * @enter prama where condition and entity name
	 * @return Total records count in table with condition
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public long getRecordCountByQuery(String Query, Map<String, Object> params){
		return dao.getRecordCountByQuery(Query,params);
	}
	/**
	 * @enter prama where condition and entity name
	 * @return Total records count in table with condition
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public long getRecordCount(String entity, String whereCOndition, Map<String, Object> params){
		return dao.getRecordCount(entity,whereCOndition,params);
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<D> findDtosByQuery(final String query, Map<String, Object> params, int pageNumber, int pageSize){
		List<E> entityList = new ArrayList<>();
		try{
			entityList =dao.getEntities(query, params, pageNumber, pageSize);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return getDtoList(entityList);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateByJpql(Map<String, Object> propertyValueMap,Map<String, Object> whereClauseParameters,String className){
		
		int updated= 0;
		try{
			updated = dao.updateByJpql(propertyValueMap, whereClauseParameters,className);
		}catch (Exception e) {
			log.error("Error while update", e);
		}
		return updated;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateByJpql(Map<String, Object> propertyValueMap,Map<String, Object> whereClauseParameters){
		
		int updated= 0;
		try{
			updated = dao.updateByJpql(propertyValueMap, whereClauseParameters);
		}catch (Exception e) {
			log.error("Error while update", e);
		}
		return updated;
	}
}

