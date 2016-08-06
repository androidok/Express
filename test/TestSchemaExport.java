import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.express.model.Admin;
import com.express.user.service.UserServiceDAO;
import com.express.user.service.impl.UserServiceImpl;
import com.express.util.HibernateUtil;
import com.express.util.MD5Util;
import com.express.util.MsgUtil;


public class TestSchemaExport {

	@Test
	public void testSchemaExport()
	{
		Configuration configuration=new Configuration().configure();
		SchemaExport schemaExport=new SchemaExport(configuration);
		schemaExport.create(true, true);
		
		
	}
	
	@Test
	public void testSave()
	{
		Transaction transaction=null;
		
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		
		transaction = session.beginTransaction();
		
		Admin admin = new Admin();
		admin.setId(MD5Util.getUUID());
		admin.setUsername("admin");
		admin.setPassword(MD5Util.LoginEncryption("admin"));
		admin.setRole("admin");
		
		session.save(admin);
		
		transaction.commit();
		
	}
	
	@Test
	public void getMsg(){
		UserServiceDAO mes = new UserServiceImpl();
		System.out.println(MsgUtil.msg("15767978870"));
		
	}
}
