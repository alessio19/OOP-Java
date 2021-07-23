package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.customer.MemberType;
import Model.employee.Employee;
import Model.employee.EmployeeDAO;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * @author Alessio
 * @author Adam
 * details: Controller for the register view, allow new user to register to our application,
 * give the choice to be a customer or an employee
 */
public class RegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pwdField;

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
    private ChoiceBox<MemberType> customerTypeChoice;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Label customerTypeLabel;
    
    private final EmployeeDAO employeeDAO;
    private final CustomerDAO customerDAO;
    
    /**
     * Constructor
     * @throws SQLException
     */
    public RegisterController() throws SQLException {
        employeeDAO = new EmployeeDAO();
        customerDAO = new CustomerDAO();
    }
    
    /**
     * Initialize items
     */
    @FXML
    public void initialize() {
        ObservableList list = FXCollections.observableArrayList();
        list.addAll(Arrays.asList(MemberType.values()));
        this.customerTypeChoice.setItems(list);
    }
    
    /**
     * Handle action on different elements on the screen
     * @param event
     * @throws SQLException 
     */
    @FXML
    void ActionHandler(ActionEvent event) throws SQLException {
        if (event.getSource().equals(this.registerButton)) {
            if (!this.emailField.getText().isEmpty() &&
                    !this.pwdField.getText().isEmpty() &&
                    !this.nameField.getText().isEmpty() &&
                    !this.lastNameField.getText().isEmpty()) {
                    if (this.employeeRadio.isSelected()) {
                        if (this.employeeDAO.hasEmployee(this.emailField.getText()) || this.customerDAO.hasCustomer(this.emailField.getText())) {
                             this.errorLabel.setOpacity(1);   
                        } else {
                             employeeDAO.addEmployee( new Employee(
                                     0, 
                                     this.emailField.getText(), 
                                     this.pwdField.getText(),  
                                     this.nameField.getText(),  
                                     this.lastNameField.getText(),
                                     this.getRandomPicture()
                             ));
                             OOP_Cinema.changeScene("login");
                        }
                    } else {
                        if (customerDAO.hasCustomer(this.emailField.getText()) || this.employeeDAO.hasEmployee(this.emailField.getText())) {
                              this.errorLabel.setOpacity(1);  
                        } else {
                            System.out.println(this.getRandomPicture());
                            customerDAO.addCustomer(new Customer(
                                    0,  
                                    this.emailField.getText(), 
                                    this.pwdField.getText(), 
                                    this.nameField.getText(),  
                                    this.lastNameField.getText(), 
                                    this.customerTypeChoice.selectionModelProperty().getValue().getSelectedItem(),
                                    this.getRandomPicture()
                            ));
                            OOP_Cinema.changeScene("login");
                        }
                    }
            }
        } else if (event.getSource().equals(this.customerRadio)) {
            this.customerTypeChoice.setOpacity(1);
            this.customerTypeLabel.setOpacity(1);
        } else if (event.getSource().equals(this.employeeRadio)) {
            this.customerTypeChoice.setOpacity(0);
            this.customerTypeLabel.setOpacity(0);
        } else if (event.getSource().equals(this.returnLogin)) {
            OOP_Cinema.changeScene("login");
        }
    }
    
    private String getRandomPicture() {
        String[] gender = {"men", "women"};
        Random rd = new Random();
        int rdGender = rd.nextDouble() >= 0.5? 1 : 0;
        return "https://randomuser.me/api/portraits/"+gender[rdGender]+"/"+rd.nextInt(50)+".jpg";
    }

}

