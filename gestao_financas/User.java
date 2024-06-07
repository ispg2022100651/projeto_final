import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String cc;
    private ArrayList<Account> accounts;

    public User(String name, String email, String password, String cc)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cc = cc;
        this.accounts = new ArrayList<>();
    }

    public User(User ref) {
        this(ref.name, ref.email, ref.password, ref.cc);
    }

    public User() {
        this("", "", "", "");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getCc()
    {
        return cc;
    }

    public void setIdCard(String cc) {
        this.cc = cc;
    }

    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User details:\n");
        builder.append("Name: ").append(this.name).append("\n");
        builder.append("Email: ").append(this.email).append("\n");
        builder.append("CC: ").append(this.cc).append("\n");
        
        // if (this.accounts.size() > 0) {
        //     builder.append("Accounts:\n");
        //     for (int i = 0; i < this.accounts.size(); i++) {
        //         builder.append("[").append(i + 1).append("] - Account Number: ").append(this.accounts.get(i)).append("\n");
        //     }
        // }
        
        return builder.toString();
    }
}