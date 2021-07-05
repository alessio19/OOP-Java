package Model.payment;

import Model.customer.Customer;
import Model.product.Movie;

/**
 * @author Alessio
 * @author Adam
 */
public class Order {
    private int id;
    private Customer customer;
    private Movie product;
    private Payment payment;
    private int Iquantity;

    public Order(int id, Customer customer, Movie product, Payment payment, int Iquantity) {
        this.id = id;
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

    public Movie getProduct() {
        return product;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getIquantity() {
        return Iquantity;
    }
}
