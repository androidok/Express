
import java.util.Date;

import org.junit.Test;

import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.Order;
import com.express.model.User;
import com.express.util.MD5Util;
import com.express.util.RandomUtil;


public class TestInsert {

	@Test
	public void insert(){
		User user = new User();
		user.setMobile("123456");
		user.setLoginPassword(MD5Util.LoginEncryption("123"));
		user.setState("no");
		user.setPath("/Express/images/identity/157679795881.jpg");
		user.setPayPassword("123");
		user.setBalance(10000.00);
		BaseDao<User> dao = new BaseDaoImpl<User>();
		dao.save(user);
	}
	@Test
	public void insertOrder(){
		BaseDao<Order> dao = new BaseDaoImpl<Order>();
		Order order = new Order();
		order.setUserid("297eec7d562b301b01562b301c6a0000");
		order.setRequestDate(new Date());
		order.setSendName("张三");
		order.setSendAddress("惠州市 惠城区 X200(南岸路) 在西湖苑附近");
		order.setSendMobile("15767978870");
		order.setReceiveName("彬松");
		order.setReceiveAddress("西湖苑 哈哈哈哈");
		order.setReceiveLongitude(114.433988);
		order.setReceiveLatitude(23.044833);
		order.setGoodsDetail("哈哈哈哈");
		order.setOrderFare(3.33);
		order.setOrderStaus(1);
		order.setReceiveMobile("15767979588");;
		order.setGoodsPic("/Express/images/identity/157679795881.jpg");
		for (double i = 0.0 ; i < 20; i++) {
			Long time = System.currentTimeMillis();
			String orderId =RandomUtil.getRandomNum()+time.toString()+RandomUtil.getRandomNum();
			order.setOrderId(orderId);
			order.setSendLatitude(23.044855);
			order.setSendLongitude(114.433975);
			dao.save(order);
			
		}
	}

}
