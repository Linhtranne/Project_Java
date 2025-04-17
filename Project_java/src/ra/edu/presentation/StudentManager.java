package ra.edu.presentation;

import java.util.Scanner;

public class StudentManager {
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int choice;
        do {
            System.out.println("====== QUẢN LÝ HỌC VIÊN ======");
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm kiếm theo tên, email hoặc id");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần)");
            System.out.println("7. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> System.out.println("Hiển thị danh sách học viên");
                case 2 -> System.out.println("Thêm mới học viên");
                case 3 -> System.out.println("Chỉnh sửa thông tin học viên");
                case 4 -> System.out.println("Xóa học viên");
                case 5 -> System.out.println("Tìm kiếm học viên");
                case 6 -> System.out.println("Sắp xếp học viên");
                case 7 -> System.out.println("Quay về menu chính");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 7);
    }
}