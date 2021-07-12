package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
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
        scrollPane.setMinWidth(1000);
        accordionUsersList.setMinWidth(995);
        for(Customer customer : customers) {
            ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(customer.getId());
            accordionUsersList.getPanes().add(getPane(customer, orders));
        }
            
    }
    
    private TitledPane getPane(Customer customer, ArrayList<Order> orders) {
        TitledPane pane = new TitledPane(customer.getLastName(), new Rectangle(100,100));
        return pane;
    }
    
}
