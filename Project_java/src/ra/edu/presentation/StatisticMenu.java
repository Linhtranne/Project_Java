package ra.edu.presentation;
import java.util.Scanner;

public class StatisticMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("\n--- Thống kê ---");
            System.out.println("1. Thống kê tổng số khóa học và học viên");
            System.out.println("2. Thống kê học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê khóa học có trên 10 học viên");
            System.out.println("5. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) return;

            System.out.println("Thực hiện chức năng " + choice + " (chưa cài đặt).");
        }
    }
}
