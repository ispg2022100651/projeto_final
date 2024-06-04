import java.util.Date;

public class Expense extends Transaction
{
    private String destination;

    public Expense(double amount, String description, Date date, String destination)
    {
        super(amount, description, date);
        this.destination = destination;
    }

    public Expense(Expense ref) {
        this(ref.getAmount(), ref.getDescription(), ref.getDate(), ref.destination);
    }

    public Expense() {
        this(0.0, "", null, "");
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override 
    public String toString() {
        return ("Transaction details:"
                        + "\nCategory: " + super.getCategory()
                        + "\nAmount: " + super.getAmount() + "€\n"
                        + "Description: " + super.getDescription() + "\n"
                        + "Date: " + super.getDate() + "\n"
                        + "\nDestination: " + this.destination);
    }

    @Override
    public void print() {
        System.out.println("Transaction details:"
        + "\nCategory: " + super.getCategory()
        + "\nAmount: " + super.getAmount() + "€\n"
        + "Description: " + super.getDescription() + "\n"
        + "Date: " + super.getDate() + "\n"
        + "\nDestination: " + this.destination);
    }
}