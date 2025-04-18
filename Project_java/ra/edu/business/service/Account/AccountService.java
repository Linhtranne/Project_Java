package ra.edu.business.service.Account;
import ra.edu.business.model.Account.Account;

public interface AccountService {
    Account login(String username, String password);

}
