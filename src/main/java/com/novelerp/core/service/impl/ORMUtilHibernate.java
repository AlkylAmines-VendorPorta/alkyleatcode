package com.novelerp.core.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentMap;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.novelerp.core.entity.PO;
import com.novelerp.core.service.ORMUtil;
@Service("ORM_UTIL_HIBERNATE")
public class ORMUtilHibernate implements ORMUtil{

	private Logger log =  LoggerFactory.getLogger(ORMUtilHibernate.class);
	public <T> T deepDeproxy(final Object maybeProxy) throws ClassCastException {
		if (maybeProxy == null)
			return null;
		T ret = deepDeproxy(maybeProxy, new HashSet<Object>());
		return ret;
	}

	@SuppressWarnings("unchecked")
	private <T> T deepDeproxy(final Object maybeProxy, final HashSet<Object> visited) throws ClassCastException {
		if (maybeProxy == null)
			return null;
		Class<?> clazz;
/*		Hibernate.initialize(maybeProxy);*/
		if (maybeProxy instanceof HibernateProxy) {
			return null;
		}else{
			clazz = maybeProxy.getClass();
		}
		T ret = (T) deepDeproxy(maybeProxy, clazz);
		if (visited.contains(ret)){
			return ret;
		}
		visited.add(ret);
		PropertyDescriptor [] descriptors = PropertyUtils.getPropertyDescriptors(ret);  
		for (PropertyDescriptor property : descriptors) {
			try {
				String name = property.getName();
				if (!"owner".equals(name) && property.getWriteMethod() != null) {
					Object value = PropertyUtils.getProperty(ret, name);
					boolean needToSetProperty = false;
					if (value instanceof HibernateProxy) {
						value = null;
						needToSetProperty = true;
					}else if (value instanceof PO) {
						value = deepDeproxy(value, visited);
						needToSetProperty = true;
					}
					else if (value instanceof Object[]) {
						Object[] valueArray = (Object[]) value;
						Object[] result = (Object[]) Array.newInstance(value.getClass(), valueArray.length);
						for (int i = 0; i < valueArray.length; i++) {
							result[i] = deepDeproxy(valueArray[i], visited);
						}
						value = result;
						needToSetProperty = true;
					} else if (value instanceof Set) {
						PersistentSet valueSet = (PersistentSet) value;
						if(!valueSet.wasInitialized()){
							value = null;
							PropertyUtils.setProperty(ret, name, value);
							continue;
						}
						Set<T> result = new HashSet<>();
						for (Object o : valueSet) {
							result.add(deepDeproxy(o, visited));
						}
						value = result;
						needToSetProperty = true;
					} else if (value instanceof Map) {
						PersistentMap valueMap = (PersistentMap) value;
						if(!valueMap.wasInitialized()){
							value = null;
							PropertyUtils.setProperty(ret, name, value);
							continue;
						}
/*						Map<?,?> valueMap = (Map<?,?>) value;*/
						Map<T,T> result = new HashMap<>();
						for (Object o : valueMap.keySet()) {
							result.put(deepDeproxy(o, visited), deepDeproxy(valueMap.get(o), visited));
						}
						value = result;
						needToSetProperty = true;
					}else if (value instanceof List) {
						PersistentCollection valueCollection =  (PersistentCollection) value;
						if(!valueCollection.wasInitialized()){
							value = null;
							PropertyUtils.setProperty(ret, name, value);
							continue;
						}
						List<?> valueList = (List<?>) value;
						List<T> result = new ArrayList<>(valueList.size());
						for (Object o : valueList) {
							result.add(deepDeproxy(o, visited));
						}
						value = result;
						needToSetProperty = true;
					}else if(value instanceof PersistentCollection ){
						PersistentCollection valueCollection =  (PersistentCollection) value;
						if(!valueCollection.wasInitialized()){
							value = null;
							continue;
						}
						
					}
					if (needToSetProperty)
						PropertyUtils.setProperty(ret, name, value);
				}
			} catch (java.lang.IllegalAccessException e) {
				log.error("Exception", e);
			} catch (InvocationTargetException e) {
				log.error("Exception", e);
			} catch (NoSuchMethodException e) {
				log.error("Exception", e);
			}
		}
		return ret;
	}

