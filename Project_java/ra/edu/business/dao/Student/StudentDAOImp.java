package ra.edu.business.dao.Student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Account.Std_status;
import ra.edu.business.model.Course.Course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {

    @Override
    public boolean registerCourse(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call register_course(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi khi đăng ký khóa học: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean unregisterCourse(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call unregister_course(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi khi hủy đăng ký khóa học: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<Course> getRegisteredCourses(int studentId, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_registered_courses(?,?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(Integer.parseInt(rs.getString("duration")));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("Có lỗi khi lấy danh sách khóa học đã đăng ký: " + e.getMessage());
            return courses;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public int countRegisteredCourses(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_registered_courses(?)}");
            callSt.setInt(1, studentId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi khi đếm khóa học đã đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }
}