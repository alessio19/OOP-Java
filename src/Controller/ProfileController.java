/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Alessio
 */
public class ProfileController {
    
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
    public void initialize() {
        this.disableInputs(true);
        this.mainNameLabel.setAlignment(Pos.CENTER);
    }    
    
    @FXML
    void editHandle(MouseEvent event) {        
        if (this.mailAdressField.disableProperty().get()) {
            this.disableInputs(false);
            this.editButtonLabel.setText("Apply");
        } else {
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
    }
    
    @FXML
    void backHandle(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuCusto");
    }
    
    @FXML
    void logoutHandler(MouseEvent event) {
        OOP_Cinema.changeScene("login");
    }
    
}
