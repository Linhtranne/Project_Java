package ra.edu.business.model.Account;

public class Admin extends Account {
    public Admin() {
    }

    public Admin(int id, String email, String password, Std_status status, Role role) {
        super(id, email, password, status, role);
    }
}
