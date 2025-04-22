package ra.edu.business.service.Enrollment;


import ra.edu.business.dao.Account.AccountDAO;
import ra.edu.business.dao.Account.AccountDAOImp;
import ra.edu.business.dao.Course.CourseDAO;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.dao.Enrollment.EnrollmentDAO;
import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
import ra.edu.business.model.Account.Account;
import ra.edu.business.model.Course.Course;
import ra.edu.business.model.Enrollment.Enrollment;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class EnrollmentServiceImp implements EnrollmentService {
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
    private final CourseDAO courseDAO = new CourseDAOImp();
    private final AccountDAO accountDAO = new AccountDAOImp();
    private static final int PAGE_SIZE = 5;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void displayStudentsByCourse(Scanner scanner) {
        List<Course> courses = courseDAO.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào!");
            return;
        }

        System.out.println("\nDanh sách khóa học:");
        printCourseTableHeader();
        for (Course c : courses) {
            System.out.printf("| %-5d | %-30s | %-10s |%n",
                    c.getId(), truncate(c.getName(), 30), c.getStatus());
        }
        printTableFooter(3);

        System.out.print("Nhập ID khóa học: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID khóa học không hợp lệ!");
            return;
        }

        int totalStudents = enrollmentDAO.countStudentsByCourse(courseId);
        if (totalStudents == 0) {
            System.out.println("Không có sinh viên nào đăng ký khóa học này!");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalStudents / PAGE_SIZE);
        int currentPage = 1;

        while (true) {
            System.out.println("\nDanh sách sinh viên đăng ký khóa học (Trang " + currentPage + "/" + totalPages + "):");
            List<Enrollment> enrollments = enrollmentDAO.getStudentsByCourse(courseId, PAGE_SIZE, currentPage);
            if (enrollments.isEmpty()) {
                System.out.println("Không có dữ liệu!");
                break;
            }

            printStudentTableHeader();
            for (Enrollment e : enrollments) {
                Account account = accountDAO.getAccountById(e.getStudent_id());
                String email = account != null ? account.getEmail() : "Không tìm thấy";
                String formattedDate = e.getRegistered_at() != null ?
                        e.getRegistered_at().format(DATE_FORMATTER) : "N/A";
                System.out.printf("| %-10d | %-10d | %-30s | %-10s | %-19s |%n",
                        e.getId(), e.getStudent_id(), truncate(email, 30),
                        e.getStatus(), formattedDate);
            }
            printTableFooter(5);

            System.out.print("Nhập trang (1-" + totalPages + ") hoặc 0 để thoát: ");
            try {
                int page = Integer.parseInt(scanner.nextLine());
                if (page == 0) break;
                if (page >= 1 && page <= totalPages) {
                    currentPage = page;
                } else {
                    System.out.println("Trang không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số trang hợp lệ!");
            }
        }
    }

    @Override
    public void approveEnrollment(Scanner scanner) {
        System.out.print("Nhập ID sinh viên: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID sinh viên không hợp lệ!");
            return;
        }

        System.out.print("Nhập ID khóa học: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID khóa học không hợp lệ!");
            return;
        }

        if (enrollmentDAO.approveEnrollment(studentId, courseId)) {
            System.out.println("Duyệt đăng ký thành công!");
        } else {
            System.out.println("Duyệt đăng ký thất bại!");
        }
    }

    @Override
    public void deleteEnrollment(Scanner scanner) {
        System.out.print("Nhập ID sinh viên: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID sinh viên không hợp lệ!");
            return;
        }

        System.out.print("Nhập ID khóa học: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID khóa học không hợp lệ!");
            return;
        }

        if (enrollmentDAO.deleteEnrollment(studentId, courseId)) {
            System.out.println("Xóa đăng ký thành công!");
        } else {
            System.out.println("Xóa đăng ký thất bại!");
        }
    }

    private void printCourseTableHeader() {
        System.out.println("+" + "-".repeat(7) + "+" + "-".repeat(32) + "+" + "-".repeat(12) + "+");
        System.out.println("| ID    | Tên khóa học                     | Trạng thái |");
        System.out.println("+" + "-".repeat(7) + "+" + "-".repeat(32) + "+" + "-".repeat(12) + "+");
    }

    private void printStudentTableHeader() {
        System.out.println("+" + "-".repeat(12) + "+" + "-".repeat(12) + "+" + "-".repeat(32) + "+" + "-".repeat(12) + "+" + "-".repeat(21) + "+");
        System.out.println("| ID Đăng ký | ID Sinh viên | Email                          | Trạng thái | Ngày đăng ký        |");
        System.out.println("+" + "-".repeat(12) + "+" + "-".repeat(12) + "+" + "-".repeat(32) + "+" + "-".repeat(12) + "+" + "-".repeat(21) + "+");
    }

    private void printTableFooter(int columns) {
        StringBuilder footer = new StringBuilder("+");
        if (columns == 3) {
            footer.append("-".repeat(7)).append("+").append("-".repeat(32)).append("+").append("-".repeat(12)).append("+");
        } else {
            footer.append("-".repeat(12)).append("+").append("-".repeat(12)).append("+")
                    .append("-".repeat(32)).append("+").append("-".repeat(12)).append("+").append("-".repeat(21)).append("+");
        }
        System.out.println(footer);
    }

    private String truncate(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength - 3) + "..." : str;
    }
}