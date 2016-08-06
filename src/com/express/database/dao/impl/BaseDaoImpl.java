package com.express.database.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;








import com.express.database.dao.BaseDao;
import com.express.util.Constant;
import com.express.util.HibernateUtil;



/**
 * 数据库操作方法
 * @author LWK
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	/**
	 * 保存
	 */
	@Override
	public void save(T entity) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			session.save(entity);
			transaction.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}
	

	/**
	 * 通过对象删除实体
	 */
	@Override
	public void delete(T entity) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			session.delete(entity);
			transaction.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}
	
	/**
	 * 通过手机号码删除实体
	 */
	@Override
	public void deleteByEntityByMobile(String Mobile) {

		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			session.save(Mobile);
			transaction.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	/**
	 * 通过单个参数查询记录并分页
	 */
	public List<T> getByParam(int first,String paramName,Object paramValue,String clazz) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			hql="from "+clazz+" where "+paramName+"=?";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter(0, paramValue);
			query.setFirstResult(first);
			query.setMaxResults(Constant.PAGE);
			List<T> list=query.list();
			transaction.commit();
				return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}
	
	
	/**
	 * 通过单个参数查询记录
	 */
	@Override
	public List<T> getByParam(String paramName, Object paramValue, String clazz) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			hql="from "+clazz+" where "+paramName+"=?";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter(0, paramValue);
			List<T> list=query.list();
			transaction.commit();
				return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	/**
	 * 通过多个参数查询记录
	 */
	public List<T> getByParams(String[] paramName,Object[] paramValue,String clazz) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			for (int i = 0; i < paramName.length; i++) {
				hql = hql + paramName[i]+"=?";
			}
			hql="form "+clazz+"where"+hql;
			Query query=session.createQuery(hql);
			for (int j = 0; j < paramValue.length; j++) {
				query.setParameter(j, paramValue[j]);
			}
			List<T> list=query.list();
			transaction.commit();
				return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}
	
	

	/**
	 * 更新
	 */
	@Override
	public void update(T entity) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			session.update(entity);
			transaction.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	/**
	 * 更新一个指定的参数
	 */
	@Override
	public void update(String tableName, String idName, String idValue,String paramName, Object paramValue) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			String hql = "update " + tableName + " set "+paramName+"=? where "+idName+"=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, paramValue);
			query.setParameter(1, idValue);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}
	
	/**
	 * 更新多个指定的参数
	 */
	@Override
	public void update(String param,String idValue, String[] paramName, Object[] paramValue,String clazz) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			String hql = "update " + clazz + " set ";
			for (int i = 1; i < paramName.length; i++) {
				hql = hql + paramName[i] +  " = ? ";
				if(i == paramName.length-1 ){
					break;
				}
				hql = hql + " , ";
			}
			hql = hql + "where "+param+" = ?";
			Query query = session.createQuery(hql);
			for (int j = 1; j < paramValue.length; j++) {
				query.setParameter(j-1, paramValue[j]);
			}
			query.setParameter(paramName.length-1, idValue); 
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public T getOne(T entity,String id) {
		Transaction tx= null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			T t = (T) session.get(entity.getClass(), id);
			tx.commit();
			return t;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}



}