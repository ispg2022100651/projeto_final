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

    public void setSource(String source) {
        this.source = source;
    }
    
    @Override
    public String toString() {
        return ("Transaction details:"
                + "\nCategory: " + super.getCategory()
                + "\nAmount: " + super.getAmount() + "€\n"
                + "Description: " + super.getDescription() + "\n"
                + "Date: " + super.getDate() + "\n"
                + "\nDestination: " + this.source);
    }
} 