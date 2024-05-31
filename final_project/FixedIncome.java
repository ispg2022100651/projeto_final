package final_project;

import java.util.Date;

public class FixedIncome extends Income implements FixedInterface{
    private static final long serialVersionUID = 1L;
    private String type; // e.g., week, day, month
    private int frequency; // in days

    public FixedIncome() {
        this(0.0,"",null,"","",0);
    }
    
    public FixedIncome(double amount, String description, Date date, String source, String type, int frequency) {
        super(amount, description, date, source);
        this.type = type;
        this.frequency = frequency;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public void print() {
        System.out.println("Income details:"
                + "\nCategory: " + super.getCategory()
                + "\nAmount: " + super.getAmount() + "€\n"
                + "Description: " + super.getDescription() + "\n"
                + "Date: " + super.getDate() + "\n"
                + "\nDestination: " + super.getSource() + "\n"
                + "Frequency: " + this.frequency + ", " + this.type);
    }
}
