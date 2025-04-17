package ra.edu.business.model.course;

import java.sql.Date;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;

    public Course(String name, int duration, String instructor) {
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Course(int id, String name, int duration, String instructor) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public String getInstructor() { return instructor; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
}