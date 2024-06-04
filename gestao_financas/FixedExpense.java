import java.util.Date;

public class FixedExpense extends Expense implements FixedInterface
{
    private String type;
    private int frequency;

    public FixedExpense(double amount, String description, Date date, Category category, String destination, String type, int frequency)
    {
        super(amount, description, date, category, destination);
        this.type = type;
        this.frequency = frequency;
    }

    public FixedExpense(FixedExpense ref) {
        this(ref.getAmount(), ref.getDescription(), ref.getDate(), ref.getCategory(), ref.getDestination(), ref.type, ref.frequency);
    }

    public FixedExpense() {
        this(0.0, "", null, null, "", "", 0);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public String toString() {
        return ("Transaction details:"
                        + "\nCategory: " + super.getCategory()
                        + "\nAmount: " + super.getAmount() + "€\n"
                        + "Description: " + super.getDescription() + "\n"
                        + "Date: " + super.getDate() + "\n"
                        + "\nDestination: " + super.getDestination() + "\n"
                        + "Frequency: " + this.frequency + ", " + this.type);
    }

    @Override
    public void print() {
        System.out.println("Transaction details:"
        + "\nCategory: " + super.getCategory()
        + "\nAmount: " + super.getAmount() + "€\n"
        + "Description: " + super.getDescription() + "\n"
        + "Date: " + super.getDate() + "\n"
        + "\nDestination: " + super.getDestination() + "\n"
        + "Frequency: " + this.frequency + ", " + this.type);
    }
} 