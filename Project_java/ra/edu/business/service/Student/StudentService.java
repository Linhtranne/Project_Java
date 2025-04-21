package ra.edu.business.service.Student;
import java.util.Scanner;
public interface StudentService {
    void registerCourse(Scanner scanner, int studentId);
    void unregisterCourse(Scanner scanner, int studentId);
    void viewRegisteredCourses(Scanner scanner, int studentId);
}