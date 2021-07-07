package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.customer.MemberType;
import Model.employee.Employee;
import Model.employee.EmployeeDAO;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * @author Alessio
 * @author Adam
 */
public class RegisterController {

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
    private TextField profilePictureURL;
    
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
    
    private EmployeeDAO employeeDAO;
    private CustomerDAO customerDAO;
    
    public RegisterController() throws SQLException {
        employeeDAO = new EmployeeDAO();
        customerDAO = new CustomerDAO();
    }
    
    @FXML
    public void initialize() {
        ObservableList list = FXCollections.observableArrayList();
        for (MemberType m : MemberType.values())
            list.add(m);
        this.customerTypeChoice.setItems(list);
    }
    
    @FXML
    void ActionHandler(ActionEvent event) throws SQLException {
        if (event.getSource().equals(this.registerButton)) {
            if (!this.emailField.getText().isEmpty() &&
                    !this.pwdField.getText().isEmpty() &&
                    !this.nameField.getText().isEmpty() &&
                    !this.lastNameField.getText().isEmpty()) {
                    if (this.employeeRadio.isSelected()) {
                        if (this.employeeDAO.hasEmployee(this.emailField.getText())) {
                             this.errorLabel.setOpacity(1);   
                        } else {
                             employeeDAO.addEmployee( new Employee(
                                     0, 
                                     this.emailField.getText(), 
                                     this.pwdField.getText(),  
                                     this.nameField.getText(),  
                                     this.lastNameField.getText(),
                                     this.profilePictureURL.getText()
                             ));
                             OOP_Cinema.changeScene("login");
                        }
                    } else {
                        if (customerDAO.hasCustomer(this.emailField.getText())) {
                              this.errorLabel.setOpacity(1);  
                        } else {
                            customerDAO.addCustomer(new Customer(
                                    0,  
                                    this.emailField.getText(), 
                                    this.pwdField.getText(), 
                                    this.nameField.getText(),  
                                    this.lastNameField.getText(), 
                                    this.customerTypeChoice.selectionModelProperty().getValue().getSelectedItem(),
                                    this.profilePictureURL.getText()
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

}

