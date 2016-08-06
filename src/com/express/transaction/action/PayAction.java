package com.express.transaction.action;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.Order;
import com.express.model.User;
import com.express.transaction.service.PayService;
import com.express.transaction.service.impl.PayServiceImpl;
import com.express.util.JsonUtil;
import com.express.util.SuperAction;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class PayAction extends SuperAction {
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	PayService dao = new PayServiceImpl();
	User user = new User();
	/**
	 * 支付订单
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void pay() throws NoSuchFieldException, JsonGenerationException, JsonMappingException, IOException{
			String payType = request.getParameter("payType");
			String orderId = request.getParameter("orderId");
			double amount = Double.valueOf(request.getParameter("amount")); 
			user.setId(request.getParameter("id"));
			user.setPayPassword(request.getParameter("payPassword"));
			//0表示余额支付
			if(payType != null && "0".equals(payType.trim())){
				User user1 = (User) dao.checkPwd(user);
				if(user1.toString().isEmpty()){
					if(user1.getBalance()>=amount){
						dao.balancePay(user1.getId(), user1.getBalance()-amount);
						dataMap.put("status", "支付成功");
						JsonUtil.writeToResponse(dataMap);
					}else{
						dataMap.put("status", "余额不足");
						JsonUtil.writeToResponse(dataMap);
					}
				}
			}
			
			//1表示支付宝支付
//			if(payType != null && "1".equals(payType.trim())){
//				Map<String, String> paramMap = payService.aliPay(user, productId, num);
//				if (paramMap != null) {
//					JsonUtil.writeToResponse(paramMap);
//				} else {
//					log.error("支付宝返回参数出错！");
//				}
//			}
	}
	

}
