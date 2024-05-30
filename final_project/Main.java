package final_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create accounts
        Account account1 = new Account(12345, 1000.0);
        Account account2 = new Account(67890, 2000.0);

        // Create transactions
        FixedExpense fixedExpense = new FixedExpense(100, "Internet", new Date(), "Provider X", "Monthly", 30);
        FixedIncome fixedIncome = new FixedIncome(2000, "Salary", new Date(), "Company Y", "Monthly", 30);

        // Add transactions to accounts
        account1.addTransaction(fixedExpense);
        account1.addTransaction(fixedIncome);

        // Create a list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        // Save accounts to file in the "database" folder
        FileManager.saveAccounts(accounts);


        // Load account by account number
        Account loadedAccount = FileManager.loadAccount(account1.getAccountNumber());
        
        // Display loaded account
        if (loadedAccount != null) {
            loadedAccount.print();
        } else {
            System.out.println("Failed to load account.");
        }
    }   
}
