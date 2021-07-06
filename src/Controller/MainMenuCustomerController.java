package Controller;

/**
 * @author Alessio
 * @author Adam
 */
import Model.customer.Customer;
import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MainMenuCustomerController {
    
    private Customer customer;
    private ArrayList<FilmSession> filmSessions;

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
    public void initialize() {
        this.filmSessions = new FilmSessionDAO().getFilmSessions(); 
       
        this.MovieSession.setFill(new ImagePattern(new Image(this.filmSessions.get(0).getMovie().getImage())));
        this.MovieSessionTitle.setText(this.filmSessions.get(0).getMovie().getTitle());
        this.MovieSessionDetails.setText(this.filmSessions.get(0).getMovie().getDetails());
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
    
}
