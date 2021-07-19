package Controller;

import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.payment.Payment;
import Model.payment.PaymentDAO;
import Model.payment.PaymentSatus;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PaymentProcedureController {
    
    private ArrayList<Order> cart;

    @FXML
    private TextArea cardNumber;

    @FXML
    private TextArea ccv;

    @FXML
    private DatePicker expirationDate;

    @FXML
    private Label price;

    @FXML
    private ImageView resultImg;
    
    @FXML
    public void inialize() {
        
    }

    public void setInfo(ArrayList<Order> cart, double price) {
        this.cart = cart;                
        this.price.setText(Double.toString(price));
    }    
    
    @FXML
    void proceedToPayment(MouseEvent event) {
        if (!this.cardNumber.getText().isEmpty() && !this.ccv.getText().isEmpty() && this.expirationDate.getValue() != null) {
            Payment p = new Payment(0, PaymentSatus.Accepted, Double.parseDouble(this.price.getText()), this.cardNumber.getText(), Date.from(this.expirationDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), this.ccv.getText());            
            p = new PaymentDAO().addPayment(p);
            System.out.println(p);
            for (Order o : this.cart) {
                o.setPayment(p);
                new OrderDAO().addOrder(o);
            }
            this.resultImg.setImage(new Image("/Resources/images/success.png"));
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    OOP_Cinema.changeScene("mainMenuCusto");
                }                
            }, 1500);
        } else {
            this.resultImg.setImage(new Image("/Resources/images/failure.png"));
        }
    }

}
