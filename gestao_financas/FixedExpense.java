import java.util.Date;

public class FixedExpense extends Expense
{
    private String type;
    private String frequency;

    public FixedExpense(double amount, String description, Date date, String destination, String type, String frequency)
    {
        super(amount, description, date, destination);
        this.type = type;
        this.frequency = frequency;
    }

    public String getType()
    {
        return type;
    }

    public String getFrequency()
    {
        return frequency;
    }
}