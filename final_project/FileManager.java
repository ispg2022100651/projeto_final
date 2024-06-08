package final_project;

import java.io.*;
import java.util.List;

public class FileManager
{ 
    public static boolean saveAccount(Account account) {
        try {
            boolean length = String.valueOf(account.getAccountNumber()).length() == 9;
            if (length) {
                int accountNumber = account.getAccountNumber();
                String fullPath = "final_project/database/accounts/account_" + accountNumber;

                // Verifica se o arquivo já existe antes de tentar carregá-lo
                File file = new File(fullPath);
                if (!file.exists()) {
                    // Cria o arquivo se ele não existir
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
                        oos.writeObject(account);
                        System.out.println("Account created and saved.");
                        return true; // Indicate success
                    } // Não há necessidade de catch aqui, pois a exceção será tratada no bloco externo
                } else {
                    throw new AccountAlreadyExistsException("Account with number " + accountNumber + " already exists.");
                }
            } else {
                System.err.println("Account number " + account.getAccountNumber() + " does not have 9 digits.");
                return false; // Indicate failure
            }
        } catch (IOException | AccountAlreadyExistsException e) {
            System.err.println("Failed to create and save account: " + e.getMessage());
            return false; // Indicate failure
        }
    }

    public static void saveAccounts(List<Account> accounts) {
        boolean allAccountsSaved = true; // Flag para verificar se todas as contas foram salvas com sucesso
    
        for (Account account : accounts) {
            try {
                boolean length = String.valueOf(account.getAccountNumber()).length() == 9;
                if (length) {
                    int accountNumber = account.getAccountNumber();
                    String fullPath = "final_project/database/accounts/account_" + accountNumber;
    
                    // Verifica se o arquivo já existe antes de tentar carregá-lo
                    File file = new File(fullPath);
                    if (!file.exists()) {
                        // Cria o arquivo se ele não existir
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
                            oos.writeObject(account);
                        } catch (IOException e) {
                            System.err.println("Error saving account with number " + accountNumber);
                            e.printStackTrace();
                            allAccountsSaved = false; // Define a flag como false se ocorrer um erro
                        }
                    } else {
                        throw new AccountAlreadyExistsException("Account with number " + accountNumber + " already exists.");
                    }
                } else {
                    System.err.println("Account number " + account.getAccountNumber() + " does not have 9 digits.");
                    allAccountsSaved = false; // Define a flag como false se ocorrer um erro
                }
            } catch (AccountAlreadyExistsException e) {
                System.err.println(e.getMessage());
                allAccountsSaved = false; // Define a flag como false se ocorrer um erro
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
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load account. Account does not exist.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
        String fullPath = "final_project/database/users/user_" + user.getName();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadUser(String email, String password) {
        File userDir = new File("final_project/database/users");
        
        if (userDir.exists() && userDir.isDirectory()) {
            File[] userFiles = userDir.listFiles();
            if (userFiles != null) {
                for (File userFile : userFiles) {
                    if (userFile.isFile()) {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                            User user = (User) ois.readObject();
                            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                                return user;
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void loadUsersFromFile(List<User> users) {
        File userDir = new File("final_project/database/users");

        if (userDir.exists() && userDir.isDirectory()) {
            File[] userFiles = userDir.listFiles();
            if (userFiles != null) {
                for (File userFile : userFiles) {
                    if (userFile.isFile()) {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                            User user = (User) ois.readObject();
                            users.add(user);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class AccountAlreadyExistsException extends Exception {
        public AccountAlreadyExistsException(String message) {
            super(message);
        }
    }
}
