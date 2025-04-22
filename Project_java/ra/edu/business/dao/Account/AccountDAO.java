package ra.edu.business.dao.Account;
import ra.edu.business.model.Account.Account;

public interface AccountDAO {
    Account login(String username, String password);

    boolean updatePassword(int accountId, String email, String newPassword);
    Account getAccountById(int id);

}
