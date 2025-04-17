package ra.edu.presentation;


import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("======== MENU ADMIN ========");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Đăng xuất");
            System.out.print("Nhập lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CourseMenu.menu();
                    break;
                case 2:
                    StudentManager.menu();
                    break;
                case 3:
                    EnrollmentManager.menu();
                    break;
                case 4:
                    StatisticMenu.menu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
