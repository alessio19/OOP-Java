/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.product.MovieGenre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Alessio
 */
public class ProfileController {
    
    private boolean updated;
    
    private Customer customer;
    
    @FXML
    private Circle profilePicture;

    @FXML
    private Label mainNameLabel;
    
    @FXML
    private TextField mailAdressField;

    @FXML
    private TextField pwdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private Label editButtonLabel;
    
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private StackedBarChart<String, Integer> profileChart;
    
    @FXML
    public void initialize() {
        this.disableInputs(true);
        this.mainNameLabel.setAlignment(Pos.CENTER);
        this.updated = false;
    }    
    
    @FXML
    void editHandle(MouseEvent event) {        
        if (this.mailAdressField.disableProperty().get()) {
            this.disableInputs(false);
            this.editButtonLabel.setText("Apply");
        } else {
            this.updated = true;
            this.editButtonLabel.setText("Edit");
            this.customer.setMail(this.mailAdressField.getText());
            this.customer.setPassword(this.pwdField.getText());
            this.customer.setName(this.nameField.getText());
            this.customer.setLastName(this.lastNameField.getText());            
            new CustomerDAO().updateCustomer(this.customer);
            this.setCustomer(customer);
            this.disableInputs(true);
        }
    }
    
    private void disableInputs(boolean state) {
        this.mailAdressField.setDisable(state);
        this.pwdField.setDisable(state);
        this.nameField.setDisable(state);
        this.lastNameField.setDisable(state);  
    }

    @FXML
    void exportHandle(MouseEvent event) {

    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.mainNameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
        if (this.customer.getProfilePicture().isEmpty())
            this.profilePicture.setFill(new ImagePattern(new Image("/Resources/images/profile.png")));
        else
            this.profilePicture.setFill(new ImagePattern(new Image(this.customer.getProfilePicture())));
        this.mailAdressField.setText(this.customer.getMail());
        this.pwdField.setText(this.customer.getPwd());
        this.nameField.setText(this.customer.getName());
        this.lastNameField.setText(this.customer.getLastName());
        this.initializeChart();
    }
    
    private void initializeChart() {
//        ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(this.customer.getId());
               
        //yAxis.set(FXCollections.observableArrayList(Arrays.toString(MovieGenre.values())));
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(MovieGenre.values().length);
        yAxis.setLabel("Movie genre");
        
        ArrayList<String> dates = new ArrayList<>();
        new OrderDAO().getDates().forEach(date -> {            
            if (!dates.contains(date.toString())) {
                ArrayList<Order> orders = new OrderDAO().getOrdersByDateAndUsr(date, this.customer.getId());
                dates.add(date.toString());                                                               
                for(MovieGenre mg : MovieGenre.values()) {
                    XYChart.Series<String, Integer> serie = new XYChart.Series<>();
                    serie.setName(mg.name());              
                    orders.forEach(order -> {  
                        if (order.getProduct().getMovie().getGenre().equals(mg))
                            serie.getData().add(new XYChart.Data<>(date.toString(), order.getProduct().getMovie().getGenre().ordinal()));
                    }); 
                    System.out.println(serie.getData());
                    this.profileChart.getData().add(serie);
                }                   
            }                                                  
        });         
        xAxis.setCategories(FXCollections.observableArrayList(dates));
    }
    
    @FXML
    void backHandle(MouseEvent event) throws IOException {
        if(this.updated) {
            OOP_Cinema.removeScreen("mainMenuCusto");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenuCustomer.fxml"));       
            OOP_Cinema.addScene("mainMenuCusto", loader.load());
            MainMenuCustomerController controller = loader.getController();
            controller.setCustomer(customer);                  
        }
        OOP_Cinema.changeScene("mainMenuCusto");
    }
    
    @FXML
    void logoutHandler(MouseEvent event) {
        OOP_Cinema.changeScene("login");
    }
    
}
