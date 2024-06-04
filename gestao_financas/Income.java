import java.util.Date;

public class Income extends Transaction
{
    private String source;

    public Income(double amount, String description, Date date, Category category, String source)
    {
        super(amount, description, date, category);
        this.source = source;
    }

    public Income(Income ref) {
        this(ref.getAmount(), ref.getDescription(), ref.getDate(), ref.getCategory(), ref.source);
    }

    public Income() {
        this(0.0, "", null, null, "");
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

    @Override
    public void print() {
        System.out.println("Transaction details:"
                + "\nCategory: " + super.getCategory()
                + "\nAmount: " + super.getAmount() + "€\n"
                + "Description: " + super.getDescription() + "\n"
                + "Date: " + super.getDate() + "\n"
                + "\nDestination: " + this.source);
    }
} 