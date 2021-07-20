package Model.payment;

import Model.customer.Customer;
import Model.filmSession.FilmSession;
import Model.product.Movie;

/**
 * @author Alessio
 * @author Adam
 */
public class Order {
    private int id;
    private Customer customer;
    private FilmSession product;
    private Payment payment;
    private int Iquantity;

    public Order(int id, Customer customer, FilmSession product, Payment payment, int Iquantity) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.payment = payment;
        this.Iquantity = Iquantity;
    }

    public Order(Customer customer, FilmSession product, Payment payment, int Iquantity) {
        this.customer = customer;
        this.product = product;
        this.payment = payment;
        this.Iquantity = Iquantity;
    }    

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public FilmSession getProduct() {
        return product;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }    
    
    public Payment getPayment() {
        return payment;
    }

    public int getIquantity() {
        return Iquantity;
    }
}
