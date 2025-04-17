package ra.edu.presentation;

import ra.edu.business.dao.course.CourseDAO;
import ra.edu.business.dao.course.CourseDAOImp;
import ra.edu.business.model.course.Course;
import ra.edu.validate.CourseValidator;

import java.util.Scanner;

public class CourseMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static final CourseDAO courseDAO = new CourseDAOImp();

    public static void menu() {
        int choice;
        do {
            System.out.println("======== QUẢN LÝ KHÓA HỌC ========");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Cập nhật thông tin khóa học");
            System.out.println("7. Quay về menu chính");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> handleDisplay();
                    case 2 -> handleInsert();
                    case 3 -> handleUpdate();
                    case 7 -> System.out.println("Quay về menu chính...");
                    default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên.");
                choice = -1;
            }
        } while (choice != 7);
    }

    private static void handleDisplay() {
        try {
            System.out.print("Nhập số dòng mỗi trang: ");
            int size = Integer.parseInt(sc.nextLine());
            courseDAO.displayPaginated(size);
        } catch (NumberFormatException e) {
            System.out.println(" Số dòng phải là số nguyên dương.");
        }
    }

    private static void handleInsert() {
        System.out.print("Tên khóa học: ");
        String name = sc.nextLine();
        System.out.print("Thời lượng (giờ): ");
        String durationStr = sc.nextLine();
        System.out.print("Giảng viên: ");
        String instructor = sc.nextLine();

        try {
            int duration = Integer.parseInt(durationStr);
            Course course = new Course(name, duration, instructor);

            if (CourseValidator.validateCourse(course)) {
                courseDAO.insertCourse(course);
            }
        } catch (NumberFormatException e) {
            System.out.println("Thời lượng phải là số nguyên.");
        }
    }

    private static void handleUpdate() {
        try {
            System.out.print("ID khóa học cần cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Tên mới: ");
            String name = sc.nextLine();
            System.out.print("Thời lượng mới: ");
            int duration = Integer.parseInt(sc.nextLine());
            System.out.print("Giảng viên mới: ");
            String instructor = sc.nextLine();

            Course course = new Course(id, name, duration, instructor);
            if (CourseValidator.validateCourse(course)) {
                courseDAO.updateCourse(course);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID và thời lượng phải là số nguyên.");
        }
    }
}
