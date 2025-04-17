package ra.edu.presentation;
import java.util.Scanner;

public class EnrollmentManager {
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int choice;
        do {
            System.out.println("===== QUẢN LÝ ĐĂNG KÝ KHÓA HỌC =====");
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Thêm học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> System.out.println("Hiển thị học viên theo từng khóa học");
                case 2 -> System.out.println("Thêm học viên vào khóa học");
                case 3 -> System.out.println("Xóa học viên khỏi khóa học");
                case 4 -> System.out.println("Quay về menu chính");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 4);
    }
}
