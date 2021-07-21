package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Zenkh
 */
public class UsersListController{

    @FXML
    private Accordion accordionUsersList;
    
    @FXML
    private ScrollPane scrollPane;
    
    private ArrayList<Customer> customers;
    
    @FXML
    public void initialize() {
        customers = new CustomerDAO().getCutomers();
        OOP_Cinema.getScene().getStylesheets().add("/Resources/css/userList.css");
        for(Customer customer : customers) {
            ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(customer.getId());
            accordionUsersList.getPanes().add(getPane(customer, orders));
        }
    }
    
    private TitledPane getPane(Customer customer, ArrayList<Order> orders) {
        VBox box = new VBox();
        box.setSpacing(10);
        Label orderslabel = new Label("Orders");
        TableView<Order> ordersTable = new TableView<>();
        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("id");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Order, String> titleColumn = new TableColumn<>("title");
        titleColumn.setCellValueFactory(col -> new SimpleStringProperty(col.getValue().getProduct().getMovie().getTitle()));
        
        TitledPane pane = new TitledPane(new Formatter().format("|%2d|%s|%s|%s|", 
                customer.getId(), 
                this.center(customer.getLastName(), 20), 
                this.center(customer.getName(), 20), 
                this.center(customer.getMail(), 40)).toString(), 
                box
        );
        return pane;
    }
    
    private String center(String s, int size) {
        return center(s, size, ' ');
    }

    private String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
    
}
