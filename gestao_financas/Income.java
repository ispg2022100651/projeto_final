import java.util.Date;

public class Income extends Transaction
{
    private String source;

    public Income(double amount, String description, Date date, String source)
    {
        super(amount, description, date);
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
}