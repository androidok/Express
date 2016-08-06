package com.express.model;

import java.util.Date;
import java.util.Map;

public class Order {
//	private long id;
	private String orderId;//订单编号
	private String userid;//订单所属用户
	private Date requestDate;//下单时间
	private Date receiveDate;//快递员接单时间
	private Date finshDate;//订单完成时间
	private String sendName;//寄件人名字
	private double sendLongitude;//寄件人经度
	private double sendLatitude;//寄件人纬度
	private String sendAddress;//寄件人地址
	private String sendMobile;//寄件人手机
	private String receiveName;//收件人名字
	private double receiveLongitude;//收件人经度
	private double receiveLatitude;//收件人纬度
	private String receiveAddress;//收件人地址
	private String receiveMobile;//收件人手机
	private String goodsDetail;//物品描述
	private int goodsWeight;//物品重量
	private String goodsPic;//物品图片
	private int supportValue;//保价金
	private double orderFare;//订单价格
	private String orderRemark;//订单备注
	//0:未支付 1:未接单 2:已接单 3:已送达 -1:用户已取消 -2:快递员取消 -3:订单自动过期 -4:管理员关闭订单
	private int orderStaus;//订单状态(包括失效)        
	private String courierid;//接单快递员
//	private Map<String,User> users;//订单所属用户，包括寄件人和快递员


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public double getSendLongitude() {
		return sendLongitude;
	}
	public void setSendLongitude(double sendLongitude) {
		this.sendLongitude = sendLongitude;
	}
	public double getSendLatitude() {
		return sendLatitude;
	}
	public void setSendLatitude(double sendLatitude) {
		this.sendLatitude = sendLatitude;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getSendMobile() {
		return sendMobile;
	}
	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public double getReceiveLongitude() {
		return receiveLongitude;
	}
	public void setReceiveLongitude(double receiveLongitude) {
		this.receiveLongitude = receiveLongitude;
	}
	public double getReceiveLatitude() {
		return receiveLatitude;
	}
	public void setReceiveLatitude(double receiveLatitude) {
		this.receiveLatitude = receiveLatitude;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceiveMobile() {
		return receiveMobile;
	}
	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}
	public String getGoodsDetail() {
		return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	public int getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(int goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public String getGoodsPic() {
		return goodsPic;
	}
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}
	public int getSupportValue() {
		return supportValue;
	}
	public void setSupportValue(int supportValue) {
		this.supportValue = supportValue;
	}
	public double getOrderFare() {
		return orderFare;
	}
	public void setOrderFare(double orderFare) {
		this.orderFare = orderFare;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public int getOrderStaus() {
		return orderStaus;
	}
	public void setOrderStaus(int orderStaus) {
		this.orderStaus = orderStaus;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getFinshDate() {
		return finshDate;
	}
	public void setFinshDate(Date finshDate) {
		this.finshDate = finshDate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCourierid() {
		return courierid;
	}
	public void setCourierid(String courierid) {
		this.courierid = courierid;
	}

}
