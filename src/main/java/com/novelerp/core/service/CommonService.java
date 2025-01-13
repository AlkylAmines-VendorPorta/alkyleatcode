package com.novelerp.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;

import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;

/**
 * 
 * @author Vivek Birdi
 * @param <E> - Entity class, annotated with @javax.persistence.Entity
 * @param <D> - Data Transfer Object class, subclass of BaseDto 
 */
public interface CommonService <E extends Persistable, D extends BaseDto> {

	/**
	 * Get all the dto objects 
	 * @return List<D>
	 */
	public List<D> findAll();
	
	/**
	 * 
	 * @param currentConverter
	 * @return List of Dtos
	 */
	public List<D> findAll(ObjectConverter<E,D> currentConverter);
	
	/**
	 * persist dto in database
	 * @param dto
	 * @return true if success, false otherwise
	 */
	public boolean create(D dto);
	
	/**
	 * Save dto in database
	 * @param dto
	 * @return D
	 */
	public D save(D dto);
	
	/**
	 * Find dtos with matching condition
	 * @param whereClause
	 * @param params
	 * @param orderBy
	 * @return List<D> or empty list in case no matching record found. 
	 */
	public List<D> find(String whereClause, Map<String, Object> params, String orderBy);
	
	/**
	 * Find dtos with matching condition. Converts entities to dtos using supplied converter.
	 * @param where
	 * @param params
	 * @param orderBy
	 * @param currentConverter
	 * @return List<D> or empty list in case no matching record found.
	 */
	public List<D> find(final String where, Map<String, Object> params, String orderBy, ObjectConverter<E, D> currentConverter);
	
	/**
	 * Find single Dto with matching condition.
	 * @param whereClause
	 * @param params
	 * @param orderBy
	 * @return Dto or null in case of matching record found or any exception thrown.
	 */
	public D findOne(String whereClause, Map<String, Object> params, String orderBy);
	
	/**
	 * Find Dto with matching ID
	 * @param id
	 * @return D if found, null otherwise
	 */
	public D findDto(final Long id);

	/**
	 * Update dto in database
	 * @param dto
	 * @return true if successfully updated, false otherwise
	 */
	public boolean update(D dto);

	/**
	 * Update dto in database
	 * @param dto
	 * @return D Dto
	 */
	public D updateDto(D dto);

	
	/**
	 * Delete entity object from database
	 * @param entity
	 * @return true if successfully deleted, false otherwise.
	 */
	public boolean delete(E entity);
	
	/**
	 * delete record from database with given ID
	 * @param entityId
	 * @return true if successfully deleted, false otherwise.
	 */
	public boolean deleteById(final Long entityId);

	/**
	 * 
	 * @param queryMethodName defined in daoImpl
	 * @param params
	 * @return List of Dto
	 */
	public List<D> findDtos(final String queryMethodName, Map<String, Object> params);
	
	/**
	 * find single dto with given query
	 * @param queryMethodName in dao impl
	 * @param params
	 * @return
	 */
	public D findDto(String queryMethodName, Map<String, Object> params);
	
	/**
	 * get Dto Object
	 * @param entity
	 * @param currentConverter
	 * @return Dto object from entity object
	 */
	public D getDto(E entity, ObjectConverter<E, D> currentConverter);
	
	/**
	 * Get List of entity
	 * @param entityList
	 * @param currentConverter
	 * @return
	 */
	public  List<D> getDtoList(List<E> entityList, ObjectConverter<E, D> currentConverter);
	
	/**
	 * Get List of entity
	 * @param entityList
	 * @param currentConverter
	 * @return
	 */
	public  Set<D> getDtoSet(Set<E> entitySet, ObjectConverter<E, D> currentConverter);
	
	
	/**
	 * find single dto with given query
	 * @param queryMethodName in dao impl
	 * @param params
	 * @param currentConverter
	 * @return
	 */
	public D findDto(final String queryMethodName, Map<String, Object> params, ObjectConverter<E, D> currentConverter);


	/**
	 * 
	 * @param queryMethodName defined in daoImpl
	 * @param params
	 * @param currentConverter
	 * @return List of Dto
	 */
	public List<D> findDtos(final String queryMethodName, Map<String, Object> params, ObjectConverter<E, D> currentConverter);
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @return List of Dto
	 */
	public List<D> findDtosByQuery(final String query, Map<String, Object> params);
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @param currentConverter
	 * @return List of Dto
	 */
	public List<D> findDtosByQuery(final String query, Map<String, Object> params, ObjectConverter<E, D> currentConverter);
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public D findDtoByQuery(String query, Map<String, Object> params);
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @param currentConverter
	 * @return
	 */
	public D findDtoByQuery(final String query, Map<String, Object> params, ObjectConverter<E, D> currentConverter);

	/**
	 * To update selected columns in a row of the table.
	 * @param propertyValueMap - columns to be updated with respective values
	 * @param params
	 * @param idColumn
	 * @param id
	 * @return number of rows updated 
	 */

	public int updateColumns(Map<String, Object> propertyValueMap,String idColumn,Long id);
	
	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn,Long id);
	
	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn,Long id,String className);
	
	public List<D> findDtos(final String whereClause, Map<String, Object> params, int pageNumber, int pageSize, String orderBy);
	

	/**
	 * @return total count of record in table
	 */
	public long getRecordCount();
	
	/**
	 * @return total count of record in table with where condition and param
	 */
	public long getRecordCount(String whereCOndition, Map<String, Object> params);
	/**
	 * @return total count of record in table with entity name, where condition and param
	 */
	public long getRecordCount(String entity, String whereCOndition, Map<String, Object> params);
		
	public List<D> findDtosByQuery(final String query, Map<String, Object> params, int pageNumber, int pageSize);
	
	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters);
	
	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters, String className);

//	AdvanceShipmentNoticeDto saveasn(AdvanceShipmentNoticeDto asn, CommercialPOHeaderDto asn2);

//	List<SecurityPOItemDto> save(List<SecurityPOItemDto> asnLines, SecurityPOHeaderDto asn);



	
}
