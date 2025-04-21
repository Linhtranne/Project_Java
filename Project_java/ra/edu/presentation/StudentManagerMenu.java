package ra.edu.presentation;

import ra.edu.business.model.Student.Student;
import ra.edu.business.service.StudentManager.StudentService;
import ra.edu.business.service.StudentManager.StudentServiceImp;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class StudentManagerMenu {
    private static final StudentService studentService = new StudentServiceImp();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        do {
            System.out.println("================== Quản lý học viên ==================");
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm kiếm học viên");
            System.out.println("6. Sắp xếp học viên");
            System.out.println("7. Quay về menu chính");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    studentService.listStudentsPagination(scanner);
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    sortStudents();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!");
            }
        } while (!exit);
    }

    private static void addNewStudent() {
        Student student = new Student();
        student.setIdAccount(StudentValidator.validateIdAccount(scanner, "Nhập mã học sinh: "));
        student.setName(StudentValidator.validateName(scanner, "Nhập tên học viên: "));
        student.setDob(StudentValidator.validateDob(scanner, "Nhập ngày sinh (yyyy-MM-dd): "));
        student.setSex(StudentValidator.validateSex(scanner, "Nhập giới tính (true - Nam, false - Nữ): "));
        student.setPhone(StudentValidator.validatePhone(scanner, "Nhập số điện thoại (nhấn Enter để bỏ qua): "));
        if (studentService.save(student)) {
            System.out.println("Thêm học viên thành công!");
        } else {
            System.out.println("Thêm học viên thất bại. Vui lòng kiểm tra lại mã học sinh hoặc kết nối cơ sở dữ liệu.");
        }
    }
    private static void updateStudent() {
        System.out.print("Nhập mã học sinh của học viên cần chỉnh sửa: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Mã học sinh không hợp lệ!");
            return;
        }
        Student student = studentService.findStudentById(id);
        if (student == null) {
            System.out.println("Không tìm thấy học viên với mã học sinh: " + id);
            return;
        }
        System.out.println("Thông tin hiện tại của học viên:");
        System.out.printf("Mã HV: %d | Tên: %s | Ngày sinh: %s | Giới tính: %s | Số điện thoại: %s | Ngày tạo: %s\n",
                student.getIdAccount(), student.getName(), student.getDob(),
                student.isSex() ? "Nam" : "Nữ", student.getPhone() != null ? student.getPhone() : "N/A",
                student.getCreateAt());

        boolean exit = false;
        do {
            System.out.println("Chọn trường cần chỉnh sửa:");
            System.out.println("1. Tên học viên");
            System.out.println("2. Ngày sinh");
            System.out.println("3. Giới tính");
            System.out.println("4. Số điện thoại");
            System.out.println("5. Hoàn tất chỉnh sửa");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    student.setName(StudentValidator.validateName(scanner, "Nhập tên mới: "));
                    System.out.println("Tên đã được cập nhật.");
                    break;
                case 2:
                    student.setDob(StudentValidator.validateDob(scanner, "Nhập ngày sinh mới (yyyy-MM-dd): "));
                    System.out.println("Ngày sinh đã được cập nhật.");
                    break;
                case 3:
                    student.setSex(StudentValidator.validateSex(scanner, "Nhập giới tính mới (true - Nam, false - Nữ): "));
                    System.out.println("Giới tính đã được cập nhật.");
                    break;
                case 4:
                    student.setPhone(StudentValidator.validatePhone(scanner, "Nhập số điện thoại mới (nhấn Enter để bỏ qua): "));
                    System.out.println("Số điện thoại đã được cập nhật.");
                    break;
                case 5:
                    if (studentService.update(student)) {
                        System.out.println("Cập nhật thông tin học viên thành công!");
                    } else {
                        System.out.println("Cập nhật thông tin học viên thất bại.");
                    }
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 5!");
            }
            if (!exit) {
                System.out.println("Thông tin học viên sau khi chỉnh sửa tạm thời:");
                System.out.printf("Mã HV: %d | Tên: %s | Ngày sinh: %s | Giới tính: %s | Số điện thoại: %s\n",
                        student.getIdAccount(), student.getName(), student.getDob(),
                        student.isSex() ? "Nam" : "Nữ", student.getPhone() != null ? student.getPhone() : "N/A");
            }
        } while (!exit);
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã học sinh của học viên cần xóa: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Mã học sinh không hợp lệ!");
            return;
        }
        Student student = studentService.findStudentById(id);
        if (student == null) {
            System.out.println("Không tìm thấy học viên với mã học sinh: " + id);
            return;
        }
        System.out.printf("Bạn có chắc chắn muốn xóa học viên: %s (Mã: %d)? (Y/N): ", student.getName(), student.getIdAccount());
        if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            if (studentService.delete(student)) {
                System.out.println("Xóa học viên thành công!");
            } else {
                System.out.println("Xóa học viên thất bại.");
            }
        } else {
            System.out.println("Hủy xóa học viên.");
        }
    }

    private static void searchStudent() {
        System.out.println("Tìm kiếm học viên theo tên:");
        String search = scanner.nextLine().trim();
        if (!search.isEmpty()) {
            studentService.findStudentByNamePagination(scanner, search);
        } else {
            System.out.println("Vui lòng nhập tên để tìm kiếm!");
        }
    }

    private static void sortStudents() {
        boolean exit = false;
        do {
            System.out.println("Sắp xếp học viên:");
            System.out.println("1. Theo tên (Tăng dần/Giảm dần)");
            System.out.println("2. Theo mã học sinh (Tăng dần/Giảm dần)");
            System.out.println("3. Thoát");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    studentService.sortByName(scanner);
                    break;
                case 2:
                    studentService.sortById(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 3!");
            }
        } while (!exit);
    }
}