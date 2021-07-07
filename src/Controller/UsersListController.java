package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
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
    
    private ArrayList<Customer> customers;
    
    @FXML
    public void initialize() {
        customers = new CustomerDAO().getCutomers();
        accordionUsersList.setMinWidth(600);
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
