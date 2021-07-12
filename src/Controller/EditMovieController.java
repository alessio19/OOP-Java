package Controller;

import Model.product.Movie;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class EditMovieController {
    
    private Movie movie;

    @FXML
    private ImageView logo;

    @FXML
    private Label nameLabel;

    @FXML
    private Rectangle movieImg;

    @FXML
    private Label titleName;

    @FXML
    private DatePicker endOfDiffusion;

    @FXML
    private DatePicker startOfDiffusion;

    @FXML
    private TextField ticketPrice;

    @FXML
    private Label movieDetails;

    @FXML
    private DatePicker diffusionDateAdd;

    @FXML
    private TextField quantityTicketAdd;

    @FXML
    private TextField quantityTicketEdit;

    @FXML
    private DatePicker diffusionDateEdit;

    @FXML
    void ApplyModifMovie(MouseEvent event) {

    }

    @FXML
    void addFilmSession(MouseEvent event) {

    }

    @FXML
    void applyEditFilmSession(MouseEvent event) {

    }

    @FXML
    void backToPreviousFrame(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuEmployee");
    }

    @FXML
    void deleteFilmSession(MouseEvent event) {

    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        this.movieImg.setFill(new ImagePattern(new Image(this.movie.getImage())));
        this.titleName.setText(this.movie.getTitle());
        this.titleName.setTextAlignment(TextAlignment.CENTER);
        this.titleName.setAlignment(Pos.CENTER);
        this.movieDetails.setText(this.movie.getDetails());
        this.ticketPrice.setText(Double.toString(this.movie.getTicketPrice()));
        this.startOfDiffusion.setValue(LocalDate.parse((CharSequence) this.movie.getDiffusionStart().toString()));
        this.endOfDiffusion.setValue(LocalDate.parse((CharSequence) this.movie.getDiffusionEnd().toString()));
        this.initializeFilmSession();
    }    
    
    private void initializeFilmSession() {
        
    }

}
