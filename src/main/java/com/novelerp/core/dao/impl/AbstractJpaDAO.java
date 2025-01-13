package com.novelerp.core.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.common.validation.CommonValidations;
import com.novelerp.core.dto.CommonDto;
import com.novelerp.core.entity.Persistable;

public abstract class AbstractJpaDAO<T extends Persistable, D extends CommonDto> {

	private Class<T> clazz;
	/* private Class<D> dtoClass; */

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println(entityManager);
	}

	public final void setClazz(final Class<T> clazzToSet, final Class<D> dtoClass) {
		this.clazz = clazzToSet;
		/* this.dtoClass = dtoClass; */
	}

	/**
	 * Get List<T>
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * @return List of entities
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> execute(String queryString, Map<String, Object> params, Class<T> resultClass) {
		Query query = getQuery(queryString, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> getEntities(String queryString, Map<String, Object> params) {
		Query query = getQuery(queryString, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public T getSingleEntity(String queryString, Map<String, Object> params) {
		Query query = getQuery(queryString, params);
		List<T> resultList = query.getResultList();
		if (CommonUtil.isListEmpty(resultList)) {
			return null;
		}
		return resultList.get(0);
	}

	/**
	 * Get Query
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	protected Query getQuery(String queryString, Map<String, Object> params) {
		if (CommonValidations.isEmpty(queryString)) {
			return null;
		}
		Query query = entityManager.createQuery(queryString, clazz);
		if (params == null) {
			return query;
		}
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			query.setParameter(key, value);
		}
		return query;
	}

	public T findOne(final int id) {
		return entityManager.find(clazz, id);
	}

	public T findOne(final Long id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy) {
		return entityManager
				.createQuery(
						"select c from " + clazz.getName() + " c " + (orderBy == null ? " " : " order by c." + orderBy))
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String whereClause, Map<String, Object> params, String orderBy) {
		if (whereClause == null) {
			return findAll(orderBy);
		}
		StringBuilder jpql = new StringBuilder("select c from " + clazz.getName());
		jpql.append(" c WHERE ").append(whereClause);
		Query query = entityManager.createQuery(jpql.toString());
		setQueryParams(query, params);
		return query.getResultList();
	}

	private void setQueryParams(Query query, Map<String, Object> params) {
		if (query == null || params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			query.setParameter(key, value);
		}
	}

	private void setPagination(Query query, int pageNumber, int pageSize) {
		if (query == null) {
			return;
		}
		if(pageNumber!=0 && pageSize!=0){
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
	}

	@SuppressWarnings("unchecked")
	public T findOne(String whereClause, Map<String, Object> params, String orderBy) {
		if (whereClause == null) {
			return null;
		}
		StringBuilder jpql = new StringBuilder("select c from " + clazz.getName());
		jpql.append(" c WHERE ").append(whereClause);
		Query query = entityManager.createQuery(jpql.toString());
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			query.setParameter(key, value);
		}
		List<T> resultList = query.getResultList();
		if (CommonUtil.isListEmpty(resultList)) {
			return null;
		}
		return resultList.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String where, String orderBy) {
		return entityManager.createQuery("select c from " + clazz.getName() + " c " + (where == null ? " " : where)
				+ (orderBy == null ? " " : " order by c." + orderBy)).getResultList();
	}

	public T create(final T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public List<T> create(final List<T> entity) {
		Integer batchSize = AppBaseConstant.BATCH_SIZE;
		for (int i = 0; i < entity.size(); i++) {
			entityManager.persist(entity.get(i));
			if (i % batchSize == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		return entity;
	}

	public T update(final T entity) {
		return entityManager.merge(entity);
	}

	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(final int entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int executeUpdate(String sqlQuery) {
		Query query = entityManager.createQuery(sqlQuery);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> executeQuery(String query) {
		Query qu = entityManager.createNativeQuery(query);
		return qu.getResultList();
	}

	public void deleteById(final Long entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	/**
	 * Get Map object from key value pair
	 * 
	 * @param key
	 * @param value
	 * @return Hashmap
	 */
	public Map<String, Object> getParamMap(String key, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(key, value);
		return params;
	}

	public int updateColumns(Map<String, Object> propertyValueMap, String idColumn, Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<T> updateCriteria = getUpdateCriteria(criteriaBuilder, propertyValueMap);
		Root<T> root = updateCriteria.from(clazz);
		updateCriteria.where(criteriaBuilder.equal(root.get(idColumn), id));
		return entityManager.createQuery(updateCriteria).executeUpdate();
	}

	public CriteriaUpdate<T> getUpdateCriteria(CriteriaBuilder criteriaBuilder, Map<String, Object> propertyValueMap) {
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		CriteriaUpdate<T> updateCriteria = criteriaBuilder.createCriteriaUpdate(clazz);
		updateCriteria.from(clazz);
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			updateCriteria.set(entry.getKey(), entry.getValue());
		}

		return updateCriteria;
	}

	private String getUpdateQuery(Map<String, Object> propertyValueMap, String idColumn, Long id, String className) {
		boolean first = true;
		StringBuilder query = new StringBuilder(" update " + className + " e ");
		query.append(" Set ");
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		while (it.hasNext()) {
			if (!first) {
				query.append(" , ");
			}

			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			query.append(" e." + entry.getKey() + "=:" + entry.getKey().replaceAll("[.]", "") + " ");
			first = false;
		}

		query.append(" WHERE e." + idColumn + "=:" + idColumn.replaceAll("[.]", "") + " ");

		return query.toString();
	}

	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn, Long id) {
		String query = getUpdateQuery(propertyValueMap, idColumn, id, clazz.getSimpleName());
		Query q = entityManager.createQuery(query);
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}
		q.setParameter(idColumn.replaceAll("[.]", ""), id);
		return q.executeUpdate();
	}

	public int updateByJpql(Map<String, Object> propertyValueMap, String idColumn, Long id, String className) {
		String query = getUpdateQuery(propertyValueMap, idColumn, id, className);
		Query q = entityManager.createQuery(query);
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}
		q.setParameter(idColumn.replaceAll("[.]", ""), id);
		return q.executeUpdate();
	}


	private String getUpdateQuery(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters, String className) {
		boolean first = true;
		StringBuilder query = new StringBuilder(" update " + className + " e ");
		query.append(" Set ");
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		while (it.hasNext()) {
			StringBuilder setPart = new StringBuilder();
			if (!first) {
				query.append(" , ");
			}

			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			query.append(" e." + entry.getKey() + "=:" + entry.getKey().replaceAll("[.]", "") + " ");
			first = false;
		}
		
		it = whereClauseParameters.entrySet().iterator();
		first=true;
		while (it.hasNext()) {
			if (!first) {
				query.append(" AND ");
			}else{
				query.append(" WHERE ");
			}

			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			query.append(" e." + entry.getKey() + "=:" + entry.getKey().replaceAll("[.]", "") + " ");
			first = false;
		}

		return query.toString();
	}
	
	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters) {
		String query = getUpdateQuery(propertyValueMap, whereClauseParameters, clazz.getSimpleName());
		Query q = entityManager.createQuery(query);
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}
		
		it = whereClauseParameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}
		
		return q.executeUpdate();
	}

	public int updateByJpql(Map<String, Object> propertyValueMap, Map<String, Object> whereClauseParameters, String className) {
		String query = getUpdateQuery(propertyValueMap, whereClauseParameters, className);
		Query q = entityManager.createQuery(query);
		Iterator<Entry<String, Object>> it = propertyValueMap.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}

		it = whereClauseParameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			q.setParameter(entry.getKey().replaceAll("[.]", ""), entry.getValue());
		}
		
		return q.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String whereClause, Map<String, Object> params, int pageNumber, int pageSize, String orderBy) {
		StringBuilder jpql = new StringBuilder("select c from " + clazz.getName() + " c " + (orderBy == null ? " " : " order by c." + orderBy));
		if (whereClause != null) {
			jpql.append(" WHERE ").append(whereClause);
		}
		Query query = entityManager.createQuery(jpql.toString());
		setPagination(query, pageNumber, pageSize);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> getEntities(String queryString, Map<String, Object> params, int pageNumber, int pageSize) {
		Query query = getQuery(queryString, params);
		setPagination(query, pageNumber, pageSize);
		return query.getResultList();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getRecordCount() {
		String jpql = "select COUNT(c) FROM " + clazz.getName() + " c ";
		Query query = entityManager.createQuery(jpql);
		return (long) query.getSingleResult();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public long getRecordCount(String where,Map<String, Object> params) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append("select COUNT(c) FROM " + clazz.getName() + " c");
		if (where != null) {
			jpql.append(" WHERE ").append(where);
		}
		Query query = entityManager.createQuery(jpql.toString());
		setQueryParams(query, params);
		return  (long) query.getSingleResult();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getRecordCount(String entity,String where,Map<String, Object> params) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append("select COUNT(c) FROM " + entity + " c");
		if (where != null) {
			jpql.append(" WHERE ").append(where);
		}
		Query query = entityManager.createQuery(jpql.toString());
		setQueryParams(query, params);
		return  (long) query.getSingleResult();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	
	public long getRecordCountByQuery(String Query,Map<String, Object> params) {
		StringBuilder jpql = new StringBuilder(Query); 
		
		Query query = entityManager.createQuery(jpql.toString());
		setQueryParams(query, params);
		return  (long) query.getSingleResult();
	}

	
}