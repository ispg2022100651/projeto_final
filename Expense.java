package projeto_final;

import java.util.Date;

public class Expense extends Transaction {
    private String destination;

    public Expense() {
        this(0.0, "", null, "");
    }
    
    public Expense(double amount, String description, Date date, String destination) {
        super(amount, description, date);
        this.destination = destination;
    }

    // Getter and Setter
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    @Override 
    public void print() {
        System.out.println("Transaction details:"
                        + "\nAmount: " + super.getAmount() + "\n"
                        + "Description: " + super.getDescription() + "\n"
                        + "Date: " + super.getDate() + "\n"
                        + "\nDestination: " + this.destination);
    }
}