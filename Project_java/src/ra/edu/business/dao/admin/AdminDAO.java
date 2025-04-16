package ra.edu.business.dao.admin;
import ra.edu.business.dao.AppDAO;
public interface AdminDAO extends AppDAO {
    int login(String username, String password);
    void logout();
}
