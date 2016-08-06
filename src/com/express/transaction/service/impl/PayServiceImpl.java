package com.express.transaction.service.impl;

import java.util.List;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.User;
import com.express.transaction.service.PayService;
import com.express.user.service.UserServiceDAO;
import com.express.user.service.impl.UserServiceImpl;

public class PayServiceImpl implements PayService{

	UserServiceDAO dao = new UserServiceImpl();
	BaseDao dao1 = new BaseDaoImpl();
	
	@Override
	public List<User> checkPwd(User user) throws NoSuchFieldException{
		return dao.checkUser(user);
	}


	@Override
	public void balancePay(String userid, double balance) {
		dao1.update(User.class.getSimpleName(), "id",userid, "balance", balance);
	}

}
