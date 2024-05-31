package final_project;

import java.util.Date;

public class Income extends Transaction {
    private static final long serialVersionUID = 1L;
    private String source;

    public Income() {
        this(0.0, "", null,"");
    }
    
    public Income(double amount, String description, Date date, String source) {
        super(amount, description, date);
        this.source = source;
    }

    // Getter and Setter
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    @Override
    public void print() {
        System.out.println("Income details:"
                + "\nCategory: " + super.getCategory()
                + "\nAmount: " + super.getAmount() + "€\n"
                + "Description: " + super.getDescription() + "\n"
                + "Date: " + super.getDate() + "\n"
                + "\nDestination: " + this.source);
    }
}
