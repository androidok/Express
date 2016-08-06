package com.express.util;

import java.io.File;

/**
 * @ClassName Constant
 * @Description ��ų����Ĺ�����
 * @author jackson
 * @date 2016��3��24������11:26:52
 */
public final class Constant {
	
	//日期格式
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDSS = "yyyyMMddss";
	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS_MMM = "HH:mm:ss.SSS";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_MMM = "yyyy-MM-dd HH:mm:ss.SSS";

	//分页
	public static final byte PAGE = 15;
	
	//充值明细状态为1，消费明细状态为2
	public static final String CHARGE_STATE = "1";
	public static final String CONSUME_STATE = "2";

	//默认地址状态为1，其他为0
	public static final String DEFAULT_ADDRESS = "1";
	public static final String OTHER_ADDRESS = "0";
	
    //应用的常量，用于加密
	public static final String WEBKEY = "EXPRESS";
	//cookie的有效期
	public static final long COOKIEMAXAGE = 3600*24*30;	
	//存储cookie的名称
	public static final String COOKIEDOMAINNAME = "express";
	
	//图片的类型
	public static final String IMAGE_TYPE = "jpg,png";
	
	
	//存放身份证图片的绝对路径
	public static String PATH_PREFIX ="/Express/images/identity/";
	//存放物品图片的绝对路径
	public static String pATH_GPIC="/Express/images/goodspic/";
	
	//信息模版
	public static String MSG="【叮咚云】您的验证码是：";
	
	//存放用户上传图片的位置
//	public static final String HEAD_IMAGE = Struts2Util.getRequest().getRealPath("/") + "user"+File.separator+"headImage";
	public static final String HEAD_IMAGE = "/user/headImage";
	//存放用户上传晒单图片的位置
//	public static final String HEAD_IMAGE = Struts2Util.getRequest().getRealPath("/") + "user"+File.separator+"headImage";
	public static final String SHOW_ORDER_IMAGE = "/user/showOrderImage";
	
	//后台上传商品缩略图片的位置
	//public static final String SUOLUETU =  ServletActionContext.getServletContext().getRealPath("houtai/sp_SuoLueTu");
	public static final String SUOLUETU = "/sp_SuoLueTu";
	
	//后台上传商品图文详情的位置
	public static final String IMAGEDETAIL = "/sp_ImageDetail";
	
	//后台上传广告图片的位置
	public static final String ADVERTISEMENT = "/advertisement";
	
	//推送的appkey
	public static final String APPKEY = "888b551904bee1a1f9f1cbbe";
	//推送的masterSecret
	public static final String MASTER_SECRET = "d138b92d8bc8404cdef92501";
	
	//短信接口的apikey       -------15767979588                                   
	public static final String MSGAPIKEY = "8c86a95a0c850d2ee2eaf851262fa5ad";
	//身份证验证接口的apikey
	public static final String IDAPIKEY = "d6987b4dfc9197869e8a20dc701703fe";
	
	//支付宝访问商户网站的网址后缀
	public static String IP = "http://localhost:8080";
	public static final String ALIPAY_NOTIFYURL_SUFFIX = "/yiyuan/p_alipayNotifyMessage";
	public static final String ALIPAY_RETURNURL_SUFFIX = "/yiyuan/p_alipayReturnMessage";
	
	public static final String ALICHARGE_NOTIFYURL_SUFFIX = "/yiyuan/p_aliChargeNotifyMessage";
	public static final String ALICHARGE_RETURNURL_SUFFIX = "/yiyuan/p_aliChargeReturnMessage";
	
}
