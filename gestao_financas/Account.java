import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable
{
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions = new ArrayList<Transaction>();
 
    public Account(String accountNumber, double balance, List<Transaction> transactions)
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

    public List<Transaction> getTransactions()
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

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Transaction details:\n");
        builder.append("Account Number: ").append(this.accountNumber).append("\n");
        builder.append("Balance: ").append(this.balance).append(" € \n");
        builder.append("Transactions: ").append(this.transactions).append("\n");

        return builder.toString();
    }

    public void print() {
        System.out.println("Transaction details:"
        + "\nAccount Number: " + this.accountNumber
        + "\nBalance: " + this.balance +"€"
        + "\nTransactions: " + this.transactions);
    }
}