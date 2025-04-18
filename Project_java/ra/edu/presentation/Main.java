package ra.edu.presentation;

import ra.edu.business.model.Account.Role;
import ra.edu.business.model.Account.Account;
import ra.edu.business.service.Account.AccountServiceImp;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class Main {
    private static AccountServiceImp authServiceImp;
    private Main(){
        authServiceImp = new AccountServiceImp();
    }
    public static Account currentAccount = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main app = new Main();
        do {
            System.out.println("======== HỆ THỐNG QUẢN LÝ ĐÀO TẠO ========");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát");
            System.out.println("==========================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ vui, vui lòng nhập tử 1 - 3!");
            }
        }while (true);
    }
    public static void login(Scanner scanner){
        String email = Validator.validateEmail(scanner);
        String pass = Validator.validateString(scanner,0,255,"Nhập vào pasword: ", "Mật khẩu");
        currentAccount = authServiceImp.login(email,pass);
        if(currentAccount != null){
            if (currentAccount.getRole().equals(Role.ADMIN)){
                System.out.println("Đăng nhập thành công với tư cách Admin!");
                AdminMenu.main(null);
            }else {
                System.out.println("Đăng nhập thành công với tư cách Học viên!");
                StudentMenu.main(null);
            }
        }else {
            System.out.println("Đăng nhập thất bại");
        }
    }

}
