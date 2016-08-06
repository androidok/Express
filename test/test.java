import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.express.model.User;
import com.express.user.service.UserServiceDAO;
import com.express.user.service.impl.UserServiceImpl;
import com.express.util.IdentityCheck;
import com.express.util.MD5Util;


public class test {

	public static void main(String[] args) throws InterruptedException {
		
//		IdentityCheck ic = new IdentityCheck();
//		String id = "4416211993122025514";
//		if(ic.Check(id)){
//			System.out.println("身份证合法");
//		}else{
//			System.out.println("身份证不合法");
//		} 
		
//		String[] s =new String[5];
//		String aa="12345";
//		for(int i=0;i<aa.length();i++){
//		System.out.println(aa.charAt(i));
//		s[i]=aa.charAt(i)+"";
//		}
//		for(int i=0;i<aa.length();i++){
//		System.out.println(s[i]);
//		}
		
//		
//		int i = 0;
//		int q = 0;
//		boolean a = true;
//		while(a){
//			++i;
//			if(i%100==0){
//				++q;
//				System.out.println("计时："+q+":0");
//			}
//			System.out.println(q+":"+(i%100));
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}

		System.out.println(MD5Util.getMD5("15767979588"));
//		while(true)
//		{
//		System.out.println(new Date().hashCode());
//		}

		
		while(true)
		{
			Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyMMddHHmmss");
		System.out.println(simpleDateFormat.format(date));
//		Thread.sleep(1000);
		}
		
//		for(Integer i=1376797958;i<1376797958;i++)
//		{
//			
//			System.out.println(i.toString().hashCode());
//		}
	}

}
