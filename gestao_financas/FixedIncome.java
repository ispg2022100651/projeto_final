import java.util.Date;

public class FixedIncome extends Income
{
    private String type;
    private int frequency;

    public FixedIncome(double amount, String description, Date date, String source, String type,  int frequency)
    {
        super(amount, description, date, source);
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
        return ("Income details:"
                + "\nCategory: " + super.getCategory()
                + "\nAmount: " + super.getAmount() + "€\n"
                + "Description: " + super.getDescription() + "\n"
                + "Date: " + super.getDate() + "\n"
                + "\nDestination: " + super.getSource() + "\n"
                + "Frequency: " + this.frequency + ", " + this.type);
    }
} 