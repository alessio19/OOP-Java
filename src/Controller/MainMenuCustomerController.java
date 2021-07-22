package Controller;

import Model.customer.Customer;
import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.product.Discount;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @author Alessio
 * @author Adam
 * details: Controller of the main menu for the customer, 
 * allow different action for the customer.
 * Also contain the cart of the customer during its session
 */
public class MainMenuCustomerController {
    
    private Customer customer;
    private ArrayList<Movie> movies;
    private ArrayList<Order> cart;

    @FXML
    private Label nameLabel;
    
    @FXML
    private Label itemInCart;
    
    @FXML
    private Pagination paginationCurrentMovies;
    
    @FXML
    private ComboBox<FilmSession> cmbSessions;

    @FXML
    private Label movieName;

    @FXML
    private Label movieDescription;

    @FXML
    private Label author;

    @FXML
    private Label ticketPrice;

    @FXML
    private Label discount;

    @FXML
    private ComboBox<Integer> cmbQuantity;

    @FXML
    private Label totalPrice;
    
    @FXML
    private AnchorPane sessionDetailPane;
    
    @FXML
    private TableView<Movie> tableDeal;
    
    @FXML
    private TableView<Order> tableOrders;
    
    /**
     * Initialize values and style sheet
     */
    @FXML
    public void initialize() {
        OOP_Cinema.getScene().getStylesheets().add("/Resources/css/movie.css");
        this.movies = new MovieDAO().getMovies();
        int page = movies.size()%3 ==0 ? 0 : 1;
        paginationCurrentMovies.setPageCount((movies.size()/3 + page));
        paginationCurrentMovies.setPageFactory((Integer pageIndex) -> {
            HBox box = new HBox(3);
            for (int i = pageIndex*3 ; i < (pageIndex+1)*3; i++) {
                box.setMinHeight(250);
                box.setMinWidth(100);
                try {
                    box.getChildren().add(getMovieContainer(movies.get(i)));
                } catch (Exception e) {
                }
            }
            return box;
        });          
        ArrayList<Movie> movieWithDiscount = new ArrayList<>();
        movies.forEach(movie -> {
            if (movie.getDiscount() != null) 
                movieWithDiscount.add(movie);            
        });
        TableColumn<Movie, String> movieCol = new TableColumn<>("Movie");
        movieCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Movie, Discount> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        
        movieCol.setMinWidth(160);
        discountCol.setMinWidth(50);
        movieCol.setStyle("-fx-alignment: CENTER;");
        discountCol.setStyle("-fx-alignment: CENTER;");        
        
        this.tableDeal.setItems(FXCollections.observableArrayList(movieWithDiscount));
        this.tableDeal.getColumns().addAll(movieCol, discountCol);   
        this.tableDeal.setOnMouseClicked(e -> {
            this.changeSession(this.tableDeal.getSelectionModel().getSelectedItem());
        });
    }
    
    /**
     * Constructor
     */
    public MainMenuCustomerController() {
        
    }  
    
