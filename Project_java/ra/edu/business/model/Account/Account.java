package ra.edu.business.model.Account;

public class Account {
    private int id;
    private String email;
    private String password;
    private Std_status status;
    private Role role;

    public Account() {
        status = Std_status.ACTIVE;
    }

    public Account(int id, String email, String password, Std_status status, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Std_status getStatus() {
        return status;
    }

    public void setStatus(Std_status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


