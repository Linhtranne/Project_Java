package ra.edu.validate;

import ra.edu.business.model.Student.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StudentValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String validateName(Scanner scanner, String prompt) {
        String name;
        do {
            System.out.print(prompt);
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên không được để trống.");
            } else if (name.length() < 3 || name.length() > 100) {
                System.out.println("Tên phải có từ 3 đến 100 ký tự.");
            } else {
                break;
            }
        } while (true);
        return name;
    }

    public static LocalDate validateDob(Scanner scanner, String prompt) {
        LocalDate dob;
        do {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                dob = LocalDate.parse(input, DATE_FORMATTER);
                if (dob.isAfter(LocalDate.now())) {
                    System.out.println("Ngày sinh không được trong tương lai.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            }
        } while (true);
        return dob;
    }

    public static boolean validateSex(Scanner scanner, String prompt) {
        boolean sex;
        do {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                sex = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Giới tính phải là true (nam) hoặc false (nữ).");
            }
        } while (true);
        return sex;
    }

    public static String validatePhone(Scanner scanner, String prompt) {
        String phone;
        do {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
            if (!phone.isEmpty() && !phone.matches("\\d{10,11}")) {
                System.out.println("Số điện thoại phải có 10 hoặc 11 chữ số.");
            } else {
                break;
            }
        } while (true);
        return phone.isEmpty() ? null : phone;
    }

    public static int validateIdAccount(Scanner scanner, String prompt) {
        int id;
        do {
            System.out.print(prompt);
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id <= 0) {
                    System.out.println("Mã tài khoản phải là số nguyên dương.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Mã tài khoản phải là số nguyên.");
            }
        } while (true);
        return id;
    }
}