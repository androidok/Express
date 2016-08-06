package com.express.admin.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.support.DaoSupport;

import com.express.admin.service.AdminDAO;
import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.Admin;
import com.express.model.Order;
import com.express.model.User;
import com.express.model.UserTemp;
import com.express.util.HibernateUtil;

public class AdminDAOImpl implements AdminDAO{
	BaseDao<User> dao1 =new BaseDaoImpl<User>();
	BaseDao<UserTemp> dao2 = new BaseDaoImpl<UserTemp>();
	BaseDao<Order> dao3 = new BaseDaoImpl<Order>();

	/**
	 * 管理员登录
	 */
	@Override
	public boolean adminLogin(Admin admin) {
		
				Transaction transaction=null;
				String hql="";
				try {
					Session session=HibernateUtil.getSessionFactory().getCurrentSession();
					hql="from Admin where username=? and password=?";
					transaction=session.beginTransaction();
					Query query=session.createQuery(hql);
					query.setParameter(0, admin.getUsername());
					query.setParameter(1, admin.getPassword());
					List<Admin> list=query.list();
					transaction.commit();
					if(list.size()>0)
					{
						return true;
					}
					else {
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
	 * 获取所有审核信息
	 */
	@Override
	public List<UserTemp> findAllUserIdentity() {
		
		List<UserTemp> list = null;
		Transaction transaction=null;
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			hql="from UserTemp";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			list=query.list();
			transaction.commit();
			if(list.size()>0)
			{
				return list;
			}
			else {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}finally
		{
			if(transaction!=null)
			{
				transaction=null;
			}
		}
	}

	@Override
	public boolean addAdmin(Admin admin) {
		Transaction transaction=null;
		
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			
			session.save(admin);
			
			transaction.commit();
			
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

	@Override
	public void updateIdentity(User user) {
		User user1 = new User();
		user1 = dao1.getOne(user, user.getId());
		user1.setIdentity(user.getIdentity());
		user1.setState("yes");
		dao1.update(user1);
	}

	@Override
	public void deleteRecord(UserTemp userTemp) {
		dao2.delete(userTemp);
	}

	@Override
	public Order findOrder(Order order) {
		return dao3.getOne(order, order.getOrderId());
	}

	@Override
	public void closeOrder(Order order) {
		Order order1 = new Order();
		order1 = dao3.getOne(order, order.getOrderId());
		//-4代表管理员关闭订单
		order1.setOrderStaus(-4);
		dao3.update(order1);
	}
	

}
