package ra.edu.business.model.admin;
import java.time.LocalDate;
public class Admin {
    private int adminId;
    private String Username;
    private String Password;
    private String Fullname;
    private LocalDate createdAt;

    public Admin() {
    }

    public Admin(int adminId, String username, String password, String fullname, LocalDate createdAt) {
        this.adminId = adminId;
        this.Username = username;
        this.Password = password;
        this.Fullname = fullname;
        this.createdAt = createdAt;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullname() {
        return Fullname;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setFullname(String fullname) {
        this.Fullname = fullname;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}


