package Controller;

import Model.customer.CustomerDAO;
import Model.employee.EmployeeDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Alessio
 * @author Adam
 */
public class LoginController  {

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
    private Label loginFail;

    public LoginController() throws SQLException {
    }

    @FXML
    void ActionHandler(ActionEvent event) throws SQLException, IOException {
        if (event.getSource().equals(this.connectButton)) {
            if (new CustomerDAO().hasCustomer(this.emailField.getText(), this.pwdField.getText())) {
                OOP_Cinema.addScene("mainMenuCusto", FXMLLoader.load(getClass().getResource("/View/MainMenuCustomer.fxml")));
                OOP_Cinema.changeScene("mainMenuCusto");               
            } else if (new EmployeeDAO().hasEmployee(this.emailField.getText(), this.pwdField.getText())) {
                System.out.println("employee exist"); // Load Employee View
            } else 
                loginFail.visibleProperty().set(true);
        } else if (event.getSource().equals(this.registerButton)) {
            OOP_Cinema.addScene("register", FXMLLoader.load(getClass().getResource("/View/Register.fxml")));
            OOP_Cinema.changeScene("register");
        }
    }

}
