package ra.edu.business.model.Student;

import java.time.LocalDate;

public class Student {
    private int idAccount;
    private String name;
    private LocalDate dob;
    private boolean sex;
    private String phone;
    private LocalDate createAt;

    public Student() {}

    public Student(int idAccount, String name, LocalDate dob, boolean sex, String phone, LocalDate createAt) {
        this.idAccount = idAccount;
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.phone = phone;
        this.createAt = createAt;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}