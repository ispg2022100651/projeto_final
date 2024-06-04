import java.util.Date;

public class FixedIncome extends Income
{
    private String type;
    private String frequency;

    public FixedIncome(double amount, String description, Date date, String source, String type, String frequency)
    {
        super(amount, description, date, source);
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