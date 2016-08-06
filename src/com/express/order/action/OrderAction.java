package com.express.order.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.Order;
import com.express.order.service.OrderService;
import com.express.order.service.impl.OrderServiceImpl;
import com.express.util.Constant;
import com.express.util.JsonUtil;
import com.express.util.MD5Util;
import com.express.util.ObjectUtil;
import com.express.util.SuperAction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class OrderAction extends SuperAction{
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	OrderService dao = new OrderServiceImpl();
	
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	
	
	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	/**
	 * 创建订单
	 * @param <T>
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public  void createOrder() throws IOException, InterruptedException{
		Order order = JsonUtil.jsonToBean(request.getParameter("order"), Order.class);
		String keepPath = ServletActionContext.getServletContext().getRealPath("/images/identity");
		File file=new File(keepPath);
		String path = "";
			if(!file.exists())
			{
				file.mkdir();
			}
			for (int i=0;i<upload.size();i++) {
				FileUtils.copyFile(upload.get(i), new File(file, order.getOrderId()+i+".jpg"));
			}
			for(int j=0;j<upload.size();j++){
				path = path+Constant.PATH_PREFIX+order.getOrderId()+j+".jpg";
				if (j==upload.size()-1) {
					break;
				}
				path = path+",";
			}
			order.setGoodsPic(path);
			dao.create(order);
			dataMap.put("status", "下单成功");
			JsonUtil.writeToResponse(dataMap);
			
	}
	
	
	/**
	 * 浏览可接订单 
	 * @throws IOException 
	 */
	public void browseOrder() throws IOException{
		Double receiveLongitude = Double.valueOf(request.getParameter("receiveLongitude"));
		Double receiveLatitude = Double.valueOf(request.getParameter("receiveLatitude"));
		int first = Integer.valueOf(request.getParameter("first"));
		System.out.println(receiveLongitude+"-------------"+receiveLatitude);
		List<Order> order = dao.rangeFindOrder(first,receiveLongitude, receiveLatitude);
		dataMap.put("order", JsonUtil.beanToJson(order));
		JsonUtil.writeToResponse(dataMap);	
	}
	
	
	/**
	 * 通过用户id查找所有订单
	 * @throws IOException
	 */
	public void findAllOrder() throws IOException{
		int first = Integer.valueOf(request.getParameter("first"));
		String userId = request.getParameter("userid");
		List<Order> order = dao.findOrderById(first, userId);
		dataMap.put("order", JsonUtil.beanToJson(order));
		JsonUtil.writeToResponse(dataMap);	
	}
	
	/**
	 * 快递员接单
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws NoSuchFieldException 
	 */
	public void receiveOrder() throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException {
		
		Order order = new Order();
		order.setCourierid(request.getParameter("courierid"));
		order.setOrderId( request.getParameter("orderId"));
		if(dao.receive(order)){
			dataMap.put("status", "接单成功");
			JsonUtil.writeToResponse(dataMap);
		}else{
			dataMap.put("status", "接单失败");
			JsonUtil.writeToResponse(dataMap);
		}
		
		}
		
		
	}
	
