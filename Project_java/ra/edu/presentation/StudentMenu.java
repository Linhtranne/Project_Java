package ra.edu.presentation;

import ra.edu.business.model.Account.Account;
import ra.edu.business.service.Account.AccountService;
import ra.edu.business.service.Account.AccountServiceImp;
import ra.edu.business.service.Course.CourseService;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.business.service.Student.StudentService;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class StudentMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CourseService courseService = new CourseServiceImp();
    private static final StudentService StudentService = new StudentServiceImp();
    private static final AccountService accountService = new AccountServiceImp();

    public static void main(String[] args) {
        boolean exit = false;
        do {
            System.out.println("================== MENU HỌC VIÊN =====================");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("========================================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    viewCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    viewRegisteredCourses();
                    break;
                case 4:
                    unregisterCourse();
                    break;
                case 5:
//                    updatePassword();
                    break;
                case 6:
                    exit = true;
                    Main.currentAccount = null;
                    System.out.println("Đăng xuất thành công!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 6!");
            }
        } while (!exit);
    }

    private static void viewCourses() {
        boolean exit = false;
        System.out.println("1. Xem tất cả khóa học");
        System.out.println("2. Tìm kiếm khóa học theo tên");
        System.out.println("3. Thoát");
        int choice = ValidatorChoice.validater(scanner);
        switch (choice) {
            case 1:
                courseService.listCoursesPagination(scanner);
                break;
            case 2:
                System.out.print("Nhập tên khóa học để tìm kiếm: ");
                String search = scanner.nextLine().trim();
                if (!search.isEmpty()) {
                    courseService.findByName(String.valueOf(scanner));
                } else {
                    System.out.println("Vui lòng nhập tên khóa học để tìm kiếm!");
                }
                break;
            case 3:
                exit = true;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 3!");
        }
    }

    private static void registerCourse() {
        Account currentAccount = Main.currentAccount;
        StudentService.registerCourse(scanner, currentAccount.getId());
    }

    private static void viewRegisteredCourses() {
        Account currentAccount = Main.currentAccount;
        StudentService.viewRegisteredCourses(scanner, currentAccount.getId());
    }

    private static void unregisterCourse() {
        Account currentAccount = Main.currentAccount;
        StudentService.unregisterCourse(scanner, currentAccount.getId());
    }

//    private static void updatePassword() {
//        Account currentAccount = Main.currentAccount;
//        if (currentAccount == null) {
//            System.out.println("Bạn cần đăng nhập để đổi mật khẩu!");
//            return;
//        }
//        System.out.print("Nhập mật khẩu mới (tối thiểu 6 ký tự): ");
//        String newPassword = scanner.nextLine().trim();
//        if (newPassword.length() < 6) {
//            System.out.println("Mật khẩu mới phải có ít nhất 6 ký tự!");
//            return;
//        }
//        System.out.print("Xác nhận mật khẩu mới: ");
//        String confirmPassword = scanner.nextLine().trim();
//        if (!newPassword.equals(confirmPassword)) {
//            System.out.println("Mật khẩu xác nhận không khớp!");
//            return;
//        }
//        if (accountService.updatePassword(scanner, Main.currentAccount.getId())) {
//            System.out.println("Đổi mật khẩu thành công!");
//        } else {
//            System.out.println("Đổi mật khẩu thất bại. Vui lòng thử lại!");
//        }
//    }
}