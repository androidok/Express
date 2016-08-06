package com.express.order.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.Order;
import com.express.order.service.OrderService;
import com.express.util.Constant;
import com.express.util.HibernateUtil;
import com.express.util.MD5Util;
import com.express.util.ObjectUtil;
import com.express.util.RandomUtil;

public class OrderServiceImpl implements OrderService{
	
	BaseDao<Order> dao = new BaseDaoImpl<Order>();

	/**
	 * 快递员查看可以接订单
	 */
	@Override
	public List<Order> rangeFindOrder(int first, Double receiveLongitude,Double receiveLatitude) {
		Transaction transaction=null;
		System.out.println(receiveLongitude);
		String hql="";
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			//orderStaus=1代表订单已支付
			hql="from Order where  sendLongitude BETWEEN ? and ? and sendLatitude BETWEEN ? and ? and orderStaus =1";
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter(0, receiveLongitude-0.05);
			query.setParameter(1, receiveLongitude+0.05);
			query.setParameter(2, receiveLatitude-0.05);
			query.setParameter(3, receiveLatitude+0.05);
			query.setFirstResult(first);
			query.setMaxResults(Constant.PAGE);
			List list=query.list();
			transaction.commit();
			System.out.println(list.size());
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
	 * 用户查看已下订单
	 */
	@Override
	public List<Order> findOrderById(int first,String Id) {
		return dao.getByParam(first,"userid", Id, Order.class.getSimpleName());
	}

	/**
	 * 用户创建订单
	 */
	@Override
	public void create(Order order) {
		Long time = System.currentTimeMillis();
		String orderId =RandomUtil.getRandomNum()+time.toString()+RandomUtil.getRandomNum();
		order.setOrderId(orderId);
		order.setOrderStaus(0);
		order.setRequestDate(new Date());
		dao.save(order);
	}

	/**
	 * 快递员抢单
	 * @throws NoSuchFieldException 
	 */
	@Override
	public boolean receive(Order order) throws NoSuchFieldException {
		Order order1 = (Order) dao.getByParam("orderId", order.getOrderId(), Order.class.getSimpleName());
		//1是订单状态表示可以接单
		if(order1.getOrderStaus()==1){
			order.setReceiveDate(new Date());
			order.setOrderStaus(2);
			String[] strings = ObjectUtil.getField(order);
			Object[] objects = ObjectUtil.getFieldValuesByName(strings, order);
			dao.update("orderId", order.getOrderId(),strings, objects,Order.class.getSimpleName());
			return true;
		}
		return false;
	}

}
