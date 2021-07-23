package Model.employee;

/**
 * @author Alessio
 * @author Adam
 * details: Contain all the informations of an employee, have access to specific view
 */
public class Employee {
    private int id;
    private String mail, name, lastName, profilePicture;
    protected String password;

    public Employee(int id, String mail, String password, String name, String lastName, String profilePicture) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
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
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
}
