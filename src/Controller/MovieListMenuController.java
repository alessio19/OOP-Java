package Controller;

import Model.employee.Employee;
import Model.product.Movie;
import Model.product.MovieDAO;
import Model.product.MovieGenre;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * @author Alessio
 * @author Adam
 * details: Controller for the movie list screen view 
 */
public class MovieListMenuController {
    
    private ArrayList<Movie> movies;
    private Movie selectedMovie = null;
    private Employee employee;
    private final MovieDAO movieDAO = new MovieDAO();
    
    @FXML
    private TableView<Movie> movieListTableView;

    @FXML
    private TableColumn<Movie, String> movieTitleTabCol;

    @FXML
    private TableColumn<Movie, Date> movieStartDateTabCol;

    @FXML
    private TableColumn<Movie, Date> movieEndDateTabCol;

    @FXML
    private TableColumn<Movie, Double> moviePriceTabCol;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private TextField movieTitleTxtField;

    @FXML
    private TextField movieAuthorTxtField;

    @FXML
    private ComboBox<MovieGenre> movieGenreCmb;

    @FXML
    private TextField movieImgUrlTxtField;

    @FXML
    private TextArea movieDescriptiontTxtArea;

    @FXML
    private DatePicker movieStartDatePicker;

    @FXML
    private DatePicker movieEndDatePicker;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private TextField ticketPrice;
    
    /**
     * Initialize the items
     */
    @FXML
    public void initialize() {
        movies = movieDAO.getMovies();
        initMovieTable();
        movieGenreCmb.setConverter(new StringConverter<MovieGenre>() {
            @Override
            public String toString(MovieGenre object) {
                return object.name();
            }

            @Override
            public MovieGenre fromString(String string) {
                return MovieGenre.valueOf(string);
            }
        });
        movieGenreCmb.setItems(FXCollections.observableArrayList(MovieGenre.values()));
    }

    /**
     * setter
     * initialize values
     * @param emp
     */
    public void setEmployee(Employee emp) {
        this.employee = emp;
        nameLabel.setText(this.employee.getName() + " " + this.employee.getLastName());
    }

    /**
     * Add a new movie based on the user inputs
     * @param event 
     */
    @FXML
    void addMovieBtn(MouseEvent event) {
        Date start =  localDateToDate(movieStartDatePicker.getValue());
        Date end = localDateToDate(movieEndDatePicker.getValue());
        float price = 0;
        try {
            price = Math.round(Float.parseFloat(ticketPrice.getText())*100)/100;
        } catch (NumberFormatException e) {
            return;
        }
        if (
                movieTitleTxtField.getText().length()>0 &&
                movieAuthorTxtField.getText().length()>0 &&
                movieImgUrlTxtField.getText().length()>0 &&
                movieDescriptiontTxtArea.getText().length()>0 &&
                start != null &&
                end != null
         ) {
            Movie newMoviee = new Movie(
                    0,  
                    movieTitleTxtField.getText(), 
                    movieAuthorTxtField.getText(), 
                    new Date(), 
                    null, 
                    movieDescriptiontTxtArea.getText(), 
                    start, 
                    end, 
                    price, 
                    movieGenreCmb.getValue(), 
                    movieImgUrlTxtField.getText()
            );
            movieDAO.addMovie(newMoviee);
            updateTableView();
        }
    }

    /**
     * Delete the selected movie
     * @param event 
     */
    @FXML
    void deleteMovieBtn(MouseEvent event) {
         movieDAO.deleteMovie(selectedMovie);
         updateTableView();
    }
    
    /**
     * Redirect the employee to the main menu
     * @param event 
     */
    @FXML
    void exitBtn(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuEmployee");
    }
    
    /**
     * Initialize the movie table
     */
    private void initMovieTable() {
        movieTitleTabCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        moviePriceTabCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        movieStartDateTabCol.setCellValueFactory(new PropertyValueFactory<>("diffusionStart"));
        movieEndDateTabCol.setCellValueFactory(new PropertyValueFactory<>("diffusionEnd"));
        movieListTableView.setItems(FXCollections.observableArrayList(this.movies));
        movieListTableView.setOnMouseClicked((e)->{
            try {
                clickOnTable(movieListTableView.getSelectionModel().getSelectedItem());
            } catch (NullPointerException ex) {
                System.out.println(ex.getStackTrace());
            }
        });
    }

    /**
     * Handle click on table
     * @param selectedMovie 
     */
    private void clickOnTable(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
        selectedMovieTitle.setText(this.selectedMovie.getTitle());
    }
    
    /**
     * Update the table after the employee action
     */
    private void updateTableView() {
        this.movies = movieDAO.getMovies();
        movieListTableView.getItems().clear();
        movieListTableView.setItems(FXCollections.observableArrayList(this.movies));
    }
    
    /**
     * Convert local date to date
     * @param local
     * @return Date: date
     */
    private Date localDateToDate(LocalDate local) {
        return Date.from(Instant.from(local.atStartOfDay(ZoneId.systemDefault())));
    }
}
