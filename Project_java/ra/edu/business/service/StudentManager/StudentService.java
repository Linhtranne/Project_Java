package ra.edu.business.service.StudentManager;
import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Student.Student;

import java.util.Scanner;

public interface StudentService extends AppDAO<Student> {
    Student findByName(String name);
    int totalStudents();
    Student findStudentById(int id);
    void listStudentsPagination(Scanner scanner);
    void sortByName(Scanner scanner);
    void sortById(Scanner scanner);
    void findStudentByNamePagination(Scanner scanner, String search);
}