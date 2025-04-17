package ra.edu.presentation;

import ra.edu.business.dao.admin.AdminDAO;
import ra.edu.business.dao.student.StudentDAO;
import java.util.Scanner;
import ra.edu.business.dao.admin.AdminDAOImp;
import ra.edu.business.dao.student.StudentDAOImp;
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        display();
    }

    public static void display() {
        int choice;
        do {
            System.out.println("======== HỆ THỐNG QUẢN LÝ ĐÀO TẠO ========");
            System.out.println("1. Đăng nhập với tư cách Quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học viên");
            System.out.println("3. Thoát");
            System.out.println("==========================================");
            System.out.print("Nhập lựa chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> loginAdmin();
                case 2 -> loginStudent();
                case 3 -> System.out.println("Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 3);
    }

    private static void loginAdmin() {
        System.out.print("Tên đăng nhập: ");
        String username = sc.nextLine();
        System.out.print("Mật khẩu: ");
        String password = sc.nextLine();

        AdminDAO adminDAO = new AdminDAOImp();
        if (adminDAO.login(username, password)) {
            System.out.println("Đăng nhập thành công với tư cách Admin!");
            AdminMenu.menu();
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
        }
    }

    private static void loginStudent() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Mật khẩu: ");
        String password = sc.nextLine();

        StudentDAO studentDAO = new StudentDAOImp();
        if (studentDAO.login(email, password)) {
            System.out.println("Đăng nhập thành công với tư cách Học viên!");
            StudentMenu.menu();
        } else {
            System.out.println("Email hoặc mật khẩu không đúng.");
        }
    }
}
