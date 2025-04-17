package ra.edu.business.dao.admin;
import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.admin.Admin;
public interface AdminDAO extends AppDAO {
    boolean login(String username, String password);
}
