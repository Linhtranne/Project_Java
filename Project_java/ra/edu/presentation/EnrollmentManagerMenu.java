package ra.edu.presentation;

import ra.edu.business.service.Enrollment.EnrollmentService;
import ra.edu.business.service.Enrollment.EnrollmentServiceImp;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class EnrollmentManagerMenu {
    private static final EnrollmentService enrollmentService = new EnrollmentServiceImp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("================ Quản lý đăng ký khóa học ======================");
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Duyệt học viên đăng ký khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    enrollmentService.displayStudentsByCourse(scanner);
                    break;
                case 2:
                    enrollmentService.approveEnrollment(scanner);
                    break;
                case 3:
                    enrollmentService.deleteEnrollment(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1-4!");
            }
        } while (!exit);
    }
}