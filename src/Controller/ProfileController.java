/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    private Label nameLabel;

    @FXML
    private Rectangle editButton;

    @FXML
    private Rectangle exportButton;

    @FXML
    private Label mailLabel;

    @FXML
    private Label pwdLabel;

    @FXML
    private Label lastNameLabel;    
    
    @FXML
    public void initialize() {

    }    
    
    @FXML
    void editHandle(MouseEvent event) {

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
        this.mailLabel.setText(this.customer.getMail());
        this.pwdLabel.setText(this.customer.getPwd());
        this.nameLabel.setText(this.customer.getName());
        this.lastNameLabel.setText(this.customer.getLastName());
    }
    
}
