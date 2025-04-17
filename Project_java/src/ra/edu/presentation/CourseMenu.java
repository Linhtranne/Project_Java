package ra.edu.presentation;

import ra.edu.business.dao.course.CourseDAO;
import ra.edu.business.dao.course.CourseDAOImp;
import ra.edu.business.model.course.Course;

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
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập số dòng mỗi trang: ");
                    int size = Integer.parseInt(sc.nextLine());
                    courseDAO.displayPaginated(size);
                }
                case 2 -> {
                    System.out.print("Tên khóa học: ");
                    String name = sc.nextLine();
                    System.out.print("Thời lượng (giờ): ");
                    int duration = Integer.parseInt(sc.nextLine());
                    System.out.print("Giảng viên: ");
                    String instructor = sc.nextLine();
                    courseDAO.insertCourse(new Course(name, duration, instructor));
                }
                case 3 -> {
                    System.out.print("ID khóa học cần cập nhật: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Tên mới: ");
                    String name = sc.nextLine();
                    System.out.print("Thời lượng mới: ");
                    int duration = Integer.parseInt(sc.nextLine());
                    System.out.print("Giảng viên mới: ");
                    String instructor = sc.nextLine();
                    courseDAO.updateCourse(new Course(id, name, duration, instructor));
                }
            }
        } while (choice != 7);
    }
}
