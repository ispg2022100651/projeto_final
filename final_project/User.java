package final_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String password;
    private int cc;
    private ArrayList<Integer> account_number;

    public User() {
        this("", "", "", 0);
    }
    
    public User(String name, String email, String password, int cc) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cc = cc;
    }

    public void addAccount(Account acc) {
        this.account_number.add(acc.getAccountNumber());

        List<Account> accounts = new ArrayList<>();
        accounts.add(acc);

        FileManager.saveAccounts(accounts);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdCard() {
        return cc;
    }

    public void setIdCard(int cc) {
        this.cc = cc;
    }
}
