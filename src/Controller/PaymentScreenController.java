/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.payment.Payment;
import Model.product.Movie;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Alessio
 */
public class PaymentScreenController {

    private Customer customer;
    private ArrayList<Order> orders;
    
    private double subTotal, discountTotal;
    
    @FXML
    private ImageView logo;

    @FXML
    private Label nameLabel;

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label discountLabel;

    @FXML
    private Label totalLabel;
    
    @FXML
    public void initialize() {
        this.subTotal = 0;
        this.discountTotal = 0;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.nameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
        this.setOrders();
    }
    
    public void setOrders() {
        this.orders = new OrderDAO().getOrdersForUsrId(this.customer.getId());
        TableColumn<Order, Movie> movieTitleCol = new TableColumn<>("Movie Title");
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        TableColumn<Order, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Iquantity"));
        TableColumn<Order, Payment> paymentCol = new TableColumn<>("Price");
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        
        this.tableOrders.setItems(FXCollections.observableArrayList(orders));
        this.tableOrders.getColumns().addAll(movieTitleCol, quantityCol, paymentCol);       
       
        this.orders.forEach(order -> {
            this.subTotal += order.getPayment().getPrice();
            this.discountTotal += order.getProduct().getDiscount().getValue();
        });
        this.subtotalLabel.setText(Double.toString(this.subTotal));
        this.discountLabel.setText(Double.toString(this.discountTotal));
        //this.totalLabel.setText(Double.toString(this.subTotal - this.discountTotal));
    }
    
    @FXML
    void conitnueShopping(MouseEvent event) {

    }

    @FXML
    void procedingPayment(MouseEvent event) {

    }

    @FXML
    void profileButton(MouseEvent event) {

    }
    
}
