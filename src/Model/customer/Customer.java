package Model.customer;

/**
 * @author Alessio
 * @author Adam
 */
public class Customer {
    private int id;
    private String mail;
    protected String password;
    private String name;
    private String lastName;
    private MemberType memberType;

    public Customer(int id, String mail, String password, String name, String lastName, MemberType memberType) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.memberType = memberType;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public MemberType getMemberType() {
        return memberType;
    } 
}
