package Controller;

import Model.employee.Employee;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class MainMenuEmployeeController {
    @FXML
    private ImageView logo;

    @FXML
    private Label nameLabel;

    @FXML
    private Pagination paginationCurrentMovies;

    @FXML
    private TextField MovieDiscount;

    @FXML
    private TextField DiscountTextField;

    @FXML
    private Label currentPrice;

    @FXML
    private Label newPrice;

    @FXML
    private Label prototionCode;

    @FXML
    private ImageView MovieButton;

    @FXML
    private ImageView ClientButton;

    @FXML
    private Button applyButton;
    
    private ArrayList<Movie> movies;
    private Employee employee;
    
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
    
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.nameLabel.setText(this.employee.getName() + " " + this.employee.getLastName());
    }
    
    @FXML
    void handleUserList(MouseEvent event) {
        try {
            OOP_Cinema.addScene("userList", FXMLLoader.load(getClass().getResource("/View/UsersList.fxml")));
            OOP_Cinema.changeScene("userList");
        } catch (IOException ex) {
              System.out.println(ex);
        }
    }
    
    
    private Pane getMovieContainer(Movie movie) {
        Pane pane = new Pane();
        Rectangle r = new Rectangle(200, 250);
        Rectangle title = new Rectangle(190, 40);
        Label titleLabel = new Label(movie.getTitle());
        Rectangle edit = new Rectangle(170,50);
        Label editLabel = new Label("Edit");

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
    
}