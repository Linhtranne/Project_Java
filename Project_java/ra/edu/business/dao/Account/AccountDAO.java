package ra.edu.business.dao.Account;
import ra.edu.business.model.Account.Account;

public interface AccountDAO {
    Account login(String username, String password);
}
