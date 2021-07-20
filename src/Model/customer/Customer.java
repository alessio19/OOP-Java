package Model.customer;

/**
 * @author Alessio
 * @author Adam
 */
public class Customer {
    
    private final double REGULAR = 0;
    private final double SENIOR = 0.15;
    private final double CHILDREN = 0.25;
    
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

    public double getMemberTypeDiscount() {
        switch(this.getMemberType().name()) {
            case "Regular": return REGULAR;
            case "Senior": return SENIOR;
            case "Children": return CHILDREN;
            default: return 0;
        }        
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public MemberType getMemberType() {
        return memberType;
    } 
    
    public String getProfilePicture() {
        return this.profilePicture;
    }    
    
}
