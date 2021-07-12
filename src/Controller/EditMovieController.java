package Controller;

import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import Model.product.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class EditMovieController {
    
    private Movie movie;
    private ArrayList<FilmSession> sessions;

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
    private GridPane GridSession;
    
    @FXML
    private ScrollPane filmSessionPane;

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
        this.startOfDiffusion.setValue(LocalDate.parse(this.movie.getDiffusionStart().toString()));
        this.endOfDiffusion.setValue(LocalDate.parse(this.movie.getDiffusionEnd().toString()));
        this.initializeFilmSession();
    }    
    
    private void initializeFilmSession() {
        sessions = new FilmSessionDAO().getFilmSessionByMovieId(this.movie.getId());
        for (int i=0; i<sessions.size()-1; ++i) {
            this.GridSession.add(new Label(Integer.toString(sessions.get(i).getIdFilmSession())), 0, i+1);
            this.GridSession.add(new Label(Integer.toString(sessions.get(i).getTicketQuantity())), 1, i+1);
            this.GridSession.add(new Label(sessions.get(i).getDiffusionDate().toString()), 2, i+1);
        }        
        this.GridSession.getChildren().forEach(cell -> {
            cell.setOnMouseClicked(e -> {
                this.clickOnGrid(e.getSource());
            });
        });
        this.GridSession.setVgap(50);
        this.filmSessionPane.fitToWidthProperty().setValue(Boolean.TRUE);
    }
    
    private void clickOnGrid(Object object) {        
        Node n = (Node) object;
        FilmSession session = this.sessions.get(GridPane.getRowIndex(n)-1);
        this.quantityTicketEdit.setText(Double.toString(session.getTicketQuantity()));
        this.diffusionDateEdit.setValue(LocalDate.parse(session.getDiffusionDate().toString()));
    }

}
