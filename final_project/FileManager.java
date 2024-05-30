package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void saveAccounts(List<Account> accounts, String filename) {
        // Construct the full path
        String fullPath = "final_project/database/accounts/" + filename;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Account> loadAccounts(String filename) {
        // Construct the full path
        String fullPath = "final_project/database/accounts/" + filename;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                if (list.isEmpty() || list.get(0) instanceof Account) {
                    return new ArrayList<>((List<Account>) list);
                }
            }
            return new ArrayList<>(); // Return an empty list if type check fails
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
