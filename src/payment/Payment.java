package payment;

import java.util.Date;

public class Payment {
    private int id;
    private PaymentSatus status;
    private double price;
    private String cardNumber;
    private Date expirationDate;
    private String ccv;

    public Payment(int id, PaymentSatus status, double price, String cardNumber, Date expirationDate, String ccv) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }
}
