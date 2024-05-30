package final_project;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private double balance;
    private String name;
    private List<Transaction> transactions;

    public Account() {
        this("",0, 0.0);
    }
    
    public Account(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Methods to add transactions
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if (transaction instanceof Expense) {
            this.balance -= transaction.getAmount();
        } else if (transaction instanceof Income) {
            this.balance += transaction.getAmount();
        }
    }

    // Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
