package projeto_final;

import java.util.Date;

public class Transaction {
    private double amount;
    private String description;
    private Date date;

    public Transaction() {
        this(0.0, "", null);
    }
    
    public Transaction(double amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    
    public Transaction(Transaction ref) {
        this(ref.amount, ref.description, ref.date);
    }
            
    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void print() {
        System.out.println("Transaction details:"
                        + "\nAmount: " + this.amount + "\n"
                        + "Description: " + this.description + "\n"
                        + "Date: " + this.date);
    }
    
    public boolean equals(Transaction ref) {
        return (this.amount == ref.amount && this.description == ref.description && this.date == ref.date);
    }
    
    @Override
    public Transaction clone() {
        return(this);
    }
    
    @Override
    public String toString() {
        return "Amount: " + this.amount + "\n"
                + "Description: " + this.description + "\n"
                + "Date" + this.date + ".";
    }
}