package ra.edu.business.dao.Student;
import ra.edu.business.model.Course.Course;

import java.util.List;

public interface StudentDAO {
    boolean registerCourse(int studentId, int courseId);
    boolean unregisterCourse(int studentId, int courseId);
    List<ra.edu.business.model.Course.Course> getRegisteredCourses(int studentId, int limit, int page);
    int countRegisteredCourses(int studentId);
}