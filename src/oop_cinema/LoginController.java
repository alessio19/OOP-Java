package oop_cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField pwdField;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Hyperlink registerButton;

    @FXML
    private Button connectButton;

    @FXML
    void ActionHandler(ActionEvent event) {
        if (event.getSource().equals(this.connectButton)) {
            
        }
    }

}
