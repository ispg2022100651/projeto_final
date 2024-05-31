package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            try {
                Account acc = loadAccount(account.getAccountNumber());
                boolean length = String.valueOf(account.getAccountNumber()).length() == 9;

                if (acc == null && length) {
                    int accountNumber = account.getAccountNumber();
                    // Construct the full path
                    String fullPath = "final_project/database/accounts/account_" + accountNumber;
        
                    //Update or create the account
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
                        oos.writeObject(account); // Serialize individual account, not the whole list
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new AccountAlreadyExistsException("Account with number " + account.getAccountNumber() + " already exists.");
                }
            } catch (AccountAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    public static Account loadAccount(int accountNumber) {
        // Construct the full path
        String fullPath = "final_project/database/accounts/account_"+accountNumber;

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

    public static void executeTransaction(Transaction transaction, Account account) {
        // Construct the full path
        String fullPath = "final_project/database/accounts/account_"+account.getAccountNumber();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            Object obj = ois.readObject();
            if (obj instanceof Account) {
                if (transaction instanceof Expense) {
                    account.setBalance( account.getBalance() - transaction.getAmount() );
                } else if (transaction instanceof Income) {
                    account.setBalance( account.getBalance() + transaction.getAmount() );
                }
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
                oos.writeObject(account); // Serialize individual account, not the whole list
                System.out.println(transaction.getAmount());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveUser(User user) {
        String fullPath = "final_project/database/users/user_" + user.getEmail().replaceAll("\\.pt$", "");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadUser(String email, String password) {
        String fullPath = "final_project/database/users/user_" + email.replaceAll("\\.pt$", "");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            User user = (User) ois.readObject();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("Invalid password.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class AccountAlreadyExistsException extends Exception {
        public AccountAlreadyExistsException(String message) {
            super(message);
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
