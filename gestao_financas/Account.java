import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Account implements Serializable
{
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;
    private int frequency = 0;
 
    public Account(String accountNumber, double balance, List<Transaction> transactions)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
        if (transactions == null)
        {
            this.transactions = new ArrayList<Transaction>();
        }
        else 
        {
            this.transactions = transactions;
        }
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

    public void addFixedExpenseTransaction(FixedExpense transaction)
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                frequency++;
                if (frequency < transaction.getFrequency()) {
                    long delay = 0;
                    switch (transaction.getType()) {
                        case "Semanalmente":
                            delay = 4000;
                            // delay = 7 * 24 * 60 * 60 * 1000L; // 7 days in milliseconds
                            break;
                        case "Mensalmente":
                            delay = getMonthlyDelay(); // Calculate days in month
                            break;
                        case "Anualmente":
                            delay = 365 * 24 * 60 * 60 * 1000L; // 365 days in milliseconds
                            if (isLeapYear()) {
                                delay += 24 * 60 * 60 * 1000L; // Add 1 day for leap year
                            }
                            break;
                    }

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            addFixedExpenseTransaction(transaction);
                        }
                    }, delay);
                } else {
                    frequency = 0; // Reset frequency counter after completing the schedule
                }

                balance -= transaction.getAmount();
                transactions.add(transaction);
                System.out.println(getFrequencyTransition());
            }
        };
        timer.schedule(task, 0);
    }

    public int getFrequencyTransition()
    {
        return this.frequency;
    }

    public void addFixedIncomeTransaction(FixedIncome transaction) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                frequency++;
                if (frequency < transaction.getFrequency()) {
                    long delay = 0;
                    switch (transaction.getType()) {
                        case "Semanalmente":
                            delay = 4000;
                            // delay = 7 * 24 * 60 * 60 * 1000L; // 7 days in milliseconds
                            break;
                        case "Mensalmente":
                            delay = getMonthlyDelay(); // Calculate days in month
                            break;
                        case "Anualmente":
                            delay = 365 * 24 * 60 * 60 * 1000L; // 365 days in milliseconds
                            if (isLeapYear()) {
                                delay += 24 * 60 * 60 * 1000L; // Add 1 day for leap year
                            }
                            break;
                    }

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            addFixedIncomeTransaction(transaction);
                        }
                    }, delay);
                } else {
                    frequency = 0; // Reset frequency counter after completing the schedule
                }

                balance += transaction.getAmount();
                transactions.add(transaction);
                System.out.println(getFrequencyTransition());
            }
        };
        timer.schedule(task, 0);
    }

    private long getMonthlyDelay() {
        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth * 24 * 60 * 60 * 1000L; // days in month to milliseconds
    }

    private boolean isLeapYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void addTransaction(Transaction transaction)
    {
        if ( transaction instanceof Expense )
        {
            this.balance -= transaction.getAmount();
        }
        else
        {
            this.balance += transaction.getAmount();
        }

        System.out.println(transaction);
        this.transactions.add(transaction);
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Account details:\n");
        builder.append("Account Number: ").append(this.accountNumber).append("\n");
        builder.append("Balance: ").append(this.balance).append(" € \n");

        return builder.toString();
    }

    public void print() {
        System.out.println("Transaction details:"
        + "\nAccount Number: " + this.accountNumber
        + "\nBalance: " + this.balance +"€");
    }
}