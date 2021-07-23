package Model.payment;

import Model.customer.Customer;
import Model.filmSession.FilmSession;
import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 * details: class containing the information of an order from a customer
 */
public class Order {
    private int id;
    private Customer customer;
    private FilmSession product;
    private Payment payment;
    private int Iquantity;
    private Date reservationDate;

    /**
     * Constructor
     * @param id
     * @param customer
     * @param product
     * @param payment
     * @param Iquantity
     * @param reservationDate
     */
    public Order(int id, Customer customer, FilmSession product, Payment payment, int Iquantity, Date reservationDate) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.payment = payment;
        this.Iquantity = Iquantity;
        this.reservationDate = reservationDate;
    }

    /**
     * Constructor
     * @param customer
     * @param product
     * @param payment
     * @param Iquantity
     */
    public Order(Customer customer, FilmSession product, Payment payment, int Iquantity) {
        this.customer = customer;
        this.product = product;
        this.payment = payment;
        this.Iquantity = Iquantity;
    }    

    /**
     * getter
     * @return int: ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter
     * @return Customer: customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * getter
     * @return FilmSession: filmSession
     */
    public FilmSession getProduct() {
        return product;
    }

    /**
     * setter
     * @param payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }    
    
    /**
     * getter
     * @return Payment: payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * getter
     * @return int: quantity
     */
    public int getIquantity() {
        return Iquantity;
    }

    /**
     * getter
     * @return Date: reservationDate
     */
    public Date getReservationDate() {
        return reservationDate;
    }
    
}
