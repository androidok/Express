import com.express.database.dao.BaseDao;
import com.express.database.dao.impl.BaseDaoImpl;
import com.express.model.User;


public class Find {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class st = Class.forName("Student");
		Student x = (Student) st.newInstance();
		x.say();
//		BaseDao<User>dao = new BaseDaoImpl<User>();
//		User user = new User();
//		user = dao.getOne( user, "123456");
//		System.out.println(user.getId());

	}

}

class Student{
	int i ;
	int j ;
	public void say(){
		System.out.println("hello");
	}
}