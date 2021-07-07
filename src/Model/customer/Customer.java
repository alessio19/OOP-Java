package Model.customer;

/**
 * @author Alessio
 * @author Adam
 */
public class Customer {
    private int id;
    private String mail, name, lastName, profilePicture;
    protected String password;    
    private MemberType memberType;

    public Customer(int id, String mail, String password, String name, String lastName, MemberType memberType, String profilePicture) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.memberType = memberType;
        this.profilePicture = profilePicture;
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
    
    public String getPwd() {
        return password;
    }

    public MemberType getMemberType() {
        return memberType;
    } 
    
    public String getProfilePicture() {
        return this.profilePicture;
    }
    
}
