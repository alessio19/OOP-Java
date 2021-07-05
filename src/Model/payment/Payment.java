package Model.payment;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 */
public class Payment {
    private int id;
    private PaymentSatus status;
    private double price;
    private String cardNumber;
    private Date expirationDate;
    protected String ccv;

    public Payment(int id, PaymentSatus status, double price, String cardNumber, Date expirationDate, String ccv) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }

    public int getId() {
        return id;
    }

    public PaymentSatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
}
