package ra.edu.business.dao.student;
import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.course.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public boolean login(String email, String password) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_login_student(?, ?)}")) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.err.println("Lỗi đăng nhập học viên: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<Course> viewAllCourses() {
        return List.of();
    }

    @Override
    public boolean registerCourse(int studentId, int courseId) {
        return false;
    }

    @Override
    public List<Course> viewRegisteredCourses(int studentId) {
        return List.of();
    }

    @Override
    public boolean cancelRegistration(int studentId, int courseId) {
        return false;
    }

    @Override
    public boolean updatePassword(int studentId, String newPassword) {
        return false;
    }
}
