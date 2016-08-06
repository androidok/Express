import com.express.admin.service.AdminDAO;
import com.express.admin.service.impl.AdminDAOImpl;
import com.express.model.User;


public class update {
	public static void main(String[] args) {
		AdminDAO dao = new AdminDAOImpl();
		User user = new User();
		user.setIdentity("陈彬松");
		user.setId("297eec7d564f4a3201564f4a33f20000");
		dao.updateIdentity(user);
	}
}
