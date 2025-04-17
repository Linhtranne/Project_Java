package ra.edu.business.dao.admin;
import java.sql.*;
import ra.edu.business.config.ConnectionDB;
public class AdminDAOImp implements AdminDAO {

    @Override
    public boolean login(String username, String password) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_login_admin(?, ?)}")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.err.println("Lỗi đăng nhập admin: " + e.getMessage());
            return false;
        }
    }
}