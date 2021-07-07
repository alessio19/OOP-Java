package Controller;

/**
 * @author Alessio
 * @author Adam
 */
import Model.customer.Customer;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class MainMenuCustomerController {
    
    private Customer customer;
    private ArrayList<Movie> movies;

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.nameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
    }
    
    private Pane getMovieContainer(Movie movie) {
        Pane pane = new Pane();
               
               Rectangle r = new Rectangle(200, 250);
               Rectangle title = new Rectangle(190, 75);
               Label titleLabel = new Label(movie.getTitle());
               Label descriptionLabel = new Label(movie.getDetails());
               
               r.getStyleClass().add("movieContainer");
               r.setFill(new ImagePattern(new Image(movie.getImage())));
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
               
               pane.getChildren().add(r);
               pane.getChildren().add(title);
               pane.getChildren().add(titleLabel);
               pane.getChildren().add(descriptionLabel);
               
               return pane;
    }
    
}
