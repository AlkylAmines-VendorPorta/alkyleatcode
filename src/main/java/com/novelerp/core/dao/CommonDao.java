package com.novelerp.core.dao;

import java.util.List;
import java.util.Map;

import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;

public interface CommonDao<T extends Persistable, D extends BaseDto> {

	public static int PAGE_SIZE = 10;

	public T findOne(final int id);

	public T findOne(final Long id);

	public List<T> findAll();

	public List<T> findAll(String orderBy);

	public List<T> findAll(String whereClause, String orderBy);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final int entityId);

	public T create(T entity);

	public List<T> create(List<T> entity);

	public int executeUpdate(String sqlQuery);

	public List<Object[]> executeQuery(String sqlQuery);

	public void deleteById(final Long entityId);

	/**
	 * 
	 * @param whereClause
	 *            e.g e.code := code?
	 * @param params
	 * @param orderBy
	 * @return List of entities with matching criteria
	 */
	public List<T> find(String whereClause, Map<String, Object> params, String orderBy);

	/**
	 * 
	 * @param @param
	 *            whereClause e.g e.code := code?
	 * @param params
	 * @param orderBy
	 * @return Single entity with matching criteria, null in case whereClause is
	 *         null or throws exception in case query return more than one row
	 */
	public T findOne(String whereClause, Map<String, Object> params, String orderBy);

	/**
	 * Get Single matching entity
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * @return first entity or null;
	 */
	public T getSingleEntity(String queryString, Map<String, Object> params);

	/**
	 * Get all entities
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * @return List of entities
	 */
	public List<T> getEntities(String queryString, Map<String, Object> params);

	/**
	 * To update selected columns in a row of the table.
	 * 
	 * @param propertyValueMap
	 *            - columns to be updated with respective values
	 * @param params
	 * @param idColumn
	 * @param id
	 * @return number of rows updated
	 */
	public int updateColumns(Map<String, Object> propertyValueMap, String idColumn, Long id);

	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn, Long id);

	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn, Long id, String className);

	/**
	 * 
	 * @param whereClause
	 *            e.g e.code := code?
	 * @param pageNumber
	 * @param pageSize
	 * @return List of entities with matching criteria
	 */
	public List<T> find(String whereClause, Map<String, Object> params, int pageNumber, int pageSize, String orderBy);

	/**
	 * @return total count of records in table
	 */
	public long getRecordCount();

	/**
	 * @return total count of records in table with where condition and params
	 */
	public long getRecordCount(String whereClause, Map<String, Object> params);

	/**
	 * @return total count of records in table with where condition and params
	 */
	public long getRecordCount(String entity, String whereClause, Map<String, Object> params);

	/**
	 * Get all entities
	 * 
	 * @param queryString
	 * @param params
	 * @param page
	 *            number
	 * @param page
	 *            Size
	 * @return List of entities
	 */
	public List<T> getEntities(String queryString, Map<String, Object> params, int pageNumber, int pageSize);

	
	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters);
	
	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters, String className);

	public long getRecordCountByQuery(String query, Map<String, Object> params);


}