    /**
     * Redirect the customer to the profile screen view
     * @param event
     * @throws IOException 
     */
    @FXML
    void profileButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Profile.fxml"));       
        OOP_Cinema.addScene("profile", loader.load());
        ProfileController controller = loader.getController();
        controller.setCustomer(this.customer);  
        OOP_Cinema.changeScene("profile");
    }

    /**
     * setter
     * set and initialize the text holder with a name and last name
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.nameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
        this.cart = new ArrayList<>();
        this.setCustomerOrders();
    }
    
    /**
     * setter
     * set and initialize the orders of the customer in a table
     */
    private void setCustomerOrders() {
        ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(this.customer.getId());
        TableColumn<Order, Movie> movieCol = new TableColumn<>("Movie");
        movieCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        TableColumn<Order, Integer> ticketCol = new TableColumn<>("Quantity");
        ticketCol.setCellValueFactory(new PropertyValueFactory<>("Iquantity"));
        TableColumn<Order, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        TableColumn<Order, String> paymentCol = new TableColumn<>("Payment status");
        paymentCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPayment().getStatus().name());
        });
        
        this.tableOrders.setItems(FXCollections.observableArrayList(orders));
        this.tableOrders.getColumns().addAll(movieCol, ticketCol, priceCol, paymentCol);
    }
    
    /**
     * Create a movie container with the different element and event handler
     * @param movie
     * @return Pane: result
     */
    private Pane getMovieContainer(Movie movie) {
        Pane pane = new Pane();
               
               Rectangle r = new Rectangle(200, 250);
               Rectangle title = new Rectangle(190, 75);
               Label titleLabel = new Label(movie.getTitle());
               Label descriptionLabel = new Label(movie.getDetails());
               
               r.getStyleClass().add("movieContainer");
               r.setFill(new ImagePattern(new Image(movie.getImage())));
               r.setOnMouseClicked((event) -> {
                   changeSession(movie);
               });
               title.getStyleClass().add("movieTitleContainer");
               title.setFill(Color.WHITE);
               title.setLayoutY(167);
               title.setLayoutX(5);
               title.setOpacity(0.85);
               titleLabel.setLayoutY(175);
               titleLabel.setLayoutX(20);
               titleLabel.setFont(Font.font("System",FontWeight.BOLD, 14));
               descriptionLabel.setLayoutY(195);
               descriptionLabel.setLayoutX(20);
               descriptionLabel.setWrapText(true);
               descriptionLabel.setMaxWidth(120);
               descriptionLabel.setMaxHeight(40);
               
               pane.getChildren().add(r);
               pane.getChildren().add(title);
               pane.getChildren().add(titleLabel);
               pane.getChildren().add(descriptionLabel);
               
               return pane;
    }

    /**
     * Create a new order and add it to the customer's cart, update the number of items
     * @param event 
     */
    @FXML
    void handleOrderBtn(ActionEvent event) {
        this.cart.add(
                new Order(
                        this.customer, 
                        cmbSessions.getValue(), 
                        null, 
                        this.cmbQuantity.getSelectionModel().getSelectedItem()
                )
        );
        this.updateNbItemCart();
    }
    
    /**
     * Modify the subsection with the detailed view of the selected movie
     * @param movie 
     */
    private void changeSession(Movie movie) {
        totalPrice.setText("0€");
        sessionDetailPane.setDisable(false);
        movieName.setText(movie.getTitle());
        author.setText(movie.getAuthor());
        movieDescription.setText(movie.getDetails());
        ticketPrice.setText(movie.getTicketPrice()+" €");
        discount.setText(movie.getDiscount()!=null ? movie.getDiscount().getValue()*100 + "%" : "No discount");
        cmbQuantity.setDisable(true);
        ArrayList<FilmSession> sessions = new FilmSessionDAO().getFilmSessionByMovieId(movie.getId());
        
        updateSessionCMB(sessions);
        
        sessionDetailPane.setVisible(true);
    }
    
    /**
     * Update the combobox containing the different film sessions
     * @param sessions 
     */
    private void updateSessionCMB(ArrayList<FilmSession> sessions) {
        cmbSessions.setItems(FXCollections.observableArrayList(sessions));
        
        cmbSessions.setCellFactory(new Callback<ListView<FilmSession>, ListCell<FilmSession>>() {
            @Override
            public ListCell<FilmSession> call(ListView<FilmSession> param) {
                final ListCell<FilmSession> cell = new ListCell<FilmSession>() {
                    @Override
                    protected void updateItem(FilmSession item, boolean empty) {
                        super.updateItem(item, false);
                        if (item != null) {
                            setText((new SimpleDateFormat("dd-M-yyyy kk:mm").format(item.getDiffusionDate().getTime())));
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        cmbSessions.setConverter(new StringConverter<FilmSession>() {
            @Override
            public String toString(FilmSession object) {
                if(object == null) {
                    return null;
                } else {
                    return new SimpleDateFormat("dd-M-yyyy kk:mm").format(object.getDiffusionDate().getTime()) ;
                }
            }

            @Override
            public FilmSession fromString(String string) {
                return null;
            }
        });
        
        cmbSessions.setOnAction(e -> {
            try {
                ComboBox cmb = (ComboBox)e.getSource();
                FilmSession session = (FilmSession)cmb.getSelectionModel().getSelectedItem();
                System.out.println(session.getMovie().getTitle());
                updateQuantityCMB(session);
            } catch (NullPointerException ex) {
                cmbQuantity.setDisable(true);
                totalPrice.setText("0€");
            }
        });
    }
    
    /**
     * Update the combox box choices with the quantity of ticket for the session selected
     * @param session 
     */
    private void updateQuantityCMB(FilmSession session) {
        cmbQuantity.setDisable(false);
        cmbQuantity.setItems(FXCollections.observableArrayList(IntStream.range(1, session.getTicketQuantity()).boxed().collect(Collectors.toList())));
        cmbQuantity.setOnAction(e->{
            try {
                double discount =  (session.getMovie().getDiscount()!=null ?session.getMovie().getDiscount().getValue():0)*session.getMovie().getTicketPrice() ;
                double price = (session.getMovie().getTicketPrice() - discount)*cmbQuantity.getSelectionModel().getSelectedItem();
                totalPrice.setText(Double.toString(round(price)));
            } catch (NullPointerException ex) {
                totalPrice.setText("0€");
            }
         });
    }
    
    /**
     * Redirect the customer to the payment screen
     * Initialize the informations needed for this screen
     * @param event
     * @throws IOException 
     */
    @FXML
    void goToPayment(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PaymentScreen.fxml"));       
        OOP_Cinema.addScene("payment", loader.load());
        PaymentScreenController controller = loader.getController();
        controller.setCustomer(this.customer);
        controller.setCart(this.cart);
        OOP_Cinema.changeScene("payment");
    }
    
    /**
     * Round the value to have double with two digit after the coma
     * @param val
     * @return double: rounded result
     */
    private double round(double val) {
        val = val * 100;
        val = Math.round(val);
        return val / 100;
    }
    
    /**
     * Update the number of item in the text holder
     */
    private void updateNbItemCart() {
        this.itemInCart.setText(Integer.toString(this.cart.size()));
    }
    
}
