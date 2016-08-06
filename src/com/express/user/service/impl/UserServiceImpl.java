package com.express.user.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.User;
import com.express.model.UserAdvice;
import com.express.model.UserTemp;
import com.express.user.service.UserServiceDAO;
import com.express.util.HibernateUtil;
import com.express.util.MD5Util;
import com.express.util.ObjectUtil;

public class UserServiceImpl implements UserServiceDAO {
	
	BaseDao<User> dao = new BaseDaoImpl<User>();
	BaseDao<UserTemp> dao1 = new BaseDaoImpl<UserTemp>();

	
    /**
     * 验证手机号是否已注册
     */
	@Override
	public List<User> findNumber(String mobile) {
				Transaction transaction=null;
				String hql="";
				try {
					Session session=HibernateUtil.getSessionFactory().getCurrentSession();
					hql="from User where mobile=?";
					transaction=session.beginTransaction();
					Query query=session.createQuery(hql);
					query.setParameter(0, mobile);
					List<User> list=query.list();
					transaction.commit();
					return list;
				} catch (Exception e) {
					e.printStackTrace();
				}finally
				{
					if(transaction!=null)
					{
						transaction=null;
					}
				}
				return null;
	}
	
	public boolean insertUser(User user) {
		
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();//提交事务
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
		
		
		
	}
	
	/**
	 * 将用户提交的身份信息保存到临时表,并修改认证状态
	 */
	@Override
	public void idAuth(UserTemp userTemp) {
		dao1.save(userTemp);
	}

	/**
	 * 通过手机查找用户
	 */
	@Override
	public User findUserByPhone(String mobile) {
		User user = null;
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			hql="from User where mobile=?";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter(0, mobile);
			user = (User) query.uniqueResult();
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到用户");
			return user;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	//完善用户信息，暂时没有用
	@Override
	public boolean updataUser(User user) {
		Transaction transaction=null;
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			Query query = session.createQuery("update User u set u.sex=?,u.id=? where u.mobile=?");
			query.setParameter(0, user.getMobile());
			query.executeUpdate();
//			session.update(user);
			transaction.commit();//提交事务
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	/**
	 * 通过手机号和密码查找用户
	 */
	@Override
	public boolean findUser(String mobile, String pwd) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			hql="from User where mobile=? and loginPassword=?";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter(0, mobile);
			query.setParameter(1, MD5Util.LoginEncryption(pwd));
//			query.setParameter(1, "7c4a8d09ca3762af61e59520943dc26494f8941b");
			List list=query.list();
			transaction.commit();//�ύ����
			System.out.println("查询的号码和密码:"+mobile+"------"+pwd+"------"+MD5Util.LoginEncryption(pwd));
			if(list.size()>0)
			{
				System.out.println("密码正确");
				return true;
			}
			else {
				System.out.println("密码错误");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	/**
	 * 修改密码
	 */
	@Override
	public void reLoginPwd(String mobile,String pwd) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			Query query = session.createQuery("update User u set u.loginPassword=? where u.mobile = ?");
			query.setParameter(0, MD5Util.LoginEncryption(pwd));
			query.setParameter(1, mobile);
			query.executeUpdate();
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
	 * 更换头像
	 */
	@Override
	public void changeIcon(User user) {
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			transaction=session.beginTransaction();
			Query query = session.createQuery("update User u set u.path=? where u.mobile = ?");
			query.setParameter(0, user.getPath());
			query.setParameter(1, user.getMobile());
			query.executeUpdate();
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

	@Override
	public void saveAdvice(UserAdvice entity) {
			BaseDao<UserAdvice> dao = new BaseDaoImpl<UserAdvice>();
			dao.save(entity);
	}

	@Override
	public List<User> checkUser(User user) throws NoSuchFieldException {
		String[] strings = ObjectUtil.getField(user);
		Object[] objects = ObjectUtil.getFieldValuesByName(strings, user);
		List<User> list = dao.getByParams(strings, objects, User.class.getSimpleName());
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public void rePayPwd(User user) {
		dao.update(User.class.getSimpleName(), "id",user.getId(),"payPassword", user.getPayPassword());
	}

	@Override
	public void rePayPwdByPhone(User user) {
		dao.update(User.class.getSimpleName(), "mobile",user.getMobile(),"payPassword", user.getPayPassword());
	}



}
