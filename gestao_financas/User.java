import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String cc;
    private ArrayList<Account> accounts;

    public User(String name, String email, String password, String cc) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cc = cc;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCc() {
        return cc;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
