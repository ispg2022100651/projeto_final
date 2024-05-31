package final_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Accounts //
        // Create accounts
        Account account1 = new Account(123456789, 1000.0);
        Account account2 = new Account(123465789, 2000.0);

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


        // Transaction //
        // Create a transaction
        Transaction tr = new Expense(120.00,"basebol",new Date(),"work");
        // Create Category (Transfer, deposit, pickup)
        Category category = new Category("Deposit");

        Account account12 = new Account(123456789, 1000.0);

        // Set the category with in the transaction
        tr.setCategory(category);

        // Calls a method from Account that calls a method from FileManager to execute the transaction
        account12.addTransaction(tr);

        tr.print();

        // Load account by account number
        Account loadedAccount2 = FileManager.loadAccount(account12.getAccountNumber());

        // Display loaded account
        if (loadedAccount2 != null) {
            loadedAccount2.print();
        } else {
            System.out.println("Failed to load account.");
        }


        // // User //
        // Create user and sync the account number by using the account method `addAccount`
        User user = new User("Daniel Adegas", "ispg2022102220@ispgaya.pt", "123", 111111111);

        Account acc = new Account(123123125, 1600);
        user.addAccount(acc);

        user.print();
        FileManager.saveUser(user);

        // Load the account from a user
        User user_loaded = FileManager.loadUser("ispg2022102220@ispgaya.pt", "123");

        // Account acc = new Account(123123125, 1600);
        // user_loaded.addAccount(acc);

        // System.out.println(user_loaded.getAccounts());
        // user_loaded.removeAccount(123123125);
        // user_loaded.print();

        System.out.println(user_loaded.getAccounts());
        user_loaded.print();
    }   
}
