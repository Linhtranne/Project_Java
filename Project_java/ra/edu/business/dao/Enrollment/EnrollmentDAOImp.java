package ra.edu.business.dao.Enrollment;
import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Enrollment.Enrollment;
import ra.edu.business.model.Enrollment.Eroll_status;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {

    @Override
    public List<Enrollment> getStudentsByCourse(int id, int limit, int page) {
        List<Enrollment> enrollments = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_students_by_course(?,?,?)}");
            callSt.setInt(1, id);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudent_id(rs.getInt("student_id"));
                e.setCourse_id(id);
                e.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());
                e.setStatus(Eroll_status.valueOf(rs.getString("status")));
                enrollments.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollments;
    }

    @Override
    public int countStudentsByCourse(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_students_by_course(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đếm sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public boolean approveEnrollment(int studentId, int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call approve_enrollment(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, id);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) {
                System.out.println(e.getMessage());
            } else {
                System.out.println("Lỗi khi duyệt đăng ký: " + e.getMessage());
            }
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean deleteEnrollment(int studentId, int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_enrollment(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, id);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) {
                System.out.println(e.getMessage());
            } else {
                System.out.println("Lỗi khi xóa đăng ký: " + e.getMessage());
            }
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}