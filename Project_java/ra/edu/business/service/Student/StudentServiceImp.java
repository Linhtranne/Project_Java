package ra.edu.business.service.Student;


import ra.edu.business.dao.Student.StudentDAO;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.model.Course.Course;
import ra.edu.business.model.Pagination;
import ra.edu.business.service.Course.CourseService;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImp implements StudentService {
    private final StudentDAO StudentDAO;
    private final CourseService courseService;
    private final Pagination pagination;

    public StudentServiceImp() {
        StudentDAO = new StudentDAOImp();
        courseService = new CourseServiceImp();
        pagination = new Pagination();
    }

    @Override
    public void registerCourse(Scanner scanner, int studentId) {
        System.out.print("Nhập ID khóa học để đăng ký: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID khóa học không hợp lệ!");
            return;
        }
        Course course = courseService.findCourseById(courseId);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
            return;
        }
//        if (!course.getStatus().equals("INACTIVE")) {
//            System.out.println("Khóa học không ở trạng thái mở để đăng ký!");
//            return;
//        }
        if (StudentDAO.registerCourse(studentId, courseId)) {
            System.out.println("Đăng ký khóa học thành công! Trạng thái: WAITING");
        } else {
            System.out.println("Đăng ký khóa học thất bại. Có thể bạn đã đăng ký khóa học này hoặc có lỗi hệ thống.");
        }
    }

    @Override
    public void unregisterCourse(Scanner scanner, int studentId) {
        System.out.print("Nhập ID khóa học để hủy đăng ký: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID khóa học không hợp lệ!");
            return;
        }
        if (StudentDAO.unregisterCourse(studentId, courseId)) {
            System.out.println("Hủy đăng ký khóa học thành công! Trạng thái: CANCEL");
        } else {
            System.out.println("Hủy đăng ký thất bại. Có thể bạn chưa đăng ký khóa học này hoặc trạng thái không cho phép hủy.");
        }
    }

    @Override
    public void viewRegisteredCourses(Scanner scanner, int studentId) {
        boolean exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourses = StudentDAO.countRegisteredCourses(studentId);
        pagination.setTotalpages(totalCourses);
        do {
            List<Course> courses = StudentDAO.getRegisteredCourses(studentId, pagination.getPagesize(), pagination.getCurrentpage());
            if (courses.isEmpty()) {
                System.out.println("Bạn chưa đăng ký khóa học nào hoặc không có khóa học ở trạng thái WAITING/CONFIRM.");
                break;
            }
            System.out.printf("%-10s | %-25s | %-15s | %-15s |\n",
                    "ID", "Tên khóa học", "Trạng thái khóa học", "Trạng thái đăng ký");
            courses.forEach(course -> {
                System.out.printf("%-10d | %-25s | %-15s | %-15s |\n",
                        course.getId(), course.getName()
                        , course.getStatus(), course.getStudentStatus());
            });
            System.out.print("Trang: ");
            if (pagination.getCurrentpage() > 1) {
                if (pagination.getCurrentpage() >= 3) System.out.print("... ");
                System.out.print(pagination.getCurrentpage() - 1);
            }
            System.out.print("\u001B[33m" + "    " + pagination.getCurrentpage() + "     " + "\u001B[0m");
            if (pagination.getCurrentpage() < pagination.getTotalpages()) {
                System.out.print(" " + (pagination.getCurrentpage() + 1));
                if (pagination.getTotalpages() - pagination.getCurrentpage() >= 2) System.out.print(" ...");
            }
            System.out.println();
            if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
            if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
            System.out.println("1. Chọn trang");
            System.out.println("2. Thoát");
            System.out.print("Lựa chọn: ");

            char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (choice) {
                case '1':
                    int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                    pagination.setCurrentpage(page);
                    break;
                case '2':
                    exit = true;
                    break;
                case 'P':
                    if (pagination.getCurrentpage() > 1)
                        pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                    break;
                case 'N':
                    if (pagination.getCurrentpage() < pagination.getTotalpages())
                        pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!exit);
    }
}