	private <T> T deepDeproxy(Object maybeProxy, Class<T> baseClass) throws ClassCastException {
		if (maybeProxy == null)
			return null;
		if (maybeProxy instanceof HibernateProxy) {
			return null;
			/*return baseClass.cast(((HibernateProxy) maybeProxy).getHibernateLazyInitializer().getImplementation());*/
		} else {
			return baseClass.cast(maybeProxy);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object deProxy(Object potentialProxy){
	
		if(potentialProxy == null){
			return null;
		}
		if(potentialProxy instanceof HibernateProxy){
			return null;
		}
		
		deProxy(potentialProxy, new HashSet<>());
		return potentialProxy;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T deProxy(Object potentialProxy, HashSet<T> visited){
		T obj = (T) potentialProxy;
		if(visited.contains(potentialProxy)){
			return (T) potentialProxy;
		}
		visited.add(obj);
		 parseProperties(potentialProxy,visited);
		 return (T)potentialProxy;
	}
	
/**
 * parse properties of entity	
 * @param potentialProxy
 */
	public <T> void parseProperties(Object entity, HashSet<T> visited){
		
		PropertyDescriptor [] descriptors = PropertyUtils.getPropertyDescriptors(entity); 
		for (PropertyDescriptor property : descriptors) {
			String name = property.getName();
			
			if (!"owner".equals(name) && property.getWriteMethod() != null) {
				try {
					Object value = PropertyUtils.getProperty(entity, name);
					if(value instanceof HibernateProxy){
						PropertyUtils.setProperty(entity, name, null);
						continue;
					}else if (value instanceof PO) {
						value = deProxy(value,visited);
						PropertyUtils.setProperty(entity, name, value);
						continue;
					}else if (value instanceof Object[]) {
						Object[] valueArray = (Object[]) value;
						Object[] result = (Object[]) Array.newInstance(value.getClass(), valueArray.length);
						for (int i = 0; i < valueArray.length; i++) {
							result[i] = deProxy(valueArray[i],visited);
						}
						PropertyUtils.setProperty(entity, name, result);
						continue;
					}else if (value instanceof Set) {
					
						if(value instanceof PersistentSet){
							PersistentSet valueSet = (PersistentSet) value;
							if(!valueSet.wasInitialized()){
								PropertyUtils.setProperty(entity, name, null);
								continue;
							}
						}
						Set<?> valueSet = (Set<?>) value;
						Set<T> result = new HashSet<>();
						for (Object o : valueSet) {
							result.add(deProxy(o,visited));
						}
						PropertyUtils.setProperty(entity, name, result);
						continue;
					} else if (value instanceof Map) {
						if(value instanceof PersistentMap){
							PersistentMap valueMap = (PersistentMap) value;
							if(!valueMap.wasInitialized()){
								PropertyUtils.setProperty(entity, name, null);
								continue;
							}
						}
						Map<?,?> valueMap = (Map<?,?>) value;
						Map<T,T> result = new HashMap<>();
						for (Object o : valueMap.keySet()) {
							result.put(deProxy(o,visited), deProxy(valueMap.get(o),visited));
						}
						PropertyUtils.setProperty(entity, name, result);
						continue;
					}else if (value instanceof List) {
						if(value  instanceof PersistentList){
							PersistentList valueCollection =  (PersistentList) value;
							if(!valueCollection.wasInitialized()){
								PropertyUtils.setProperty(entity, name, null);
								continue;
							}
						}
						List<?> valueList = (List<?>) value;
						List<T> result = new ArrayList<>(valueList.size());
						for (Object o : valueList) {
							result.add(deProxy(o,visited));
						}
						PropertyUtils.setProperty(entity, name, result);
						continue;
					}else if(value instanceof PersistentCollection ){
						PersistentCollection valueCollection =  (PersistentCollection) value;
						if(!valueCollection.wasInitialized()){
							PropertyUtils.setProperty(entity, name, null);
							continue;
						}
					}
					
				} catch (IllegalAccessException e) {
					log.error("Error", e);
				} catch (InvocationTargetException e) {
					log.error("Error", e);
				} catch (NoSuchMethodException e) {
					log.error("Error", e);
				}
			}

		}
	}
}
