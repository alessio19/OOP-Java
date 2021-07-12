package Controller;

/**
 * @author Alessio
 * @author Adam
 */
import Model.customer.Customer;
import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class MainMenuCustomerController {
    
    private Customer customer;
    private ArrayList<Movie> movies;
    private ArrayList<Order> orders;

    @FXML
    private ImageView logo;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField searchBarField;

    @FXML
    private ImageView magnifyingGlass;

    @FXML
    private TextArea textAreaMovie;
    
    @FXML
    private TextArea textAreaOrder;

    @FXML
    private Label exampleTitle;

    @FXML
    private Label exampleGenre;

    @FXML
    private Label exampleRelease;

    @FXML
    private Label exampleOrderDate;
    
    @FXML
    private Rectangle MovieSession;

    @FXML
    private Label MovieSessionTitle;

    @FXML
    private Label MovieSessionDetails;
    
    @FXML
    private Pagination paginationCurrentMovies;
    
    @FXML
    private ComboBox<FilmSession> cmbSessions;

    @FXML
    private Button orderBtn;

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
    public void initialize() {
        OOP_Cinema.getScene().getStylesheets().add("/Resources/css/movie.css");
        this.movies = new MovieDAO().getMovies();
        int page = movies.size()%3 ==0 ? 0 : 1;
        paginationCurrentMovies.setPageCount((movies.size()/3 + page));
        paginationCurrentMovies.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
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
            }
        });               
    }
    
    public MainMenuCustomerController() {
        
    }  

    @FXML
    void ActionHandler(ActionEvent event) {
        
    }
    
    @FXML
    void profileButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Profile.fxml"));       
        OOP_Cinema.addScene("profile", loader.load());
        ProfileController controller = loader.getController();
        controller.setCustomer(this.customer);  
        OOP_Cinema.changeScene("profile");
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.nameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
        this.setCustomerOrders();
    }
    
    private void setCustomerOrders() {
        this.orders = new OrderDAO().getOrdersForUsrId(this.customer.getId());
        this.orders.forEach(order -> {
            this.textAreaOrder.setText(this.textAreaOrder.getText()
                    + order.getId() + "\t"
                    + order.getProduct().getTitle() + "\t"
                    + order.getIquantity() + "\t"
                    + order.getPayment().getPrice() + "\t" 
                    + order.getPayment().getStatus().toString() + "\n"
            );
        });
    }
    
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

    @FXML
    void handleOrderBtn(ActionEvent event) {

    }
    
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
                            setText(item.getDiffusionDate().toString());
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
                    return object.getDiffusionDate().toString();
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
    
    private void updateQuantityCMB(FilmSession session) {
        cmbQuantity.setDisable(false);
        cmbQuantity.setItems(FXCollections.observableArrayList(IntStream.range(1, session.getTicketQuantity()).boxed().collect(Collectors.toList())));
        cmbQuantity.setOnAction(e->{
            try {
                double discount =  (session.getMovie().getDiscount()!=null ?session.getMovie().getDiscount().getValue():0)*session.getMovie().getTicketPrice() ;
                double price = (session.getMovie().getTicketPrice() - discount)*cmbQuantity.getSelectionModel().getSelectedItem();
                totalPrice.setText(round(price)+"€");
            } catch (NullPointerException ex) {
                totalPrice.setText("0€");
            }
         });
    }
    
    private double round(double val) {
        val = val * 100;
        val = Math.round(val);
        return val / 100;
    }
    
}
