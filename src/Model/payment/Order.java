package Model.payment;

import Model.customer.Customer;
import Model.filmSession.FilmSession;
import Model.product.Movie;
import java.util.Date;

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
    private Date reservationDate;

    public Order(int id, Customer customer, FilmSession product, Payment payment, int Iquantity, Date reservationDate) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.payment = payment;
        this.Iquantity = Iquantity;
        this.reservationDate = reservationDate;
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

    public Date getReservationDate() {
        return reservationDate;
    }
    
}
