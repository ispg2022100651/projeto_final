package final_project;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void print() {
        System.out.println("Category details:"
                        + "\nName: " + this.name );
    }
}
