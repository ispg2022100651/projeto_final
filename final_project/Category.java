package final_project;

public class Category {
    private String name;

    public Category() {
        this("");
    }
    
    public Category(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
