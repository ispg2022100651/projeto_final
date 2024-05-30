package final_project;

import java.io.*;
import java.util.List;

public class FileManager {

    public static void saveAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            Account acc = loadAccount(account.getAccountNumber());
            boolean length = String.valueOf(account.getAccountNumber()).length() != 9;

            if (acc == null && length) {
                int accountNumber = account.getAccountNumber();
                
                // Construct the full path
                String fullPath = "final_project/database/accounts/accounts_" + accountNumber;
    
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
                    oos.writeObject(account); // Serialize individual account, not the whole list
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("There are already an account with this number!");
            }
        }
    }

    public static Account loadAccount(int accountNumber) {
        // Construct the full path
        String fullPath = "final_project/database/accounts/accounts_"+accountNumber;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            Object obj = ois.readObject();
            if (obj instanceof Account) {
                return (Account) obj;
            }
            return null; // Return null if the object is not an Account
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
        

    // public static void saveAccounts(List<Account> accounts, String filename) {
    //     for (Account account : accounts) {
    //         int account_number = account.getAccountNumber();

    //         // Construct the full path
    //         String fullPath = "final_project/database/accounts/" + filename + "_" + account_number;
    
    //         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
    //             oos.writeObject(accounts);
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }

    // }

    // @SuppressWarnings("unchecked")
    // public static List<Account> loadAccounts(String filename) {
    //     // Construct the full path
    //     String fullPath = "final_project/database/accounts/" + filename;

    //     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
    //         Object obj = ois.readObject();
    //         if (obj instanceof List<?>) {
    //             List<?> list = (List<?>) obj;
    //             if (list.isEmpty() || list.get(0) instanceof Account) {
    //                 return new ArrayList<>((List<Account>) list);
    //             }
    //         }
    //         return new ArrayList<>(); // Return an empty list if type check fails
    //     } catch (IOException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //         return new ArrayList<>();
    //     }
    // }
}
