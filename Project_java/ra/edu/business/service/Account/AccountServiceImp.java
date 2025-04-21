package ra.edu.business.service.Account;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.Account.AccountDAOImp;
import ra.edu.business.model.Account.Account;
import java.sql.*;
public class AccountServiceImp implements AccountService {
    public AccountDAOImp accountDAOImp;
    public AccountServiceImp() {
        accountDAOImp = new AccountDAOImp();
    }
    
    @Override
    public Account login(String username, String password) {
        return accountDAOImp.login(username, password);
    }

    @Override
    public boolean updatePassword(int accountId, String newPassword) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_account_password(?,?)}");
            callSt.setInt(1, accountId);
            callSt.setString(2, newPassword);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi khi cập nhật mật khẩu: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

}
