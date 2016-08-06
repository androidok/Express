package com.express.database.dao;

import java.util.List;

public interface BaseDao<T> {

	public abstract void save(T entity);

	public abstract void delete(T entity);
	
	public abstract void deleteByEntityByMobile(String Mobile);

	public abstract List<T> getByParam(int first,String paramName,Object paramValue,String clazz);
	
	public abstract List<T> getByParam(String paramName,Object paramValue,String clazz);

	public abstract List<T> getByParams(String[] paramName,Object[] paramValue,String clazz);

	public abstract void update(T entity);
	
	public abstract void update(String tableName,String idName,String idValue,String paramName,
			Object paramValue);

	public abstract void update(String param,String idValue, String[] paramName,
			Object[] paramValue,String clazz);

	public abstract List<T> getAll();
	
	public abstract T getOne(T entity,String id);
		
}