package ra.edu.business.model.course;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private String Description;
    private int durationWeek;

    public Course() {
    }

    public Course(int courseId, String courseCode, String courseName, String description, int durationWeek) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        Description = description;
        this.durationWeek = durationWeek;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return Description;
    }

    public int getDurationWeek() {
        return durationWeek;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDurationWeek(int durationWeek) {
        this.durationWeek = durationWeek;
    }
}
