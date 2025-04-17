package ra.edu.presentation;

import java.util.Scanner;

public class StudentMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("\n======== MENU HỌC VIÊN ========");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký (nếu chưa bắt đầu)");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("===============================");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) return;

            System.out.println("Thực hiện chức năng " + choice + " (chưa cài đặt).");
        }
    }
}
