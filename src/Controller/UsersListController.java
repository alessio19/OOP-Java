package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.customer.MemberType;
import Model.payment.Order;
import Model.payment.OrderDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

public class UsersListController{
    
    private Customer selectedUser ;
    private TitledPane selectedPane;

    @FXML
    private Accordion accordionUsersList;
    
    private ArrayList<Customer> customers;
    
    @FXML
    private Circle profilePicture;

    @FXML
    private TextField mailAdressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<MemberType> userTypeCmb;
    
    @FXML
    private Label editlabel;
    
    @FXML
    public void initialize() {
        customers = new CustomerDAO().getCutomers();
        OOP_Cinema.getScene().getStylesheets().add("/Resources/css/userList.css");
        for(Customer customer : customers) {
            ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(customer.getId());
            accordionUsersList.getPanes().add(getPane(customer, orders));
        }
        userTypeCmb.setItems( FXCollections.observableArrayList( MemberType.values()));
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
                        deleteOrder(data, ordersTable);
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
        
        pane.setOnMouseClicked(event -> {
            this.changeSelectedUser(customer);
            this.selectedPane = pane;
        });
        
        return pane;
    }
    
    private void deleteOrder(Order order,  TableView<Order> ordersTable) {
        new OrderDAO().deleteOrder(order);
        ordersTable.getItems().remove(order);
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
    
    @FXML
    void backToMenu(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuEmployee");
    }
    
    private void changeSelectedUser(Customer customer) {
          if(customer == null) {
            return;
        }
        this.selectedUser = customer;
        this.disableInputs(true);
         if (customer.getProfilePicture().isEmpty())
            this.profilePicture.setFill(new ImagePattern(new Image("/Resources/images/profile.png")));
        else
            this.profilePicture.setFill(new ImagePattern(new Image(customer.getProfilePicture())));
        this.mailAdressField.setText(customer.getMail());
        this.nameField.setText(customer.getName());
        this.lastNameField.setText(customer.getLastName());
        this.userTypeCmb.getSelectionModel().select(customer.getMemberType());
    }
    
    private void disableInputs(boolean state) {
        this.mailAdressField.setDisable(state);
        this.nameField.setDisable(state);
        this.lastNameField.setDisable(state);  
        this.userTypeCmb.setDisable(state);
    }
    
    @FXML
    void editHandle(MouseEvent event) {
        if (this.mailAdressField.disableProperty().get()) {
            this.disableInputs(false);
            this.editlabel.setText("Apply");
        } else {
            this.editlabel.setText("Edit");
            this.selectedUser.setMail(this.mailAdressField.getText());
            this.selectedUser.setName(this.nameField.getText());
            this.selectedUser.setLastName(this.lastNameField.getText());      
            this.selectedUser.setMemberType(this.userTypeCmb.getSelectionModel().getSelectedItem());
            System.out.println(this.userTypeCmb.getSelectionModel().getSelectedItem());
            new CustomerDAO().updateCustomer(this.selectedUser);
            this.disableInputs(true);
        }
    }
    
     @FXML
    void deleteCustomer(MouseEvent event) {
        new CustomerDAO().deleteCustomer(this.selectedUser);
        accordionUsersList.getPanes().remove(this.selectedPane);
    }
    
}
