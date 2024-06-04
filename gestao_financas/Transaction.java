import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable
{
    private double amount;
    private String description;
    private Date date;

    public Transaction(double amount, String description, Date date)
    {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDescription()
    {
        return description;
    }

    public Date getDate()
    {
        return date;
    }
}