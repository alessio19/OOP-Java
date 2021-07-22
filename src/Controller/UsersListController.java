package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Adam
 * @author Alessio
 * details: Controller for the user list screen view
 */
public class UsersListController{

    @FXML
    private Accordion accordionUsersList;
    
    private ArrayList<Customer> customers;
    
    /**
     * Initialize value
     */
    @FXML
    public void initialize() {
        customers = new CustomerDAO().getCutomers();
        OOP_Cinema.getScene().getStylesheets().add("/Resources/css/userList.css");
        customers.forEach(customer -> {
            ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(customer.getId());
            accordionUsersList.getPanes().add(getPane(customer, orders));
        });
    }
    
    /**
     * Create new titledPane for a customer and show its orders
     * @param customer
     * @param orders
     * @return TitledPane: pane
     */
    private TitledPane getPane(Customer customer, ArrayList<Order> orders) {
        VBox box = new VBox();
        box.setSpacing(10);
        Label orderslabel = new Label("Orders");
        TableView<Order> ordersTable = new TableView<>();
        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("id");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Order, String> titleColumn = new TableColumn<>("title");
        titleColumn.setCellValueFactory(col -> new SimpleStringProperty(col.getValue().getProduct().getMovie().getTitle()));
        TableColumn<Order, Calendar> reservationDateColumn = new TableColumn<>("reservation date");
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        TableColumn<Order, Double> ticketPriceColumn = new TableColumn<>("ticket price");
        ticketPriceColumn.setCellValueFactory(col -> {
            Double value = col.getValue().getPayment().getPrice();
            return new SimpleDoubleProperty(value).asObject();
        });
        TableColumn<Order, Void> actionColumn = new TableColumn<>("action");
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory = (TableColumn<Order, Void> param) -> {
            final TableCell<Order, Void> cell = new TableCell<Order, Void>() {
                private final Button btn = new Button("Delete");
                {
                    btn.setOnAction((ActionEvent event) -> {
                        Order data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data);
                    });
                }
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        btn.setStyle("-fx-arc-height: 50;  -fx-arc-width: 50;-fx-background-color: #6070ff;");
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };
        actionColumn.setCellFactory(cellFactory);
        ordersTable.getColumns().addAll(orderIdColumn, titleColumn, reservationDateColumn, ticketPriceColumn, actionColumn);
        ordersTable.getItems().addAll(orders);
        box.getChildren().addAll(orderslabel, ordersTable);
        TitledPane pane = new TitledPane(new Formatter().format("|%2d|%s|%s|%s|", 
                customer.getId(), 
                this.center(customer.getLastName(), 20), 
                this.center(customer.getName(), 20), 
                this.center(customer.getMail(), 40)).toString(), 
                box
        );
        pane.setMinHeight(250);
        pane.setMaxHeight(250);
        return pane;
    }
    
    /**
     * Change string to a centered string
     * @param s
     * @param size
     * @return String: string
     */
    private String center(String s, int size) {
        return center(s, size, ' ');
    }

    /**
     * Modify string to center it
     * @param s
     * @param size
     * @param pad
     * @return String: centered
     */
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
