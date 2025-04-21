package ra.edu.business.dao.StudentManager;


import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Student.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {

    @Override
    public Student findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> listPagination(int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call list_students(?,?)}");
            callSt.setInt(1, limit);
            callSt.setInt(2, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public int totalCount() {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalStudents = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call total_students()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                totalStudents = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalStudents;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> findByNamePagination(String name, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_name_pagination(?,?,?)}");
            callSt.setString(1, name);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> sortByName(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_students_by_name(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> sortById(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_students_by_id(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setIdAccount(rs.getInt("id_account"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_student(?,?,?,?,?)}");
            callSt.setInt(1, student.getIdAccount());
            callSt.setString(2, student.getName());
            callSt.setDate(3, java.sql.Date.valueOf(student.getDob()));
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình thêm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thêm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_student(?,?,?,?,?)}");
            callSt.setInt(1, student.getIdAccount());
            callSt.setString(2, student.getName());
            callSt.setDate(3, java.sql.Date.valueOf(student.getDob()));
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình cập nhật: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình cập nhật: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_student(?)}");
            callSt.setInt(1, student.getIdAccount());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình xóa: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xóa: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}