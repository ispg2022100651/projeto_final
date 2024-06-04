import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String USER_FILE = "users.dat";
    private static final String ACCOUNT_FILE = "accounts.dat";

    public static void saveUsers(ArrayList<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        }
    }

    public static ArrayList<User> loadUsers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (ArrayList<User>) ois.readObject();
        }
    }

    public static void saveAccounts(ArrayList<Account> accounts) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        }
    }

    public static ArrayList<Account> loadAccounts() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            return (ArrayList<Account>) ois.readObject();
        }
    }
}
