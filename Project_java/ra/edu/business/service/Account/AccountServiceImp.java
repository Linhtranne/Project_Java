package ra.edu.business.service.Account;

import ra.edu.business.dao.Account.AccountDAOImp;
import ra.edu.business.model.Account.Account;
import ra.edu.validate.Validator;
import ra.edu.business.dao.Account.AccountDAO;
import java.util.Scanner;

public class AccountServiceImp implements AccountService {
    public AccountDAOImp accountDAOImp;
    public AccountServiceImp() {
        accountDAOImp = new AccountDAOImp();
    }
    
    @Override
    public Account login(String username, String password) {
        return accountDAOImp.login(username, password);
    }

//    @Override
//    public boolean updatePassword(Scanner scanner, int accountId) {
//        System.out.print("Nhập email của bạn: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Nhập mật khẩu mới: ");
//        String newPassword = Validator.validateString(scanner, 6, 255, "Mật khẩu mới: ", "Mật khẩu");
//
//        if (AccountDAO.updatePassword(accountId, email, newPassword)) {
//            System.out.println("Cập nhật mật khẩu thành công!");
//        } else {
//            System.out.println("Cập nhật mật khẩu thất bại. Vui lòng kiểm tra email hoặc thử lại.");
//        }
//        return false;
//    }
}

