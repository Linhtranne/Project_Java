package ra.edu.validate;
import ra.edu.business.model.course.Course;
public class CourseValidator {
    public static boolean validateCourse(Course course) {
        if (Validator.isNullOrEmpty(course.getName())) {
            System.out.println("Tên khóa học không được để trống.");
            return false;
        }

        if (course.getDuration() <= 0) {
            System.out.println(" Thời lượng phải là số nguyên dương.");
            return false;
        }

        if (Validator.isNullOrEmpty(course.getInstructor())) {
            System.out.println("Tên giảng viên không được để trống.");
            return false;
        }

        return true;
    }
}
