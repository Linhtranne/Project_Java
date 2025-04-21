package ra.edu.business.service.StudentManager;
import ra.edu.business.dao.StudentManager.StudentDAOImp;
import ra.edu.business.model.Student.Student;
import ra.edu.business.model.Pagination;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImp implements StudentService {
    private final StudentDAOImp studentDAOImp;
    private final Pagination pagination;

    public StudentServiceImp() {
        studentDAOImp = new StudentDAOImp();
        pagination = new Pagination();
    }

    @Override
    public Student findByName(String name) {
        return studentDAOImp.findByName(name);
    }

    public List<Student> listStudents(String search) {
        if (search == null) {
            return studentDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
        } else {
            return studentDAOImp.findByNamePagination(search, pagination.getPagesize(), pagination.getCurrentpage());
        }
    }

    private void displayStudentList(Scanner scanner, String search, String sortBy, String sortOrder) {
        boolean exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalStudents = this.totalStudents();
        pagination.setTotalpages(totalStudents);
        do {
            List<Student> listPagination;
            if (sortBy == null) {
                listPagination = (search == null) ?
                        studentDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
                        studentDAOImp.findByNamePagination(search, pagination.getPagesize(), pagination.getCurrentpage());
            } else {
                listPagination = (sortBy.equals("NAME")) ?
                        studentDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
                        studentDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
            }

            if (!listPagination.isEmpty()) {
                System.out.printf("%-10s | %-25s | %-15s | %-10s | %-15s | %-15s\n",
                        "Mã HV", "Tên học viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Ngày tạo");
                listPagination.forEach(student -> {
                    System.out.printf("%-10d | %-25s | %-15s | %-10s | %-15s | %-15s\n",
                            student.getIdAccount(), student.getName(), student.getDob(),
                            student.isSex() ? "Nam" : "Nữ", student.getPhone() != null ? student.getPhone() : "N/A",
                            student.getCreateAt());
                });
                System.out.print("Trang: ");
                if (pagination.getCurrentpage() > 1) {
                    if (pagination.getCurrentpage() >= 3) System.out.print("... ");
                    System.out.print(pagination.getCurrentpage() - 1);
                }
                System.out.print("\u001B[33m" + "    " + pagination.getCurrentpage() + "     " + "\u001B[0m");
                if (pagination.getCurrentpage() < pagination.getTotalpages()) {
                    System.out.print(" " + (pagination.getCurrentpage() + 1));
                    if (pagination.getTotalpages() - pagination.getCurrentpage() >= 2) System.out.print(" ...");
                }
                System.out.println();
                if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
                if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
                System.out.println("1. Chọn trang");
                System.out.println("2. Thoát");
                System.out.print("Lựa chọn: ");

                char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        exit = true;
                        break;
                    case 'P':
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case 'N':
                        if (pagination.getCurrentpage() < pagination.getTotalpages())
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                }
            } else {
                System.out.println("Không có học viên nào.");
                break;
            }
        } while (!exit);
    }

    @Override
    public void listStudentsPagination(Scanner scanner) {
        displayStudentList(scanner, null, null, null);
    }

    @Override
    public int totalStudents() {
        return studentDAOImp.totalCount();
    }

    @Override
    public Student findStudentById(int id) {
        return studentDAOImp.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        return studentDAOImp.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDAOImp.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentDAOImp.delete(student);
    }

    public void findStudentByNamePagination(Scanner scanner, String search) {
        displayStudentList(scanner, search, null, null);
    }

    @Override
    public void sortByName(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayStudentList(scanner, null, "NAME", "asc");
                    exit = true;
                    break;
                case 2:
                    displayStudentList(scanner, null, "NAME", "desc");
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!exit);
    }

    @Override
    public void sortById(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayStudentList(scanner, null, "ID", "asc");
                    exit = true;
                    break;
                case 2:
                    displayStudentList(scanner, null, "ID", "desc");
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!exit);
    }
}