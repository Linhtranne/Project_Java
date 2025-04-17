package ra.edu.business.dao.student;

import ra.edu.business.model.course.Course;
import ra.edu.business.model.student.Student;
import java.util.List;

public interface StudentDAO {
    boolean login(String email, String password);
    List<Course> viewAllCourses();
    boolean registerCourse(int studentId, int courseId);
    List<Course> viewRegisteredCourses(int studentId);
    boolean cancelRegistration(int studentId, int courseId);
    boolean updatePassword(int studentId, String newPassword);
}
