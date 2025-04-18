package ra.edu.business.dao.Account;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Account.Role;
import ra.edu.business.model.Account.Account;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImp implements AccountDAO {
    @Override
    public Account login(String Accountname, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Account Account = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call login_by_account(?,?,?)}");
            callSt.setString(1, Accountname);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, java.sql.Types.INTEGER);
            callSt.execute();
            int returnCode = callSt.getInt(3);
            if (returnCode == 1) {
                ResultSet rs = callSt.getResultSet();
                if (rs.next()) {
                    Account = new Account();
                    Account.setId(rs.getInt("id"));
                    Account.setEmail(rs.getString("email"));
                    Account.setRole(Role.valueOf(rs.getString("role")));
                    return Account;
                }
            }else if (returnCode == 2) {
                System.out.println("Tài khoản đã bị xóa!");
            }else if (returnCode == 0) {
                System.out.println("Tài khoản hoặc mật khẩu không đúng");
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return Account;
    }


}
