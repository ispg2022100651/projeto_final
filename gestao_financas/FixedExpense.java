import java.util.Date;

public class FixedExpense extends Expense
{
    private String type;
    private int frequency;

    public FixedExpense(double amount, String description, Date date, String destination, String type, int frequency)
    {
        super(amount, description, date, destination);
        this.type = type;
        this.frequency = frequency;
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
} 