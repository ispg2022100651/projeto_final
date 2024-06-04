import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if (transaction instanceof Expense) {
            balance -= transaction.getAmount();
        } else if (transaction instanceof Income) {
            balance += transaction.getAmount();
        }
    }
}
