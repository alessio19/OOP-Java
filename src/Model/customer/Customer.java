package Model.customer;

/**
 * @author Alessio
 * @author Adam
 * details: Class containing the informations of a customer  
 */
public class Customer {
    
    private final double REGULAR = 0;
    private final double SENIOR = 0.15;
    private final double CHILDREN = 0.25;
    
    private int id;
    private String mail, name, lastName, profilePicture;
    protected String password;    
    private MemberType memberType;

    /**
     * Constructor
     * @param id
     * @param mail
     * @param password
     * @param name
     * @param lastName
     * @param memberType
     * @param profilePicture
     */
    public Customer(int id, String mail, String password, String name, String lastName, MemberType memberType, String profilePicture) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.memberType = memberType;
        this.profilePicture = profilePicture;
    }

    /**
     * getter
     * @return double: discount
     */
    public double getMemberTypeDiscount() {
        switch(this.getMemberType().name()) {
            case "Regular": return REGULAR;
            case "Senior": return SENIOR;
            case "Children": return CHILDREN;
            default: return 0;
        }        
    }
    
    /**
     * getter
     * @return String: name
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return String: lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter
     * @return int; ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter
     * @return String: email
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * getter
     * @return String: pwd
     */
    public String getPwd() {
        return password;
    }

    /**
     * setter
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * getter
     * @return MemberType: memberType
     */
    public MemberType getMemberType() {
        return memberType;
    } 
    
    /**
     * getter
     * @return String: url
     */
    public String getProfilePicture() {
        return this.profilePicture;
    }    

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
