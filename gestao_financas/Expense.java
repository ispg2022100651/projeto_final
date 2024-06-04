import java.io.Serializable;
import java.util.Date;

public class Expense extends Transaction implements Serializable {
    private String destination;

    public Expense(double amount, String description, Date date, String destination) {
        super(amount, description, date);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}