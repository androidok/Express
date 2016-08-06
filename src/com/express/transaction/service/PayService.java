package com.express.transaction.service;

import java.util.List;

import com.express.model.User;

public interface PayService {
     public List<User> checkPwd(User user) throws NoSuchFieldException;
     
     public void balancePay(String userid,double balance); 
}
