package Model.customer;

import Model.payment.Order;
import java.util.ArrayList;

public abstract class Customer {
    protected int id;
    protected String mail;
    protected String password;
    protected String name;
    protected String lastName;
    protected MemberType memberType;
    protected ArrayList<Order> orders;

    public Customer(int id, String mail, String password, String name, String lastName, MemberType memberType, ArrayList<Order> orders) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.memberType = memberType;
        this.orders = orders;
    }
}
