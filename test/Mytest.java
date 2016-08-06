import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.express.model.User;



public class Mytest {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
public static void init(){
		
		//创建内置对象
				Configuration config = new Configuration().configure();
				//创建服务注册对象
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
				//创建会话工厂对象
				sessionFactory = config.buildSessionFactory(serviceRegistry);
				//会话对象
				session = sessionFactory.openSession();
				//开启事务
				transaction = session.beginTransaction();
				
		
	}
	
	public static void destory(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	public static void testSaveStudent(){
		
		User user = new User();
		session.save(user);//保存对象进入数据库
	}

}
