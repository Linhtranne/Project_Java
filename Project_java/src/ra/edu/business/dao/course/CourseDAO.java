package ra.edu.business.dao.course;

import ra.edu.business.model.course.Course;

public interface CourseDAO {
    void displayPaginated(int pageSize);
    void insertCourse(Course course);
    void updateCourse(Course course);

}
