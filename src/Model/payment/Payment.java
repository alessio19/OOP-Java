package Model.payment;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 * details: Informations of the customer and the final price of the order
 */
public class Payment {
    private int id;
    private PaymentSatus status;
    private double price;
    private String cardNumber;
    private Date expirationDate;
    protected String ccv;

    /**
     * Constructor
     * @param id
     * @param status
     * @param price
     * @param cardNumber
     * @param expirationDate
     * @param ccv
     */
    public Payment(int id, PaymentSatus status, double price, String cardNumber, Date expirationDate, String ccv) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }

    /**
     * Constructor
     * @param status
     * @param price
     */
    public Payment(PaymentSatus status, double price) {
        this.status = status;
        this.price = price;
    }    

    /**
     * getter
     * @return int: ID
     */
    public int getId() {
        return id;
    }

    /**
     * setter
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }    

    /**
     * getter
     * @return PaymentStatus: status
     */
    public PaymentSatus getStatus() {
        return status;
    }

    /**
     * getter
     * @return double: price
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter
     * @return Date: expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    /**
     * getter
     * @return String: cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * toString
     * @return String: price
     */
    @Override
    public String toString() {
        return Double.toString(price);
    }    
    
}
