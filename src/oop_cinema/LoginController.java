package oop_cinema;

import Controller.InformationSearchModule.SelectQuery;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
    
    private SelectQuery selection;

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

    public LoginController() throws SQLException {
        super("root", "root");
        this.selection = new SelectQuery(this.connection);
    }

    public LoginController(String username, String password) throws SQLException {
        super(username, password);
        this.selection = new SelectQuery(this.connection);
    }

    @FXML
    void ActionHandler(ActionEvent event) throws SQLException, IOException {
        if (event.getSource().equals(this.connectButton)) {
            if (!this.selection.isEmpty(this.selection.getCustomer(this.emailField.getText(), this.pwdField.getText()))) {
                System.out.println("customer exist");
            } else if (!this.selection.isEmpty(this.selection.getEmployee(this.emailField.getText(), this.pwdField.getText()))) {
                System.out.println("employee exist");
            } else 
                System.out.println("not in the db");
        } else if (event.getSource().equals(this.registerButton)) {
            OOP_Cinema.addScene("register", FXMLLoader.load(getClass().getResource("Register.fxml")));
            OOP_Cinema.changeScene("register");
        }
    }

}
