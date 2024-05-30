package final_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create accounts
        Account account1 = new Account(12345, 1000.0);
        Account account2 = new Account(67890, 2000.0);


        // Create a list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);


        // Load accounts from file
        List<Account> loadedAccounts = FileManager.loadAccounts("accounts.ser");

        // Display loaded accounts
        if (loadedAccounts != null) {
            for (Account account : loadedAccounts) {
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Balance: " + account.getBalance());
                for (Transaction transaction : account.getTransactions()) {
                    System.out.println("Transaction: " + transaction.getDescription() + ", Amount: " + transaction.getAmount() + ", Date: " + transaction.getDate());
                }
            }
        }
    }   
}
