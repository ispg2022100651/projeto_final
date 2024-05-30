package projeto_final;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create an account
        Account account = new Account(12345, 1000.0);

        // Create fixed transactions
        FixedExpense fixedExpense = new FixedExpense(100, "Internet", new Date(), "Provider X", "Monthly", 30);
        FixedIncome fixedIncome = new FixedIncome(2000, "Salary", new Date(), "Company Y", "Monthly", 30);

        // Add transactions to the account
        account.addTransaction(fixedExpense);
        account.addTransaction(fixedIncome);

        // Display balance and transactions
        System.out.println("Current balance: " + account.getBalance());
        for (Transaction transaction : account.getTransactions()) {
            transaction.print();
        }
        
    }   
}
