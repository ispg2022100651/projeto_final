public class Category
{
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
        this("");
    }

    public Category(Category ref) {
        this.name = ref.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    } 

    public String print() {
        return ("Category details:"
                        + "\nName: " + this.name );
    }
}