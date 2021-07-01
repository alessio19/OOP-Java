/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_cinema;

import Controller.DataUpdateModule.InsertQuery;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Alessio
 */

public class RegisterController extends Controller {
    
    private InsertQuery insertion;

    @FXML
    private TextField emailField;

    @FXML
    private TextField pwdField;

    @FXML
    private Hyperlink returnLogin;

    @FXML
    private Button registerButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private RadioButton customerRadio;
    
    @FXML
    private RadioButton employeeRadio;
    
    @FXML
    private ChoiceBox<?> customerTypeChoice;
    
    @FXML
    private Label errorLabel;
    
    public RegisterController() throws SQLException {
        super("root", "root");
        this.insertion = new InsertQuery(this.connection);
    }
    
    public RegisterController(String username, String password) throws SQLException {
        super(username, password);
        this.insertion = new InsertQuery(this.connection);
    }

    @FXML
    void ActionHandler(ActionEvent event) throws SQLException {
        if (event.getSource().equals(this.registerButton)) {
            if (!this.emailField.getText().isEmpty() &&
                    !this.pwdField.getText().isEmpty() &&
                    !this.nameField.getText().isEmpty() &&
                    !this.lastNameField.getText().isEmpty()) {
                boolean exception = true;
                if (this.employeeRadio.isSelected()) {
                    exception = this.insertion.insertEmployee(this.emailField.getText(), this.pwdField.getText(), this.nameField.getText(), this.lastNameField.getText());
                } else {
                    exception = this.insertion.insertCustomer(this.emailField.getText(), this.pwdField.getText(), this.nameField.getText(), this.lastNameField.getText(), this.customerTypeChoice.selectionModelProperty().getValue().getSelectedItem().toString());
                }
                if (!exception) {
                    this.errorLabel.setOpacity(1);
                }
            }
        }
    }

}

