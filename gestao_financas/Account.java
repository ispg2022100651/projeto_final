import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions;
 
    public Account(String accountNumber, double balance, ArrayList<Transaction> transactions)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Account(Account ref) {
        this(ref.accountNumber, ref.balance, ref.transactions);
    }

    public Account() {
        this("", 0.0, null);
    }

    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public ArrayList<Transaction> getTransactions()
    {
        return this.transactions;
    }

    public void addTransaction(Transaction transaction)
    {
        if ( transaction instanceof Expense )
        {
            this.balance -= transaction.getAmount();
        }
        else if ( transaction instanceof Income )
        {
            this.balance += transaction.getAmount();
        }
        System.out.println(transaction);
        this.transactions.add(transaction);
    }

    public String toString() {
        return ("Transaction details:"
                        + "\nAccount Number: " + this.accountNumber
                        + "\nBalance: " + this.balance +"€");
    }

    public void print() {
        System.out.println("Transaction details:"
        + "\nAccount Number: " + this.accountNumber
        + "\nBalance: " + this.balance +"€");
    }
}