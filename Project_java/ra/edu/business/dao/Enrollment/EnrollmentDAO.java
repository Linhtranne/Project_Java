package ra.edu.business.dao.Enrollment;

import ra.edu.business.model.Enrollment.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    List<Enrollment> getStudentsByCourse(int courseId, int limit, int page);
    int countStudentsByCourse(int courseId);
    boolean approveEnrollment(int studentId, int courseId);
    boolean deleteEnrollment(int studentId, int courseId);
}