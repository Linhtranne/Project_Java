package ra.edu.business.service.Enrollment;


import java.util.Scanner;

public interface EnrollmentService {
    void displayStudentsByCourse(Scanner scanner);
    void approveEnrollment(Scanner scanner);
    void deleteEnrollment(Scanner scanner);
}