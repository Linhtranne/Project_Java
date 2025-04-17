package ra.edu.business.dao.course;
import java.sql.*;
import java.util.Scanner;
import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.course.Course;
public class CourseDAOImp implements CourseDAO {
    @Override
    public void displayPaginated(int pageSize) {
        int page = 1;
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            try (Connection conn = ConnectionDB.openConnection();
                 CallableStatement cs = conn.prepareCall("{CALL sp_get_paginated_courses(?, ?)}")) {
                cs.setInt(1, pageSize);
                cs.setInt(2, (page - 1) * pageSize);
                ResultSet rs = cs.executeQuery();

                System.out.println("ID | Tên | Thời lượng | Giảng viên | Ngày tạo");
                while (rs.next()) {
                    System.out.printf("%d | %s | %d | %s | %s\n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("duration"),
                            rs.getString("instructor"),
                            rs.getTimestamp("create_at"));
                }

                System.out.print("Nhập 'n' để trang tiếp, 'p' để quay lại, 'q' để thoát: ");
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("n")) page++;
                else if (choice.equalsIgnoreCase("p") && page > 1) page--;

            } catch (Exception e) {
                e.fillInStackTrace();
                break;
            }
        } while (!choice.equalsIgnoreCase("q"));
    }

    @Override
    public void insertCourse(Course course) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{CALL sp_insert_course(?, ?, ?)}")) {
            cs.setString(1, course.getName());
            cs.setInt(2, course.getDuration());
            cs.setString(3, course.getInstructor());
            cs.executeUpdate();
            System.out.println("Thêm khóa học thành công!");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{CALL sp_update_course(?, ?, ?, ?)}")) {
            cs.setInt(1, course.getId());
            cs.setString(2, course.getName());
            cs.setInt(3, course.getDuration());
            cs.setString(4, course.getInstructor());
            int rows = cs.executeUpdate();
            if (rows > 0)
                System.out.println("Cập nhật thành công!");
            else
                System.out.println("Không tìm thấy khóa học!");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
