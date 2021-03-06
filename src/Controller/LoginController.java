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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Alessio
 * @author Adam
 * details: Controller of the login section, allow any user to be redirect to its section
 */
public class LoginController  {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Hyperlink registerButton;

    @FXML
    private Button connectButton;
    
    @FXML
    private Label loginFail;

    /**
     * Constructor
     * @throws SQLException
     */
    public LoginController() throws SQLException {
    }

    @FXML
    void ActionHandler(ActionEvent event) throws SQLException, IOException {
        if (event.getSource().equals(this.connectButton)) {
            if (new CustomerDAO().hasCustomer(this.emailField.getText(), this.pwdField.getText())) { //if user is a customer redirect to the main menu for customer
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenuCustomer.fxml"));       
                OOP_Cinema.addScene("mainMenuCusto", loader.load());
                MainMenuCustomerController controller = loader.getController();
                controller.setCustomer(new CustomerDAO().getCustomerByCredentials(this.emailField.getText(), this.pwdField.getText()));                
                OOP_Cinema.changeScene("mainMenuCusto");               
                OOP_Cinema.getPane("mainMenuCusto").getScene().setUserData(loader);
            } else if (new EmployeeDAO().hasEmployee(this.emailField.getText(), this.pwdField.getText())) { //if user is an employee redirect to the main menu for employee
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenuEmployee.fxml"));       
                OOP_Cinema.addScene("mainMenuEmployee", loader.load());
                MainMenuEmployeeController controller = loader.getController();
                controller.setEmployee(new EmployeeDAO().getEmployeeByCredentials(this.emailField.getText(), this.pwdField.getText()));                
                OOP_Cinema.changeScene("mainMenuEmployee");               
            } else //not in the database
                loginFail.visibleProperty().set(true);
        } else if (event.getSource().equals(this.registerButton)) {
            OOP_Cinema.addScene("register", FXMLLoader.load(getClass().getResource("/View/Register.fxml")));
            OOP_Cinema.changeScene("register");
        }
    }

}
