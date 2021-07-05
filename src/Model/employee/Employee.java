package Model.employee;

/**
 * @author Alessio
 * @author Adam
 */
public class Employee {
    private int id;
    private String mail;
    protected String password;
    private String name;
    private String lastName;

    public Employee(int id, String mail, String password, String name, String lastName) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
    
    
}
