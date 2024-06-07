import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable
{
    private double amount;
    private String description;
    private Category category;
    private Date date;
 
    public Transaction(double amount, String description, Date date, Category category)
    {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public Transaction(Transaction ref) {
        this(ref.amount, ref.description, ref.date, ref.category);
    }

    public Transaction() {
        this(0.0, "", null, null);
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public Transaction clone() {
        return(this);
    }

    public boolean equals(Transaction ref) {
        return (this.amount == ref.amount && this.description == ref.description && this.date == ref.date);
    }

    public String toString() {
        return ("Transaction details:"
                        + "\nCategory: " + this.getCategory()
                        + "\nAmount: " + this.amount + "€\n"
                        + "Description: " + this.description + "\n"
                        + "Date: " + this.date);
    }

    public void print() {
        System.out.println("Transaction details:"
                        + "\nCategory: " + this.getCategory()
                        + "\nAmount: " + this.amount + "€\n"
                        + "Description: " + this.description + "\n"
                        + "Date: " + this.date);
    }
}