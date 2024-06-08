import java.io.Serializable;


public class Category implements Serializable
{
    private String name;

    public Category(String name) {
        this.name = name;
    }
    
    public Category(Category ref) {
        this.name = ref.name;
    }

    public Category() {
        this("");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    } 

    public String toString() {
        return ( this.name );
    }
    
    public void print() {
        System.out.println("Category details:"
        + "\nName: " + this.name );
    }

}