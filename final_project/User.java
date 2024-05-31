package final_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String password;
    private String cc;
    private ArrayList<Integer> account_number;

    public User() {
        this("", "", "", "");
    }
    
    public User(String name, String email, String password, String cc) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cc = cc;
        this.account_number = new ArrayList<>();
    }

    public void addAccount(Account acc) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(acc);

        try {
            FileManager.saveAccounts(accounts);
            this.account_number.add(acc.getAccountNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getAccounts()
    {
        return this.account_number;
    }

    public void removeAccount(int acc_number) {
        boolean removed = this.account_number.remove(Integer.valueOf(acc_number));
        if (!removed) {
            System.out.println("Account number " + acc_number + " not found.");
        }
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

    public String getIdCard() {
        return cc;
    }

    public void setIdCard(String cc) {
        this.cc = cc;
    }

    public void print() {
        System.out.println("User details:");
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("CC: " + this.cc);
        if (this.account_number.size() > 0)
        {
            System.out.println("Accounts:");
            for (int i = 0; i < this.account_number.size(); i++) {
                System.out.println("[" + (i + 1) + "] - Account Number: " + this.account_number.get(i));
            }
        }
    }
}
