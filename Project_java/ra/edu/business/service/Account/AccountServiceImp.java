package ra.edu.business.service.Account;

import ra.edu.business.dao.Account.AccountDAOImp;
import ra.edu.business.model.Account.Account;

public class AccountServiceImp implements AccountService {
    public AccountDAOImp accountDAOImp;
    public AccountServiceImp() {
        accountDAOImp = new AccountDAOImp();
    }
    
    @Override
    public Account login(String username, String password) {
        return accountDAOImp.login(username, password);
    }
}
