package com.express.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IdentityCheck {	
	/**
	 * @param httpUrl
	 *            :请求接口
	 * @param identity
	 *            :身份证号
	 * @return 返回结果
	 */
	public static boolean Check(String identity){
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    String httpUrl = "http://apis.baidu.com/apistore/idservice/id?id="+identity;
	    System.out.println(httpUrl);
	    
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey", "d6987b4dfc9197869e8a20dc701703fe");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();

	        result = sbf.toString();
		       /* JSON返回示例 :
				{
					    "errNum": 0,
					    "retMsg": "success",
					    "retData": {
	     				 "sex": "M", //M-男，F-女，N-未知
	    				 "birthday": "1987-04-20", //出生日期
	     			     "address": "湖北省孝感市汉川市" //身份证归属地 市/县
	  				      }
			    }*/
	        JsonParser jP = new JsonParser();
	        JsonObject jobj=jP.parse(result).getAsJsonObject();
	        String errNum = jobj.get("errNum").getAsString();
//	        String needStr=jobj.getStrig("NeededString");
	        System.out.println("查询结果："+errNum);
	        if(errNum.equals("0")){
	        	return true;
	        }else {
				return false;
			}
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }

	}


	

}
