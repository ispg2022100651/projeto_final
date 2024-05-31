package final_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private int accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account() {
        this(0, 0.0);
    }
    
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Methods to add transactions
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        FileManager.executeTransaction(transaction, this);
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

    public void print() {
        System.out.println("Transaction details:"
                        + "\nAccount Number: " + this.accountNumber
                        + "\nBalance: " + this.balance +"€");
    }
}
