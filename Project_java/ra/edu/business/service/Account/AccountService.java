package ra.edu.business.service.Account;
import ra.edu.business.model.Account.Account;

import java.util.Scanner;

public interface AccountService {
    Account login(String username, String password);
//    boolean updatePassword(Scanner scanner, int accountId);

}
