package Controller;

import Model.employee.Employee;
import Model.product.Discount;
import Model.product.DiscountDAO;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 *
 * @author Alessio
 */
public class MainMenuEmployeeController {

    @FXML
    private Label nameLabel;

    @FXML
    private Pagination paginationCurrentMovies;

    @FXML
    private TextField DiscountTextField;

    @FXML
    private Label currentPrice;

    @FXML
    private Label newPrice;

    @FXML
    private ChoiceBox<String> choiceBoxMovie;
    
    @FXML
    private Label errorDiscountAlreadyExisting;
    
    private ArrayList<Movie> movies;
    private Employee employee;
    private Movie movieForDiscount;
    
    /**
     * Initialize items
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
       
       for (int i=0; i<this.movies.size(); ++i) {
           this.choiceBoxMovie.getItems().add(this.movies.get(i).getTitle());
       }
    }    
    
    /**
     * setter
     * set the employee and initalize the text holder with the name and last name
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.nameLabel.setText(this.employee.getName() + " " + this.employee.getLastName());
    }
    
    /**
     * Redirect the employee to the user list screen
     * @param event 
     */
    @FXML
    void handleUserList(MouseEvent event) {
        try {
            OOP_Cinema.addScene("userList", FXMLLoader.load(getClass().getResource("/View/UsersList.fxml")));
            OOP_Cinema.changeScene("userList");
        } catch (IOException ex) {
              System.out.println(ex);
        }
    }    
    
    /**
     * Create a movie container element
     * @param movie
     * @return Pane: movie container
     */
    private Pane getMovieContainer(Movie movie) {
        Pane pane = new Pane();
        Rectangle r = new Rectangle(200, 250);
        Rectangle title = new Rectangle(190, 40);
        Label titleLabel = new Label(movie.getTitle());
        Rectangle edit = new Rectangle(170,50);
        Label editLabel = new Label("Edit");
        edit.setOnMouseClicked(e -> {
            try {
                this.editMovie(movie);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        editLabel.setOnMouseClicked(e -> {
            try {
                this.editMovie(movie);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        r.setOnMouseClicked(e -> {
            this.discountHandler(movie);
        });

        r.getStyleClass().add("roundBorder");
        r.setFill(new ImagePattern(new Image(movie.getImage())));
        title.getStyleClass().add("roundBorder");
        title.setFill(Color.WHITE);
        title.setLayoutY(15);
        title.setLayoutX(5);
        titleLabel.setLayoutY(20);
        titleLabel.setLayoutX(20);
        titleLabel.setMinWidth(155);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(Font.font("System",FontWeight.BOLD, 14));
        edit.getStyleClass().add("roundBorder");
        edit.setFill(Paint.valueOf("#6070ff"));
        edit.setLayoutX(10);
        edit.setLayoutY(180);
        editLabel.setLayoutX(78);
        editLabel.setLayoutY(190);
        editLabel.setFont(Font.font("System",FontWeight.BOLD, 20));
        editLabel.setTextFill(Paint.valueOf("#ffffff"));

        pane.getChildren().add(r);
        pane.getChildren().add(title);
        pane.getChildren().add(titleLabel);
        pane.getChildren().add(edit);
        pane.getChildren().add(editLabel);

        return pane;
    }
    
    /**
     * Redirect the employee to the edit movie screen (with the selected movie)
     * @param m
     * @throws IOException 
     */
    private void editMovie(Movie m) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EditMovie.fxml"));       
        OOP_Cinema.addScene("editMovie", loader.load());
        EditMovieController controller = loader.getController();
        controller.setMovie(m);
        OOP_Cinema.changeScene("editMovie");
    }
    
    /**
     * Initialize value of discount and warn the employee of an already existing discount
     * @param m 
     */
    private void discountHandler(Movie m) {
        if (m.getDiscount() != null)
            this.errorDiscountAlreadyExisting.setVisible(true);
        else
            this.errorDiscountAlreadyExisting.setVisible(false);
        this.choiceBoxMovie.setValue(m.getTitle());
        this.currentPrice.setText(Double.toString(m.getTicketPrice()));      
        movieForDiscount = m;
    }
    
    /**
     * Handle chamgement in a discount value
     * @param event 
     */
    @FXML
    void DiscountValueCHange(KeyEvent event) {
        try {
            if (!Double.isNaN(Double.parseDouble(this.DiscountTextField.getText()))) {
                this.newPrice.setText(Double.toString(Double.parseDouble(this.currentPrice.getText()) * Double.parseDouble(this.DiscountTextField.getText())));
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Apply a new discount to a movie
     * @param event 
     */
    @FXML
    void applyDiscount(MouseEvent event) {
        boolean result = new DiscountDAO().applyDiscount(new Discount(Double.parseDouble(this.DiscountTextField.getText())), movieForDiscount);
        System.out.println(result);
    }
    
    /**
     * Redirect the employee to the movie list screen
     * @param event
     * @throws IOException 
     */
    @FXML
    void movieListBtn(MouseEvent event) throws IOException {  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MovieListMenu.fxml"));       
        OOP_Cinema.addScene("movieListMenu", loader.load());
        MovieListMenuController controller = loader.getController();
        controller.setEmployee(employee);
        OOP_Cinema.changeScene("movieListMenu");
    }
    
    @FXML
    void logOutBtn(MouseEvent event) {
        OOP_Cinema.changeScene("login");
    }
    
}
