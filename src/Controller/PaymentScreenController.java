/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import Model.payment.Order;
import Model.product.Movie;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private ArrayList<Order> cart;
    
    private double subTotal, total;
    
    @FXML
    private ImageView logo;

    @FXML
    private Label nameLabel;

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private Label subtotalLabel;
    
    @FXML
    private Label memberTypeDiscount;

    @FXML
    private Label totalLabel;    
    
    @FXML
    public void initialize() {
        this.subTotal = 0;
        this.total = 0;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.nameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());        
    }
    
    public void setCart(ArrayList<Order> cart) {
        this.cart = cart;
        this.setOrders();
    }
    
    public void setOrders() {        
        TableColumn<Order, Movie> movieTitleCol = new TableColumn<>("Movie Title");
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        
        TableColumn<Order, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Iquantity"));
        
        TableColumn<Order, Double> paymentCol = new TableColumn<>("Price");
        paymentCol.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getProduct().getMovie().getTicketPrice() * cellData.getValue().getIquantity()).asObject();
        });
        
        TableColumn<Order, Double> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getProduct().getMovie().getDiscount() != null)
                return new SimpleDoubleProperty(cellData.getValue().getProduct().getMovie().getDiscount().getValue()).asObject();
            return new SimpleDoubleProperty(0).asObject();
        });
        
        this.tableOrders.setItems(FXCollections.observableArrayList(cart));
        this.tableOrders.getColumns().addAll(movieTitleCol, quantityCol, paymentCol, discountCol);       
       
        this.cart.forEach(order -> {
            this.subTotal += order.getIquantity() * order.getProduct().getMovie().getTicketPrice();
            if (order.getProduct().getMovie().getDiscount() != null)
                this.total += (order.getIquantity() * order.getProduct().getMovie().getTicketPrice()) * order.getProduct().getMovie().getDiscount().getValue();
            else
                this.total += (order.getIquantity() * order.getProduct().getMovie().getTicketPrice());
        });
        
        this.subtotalLabel.setText(Double.toString(this.round(this.subTotal)));
        this.memberTypeDiscount.setText(Double.toString(this.customer.getMemberTypeDiscount()));        
        this.totalLabel.setText(Double.toString(this.round(this.total)));
    }
    
    private double round(double val) {
        val = val * 100;
        val = Math.round(val);
        return val / 100;
    }
    
    @FXML
    void conitnueShopping(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuCusto");
    }

    @FXML
    void procedingPayment(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PaymentProcedure.fxml"));       
        OOP_Cinema.addScene("paymentProcedure", loader.load());
        PaymentProcedureController controller = loader.getController();
        controller.setInfo(this.cart, Double.parseDouble(this.totalLabel.getText()));
        OOP_Cinema.changeScene("paymentProcedure");  
    }
    
}
