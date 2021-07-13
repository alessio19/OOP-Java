package Controller;

import Model.filmSession.FilmSession;
import Model.filmSession.FilmSessionDAO;
import Model.product.Movie;
import Model.product.MovieDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class EditMovieController {
    
    private Movie movie;
    private ArrayList<FilmSession> sessions;
    private FilmSession selectedFilmSession = null;
    private FilmSessionDAO filmSessionDAO = new FilmSessionDAO();

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
    private Label movieEditMessage;

    @FXML
    private Label sessionEditMessage;
    
    @FXML
    private Label sessionAddMessage;
    
     @FXML
    private Spinner<LocalTime> addFilmSessionTimeSpinner;

    @FXML
    private Spinner<LocalTime> editFilmSessionTimeSpinner;
    
    @FXML
    private TableView<FilmSession> sessionsTableView;

    @FXML
    void ApplyModifMovie(MouseEvent event) {
        double price = 0;
        try {
           price = Double.valueOf(ticketPrice.getText());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        if(endOfDiffusion.getValue() != null && startOfDiffusion.getValue() != null && price > 0) {
            Date start = Date.from(startOfDiffusion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(endOfDiffusion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            movie.setDiffusionStart(start);
            movie.setDiffusionEnd(end);
            movie.setTicketPrice(price);
            boolean queryResult = new MovieDAO().updateMovie(movie);
            if(queryResult) {
                movieEditMessage.setVisible(true);
                movieEditMessage.setTextFill(Color.GREEN);
                movieEditMessage.setText("Updated successfully");
            } else {
                 movieEditMessage.setVisible(true);
                 movieEditMessage.setTextFill(Color.RED);
                 movieEditMessage.setText("Something gone wrong");
            }
        } else {
            movieEditMessage.setVisible(true);
            movieEditMessage.setTextFill(Color.RED);
            movieEditMessage.setText("Please fill all data");
        }
    }

    @FXML
    void addFilmSession(MouseEvent event) {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityTicketAdd.getText());
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        if(diffusionDateAdd.getValue() != null && quantity > 0 && addFilmSessionTimeSpinner.getValue() != null) {
            
            Calendar diffusion = Calendar.getInstance();
            diffusion.set(
              diffusionDateAdd.getValue().getYear(), 
              diffusionDateAdd.getValue().getMonthValue()-1, 
              diffusionDateAdd.getValue().getDayOfMonth(), 
              addFilmSessionTimeSpinner.getValue().getHour(), 
              addFilmSessionTimeSpinner.getValue().getMinute()
            );
            boolean result = filmSessionDAO.addFilmSession(new FilmSession(0, movie, diffusion, quantity));
            if(result) {
                sessionAddMessage.setVisible(true);
                sessionAddMessage.setTextFill(Color.GREEN);
                sessionAddMessage.setText("Added successfully");
               updateSessionTable();
            } else {
                sessionAddMessage.setVisible(true);
                sessionAddMessage.setTextFill(Color.RED);
                sessionAddMessage.setText("Something gone wrong");
            }
        } else {
            sessionAddMessage.setVisible(true);
            sessionAddMessage.setTextFill(Color.RED);
            sessionAddMessage.setText("Please fill all data");
        }
    }

    @FXML
    void applyEditFilmSession(MouseEvent event) {
        int tickets = 0;
        try {
            tickets = Integer.parseInt(quantityTicketEdit.getText());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        if(tickets > 0 && diffusionDateEdit.getValue() != null && editFilmSessionTimeSpinner.getValue() != null) {
            System.out.println(tickets);
            Calendar diffusion = Calendar.getInstance();
            diffusion.set(
              diffusionDateEdit.getValue().getYear(), 
              diffusionDateEdit.getValue().getMonthValue()-1, 
              diffusionDateEdit.getValue().getDayOfMonth(), 
              editFilmSessionTimeSpinner.getValue().getHour(), 
              editFilmSessionTimeSpinner.getValue().getMinute()
            );
            this.selectedFilmSession.setTicketQuantity(tickets);
            this.selectedFilmSession.setDiffusionDate(diffusion);
            boolean result = this.filmSessionDAO.updateFilmSession(this.selectedFilmSession);
            if(result) {
                sessionEditMessage.setVisible(true);
                sessionEditMessage.setTextFill(Color.GREEN);
                sessionEditMessage.setText("Added successfully");
               updateSessionTable();
            } else {
                sessionEditMessage.setVisible(true);
                sessionEditMessage.setTextFill(Color.RED);
                sessionEditMessage.setText("Something gone wrong");
            }
        } else {
            sessionEditMessage.setVisible(true);
            sessionEditMessage.setTextFill(Color.RED);
            sessionEditMessage.setText("Please fill all data");
        }
    }

    @FXML
    void backToPreviousFrame(MouseEvent event) {
        OOP_Cinema.changeScene("mainMenuEmployee");
    }

    @FXML
    void deleteFilmSession(MouseEvent event) {
        if(selectedFilmSession != null) {
            boolean result = filmSessionDAO.deleteFilmSessionById(selectedFilmSession.getIdFilmSession());
            if(result) {
                sessionEditMessage.setVisible(true);
                sessionEditMessage.setTextFill(Color.GREEN);
                sessionEditMessage.setText("Session deleted");
                updateSessionTable();
            } else {
                sessionEditMessage.setVisible(true);
                sessionEditMessage.setTextFill(Color.RED);
                sessionEditMessage.setText("Something gone wrong");
            }
        } else {
            sessionEditMessage.setVisible(true);
            sessionEditMessage.setTextFill(Color.RED);
            sessionEditMessage.setText("Please select session");
        }
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
        sessions = filmSessionDAO.getFilmSessionByMovieId(this.movie.getId());
        TableColumn<FilmSession, Integer> sessionIdCol = new TableColumn<>("ID");
        sessionIdCol.setCellValueFactory(new PropertyValueFactory<>("idFilmSession"));
        TableColumn<FilmSession, Integer> sessionTicketCol = new TableColumn<>("Tickets");
        sessionTicketCol.setCellValueFactory(new PropertyValueFactory<>("ticketQuantity"));
        TableColumn<FilmSession, Calendar> sessionDateCol = new TableColumn<>("Session Date");
        sessionDateCol.setCellValueFactory(new PropertyValueFactory<>("diffusionDate"));
        sessionDateCol.setCellFactory(col -> new TableCell<FilmSession, Calendar>() {
            @Override
            protected void updateItem(Calendar date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(new SimpleDateFormat("dd-M-yyyy kk:mm").format(date.getTime()));
                }
            }
        });
        
        //Column Styles
        sessionDateCol.setMinWidth(214);
        sessionTicketCol.setMinWidth(60);
        sessionIdCol.setMinWidth(60);
        sessionDateCol.setStyle( "-fx-alignment: CENTER;");
        sessionTicketCol.setStyle( "-fx-alignment: CENTER;");
        sessionIdCol.setStyle( "-fx-alignment: CENTER;");
        
        sessionsTableView.setItems(FXCollections.observableArrayList(sessions));
        sessionsTableView.getColumns().addAll(sessionIdCol, sessionTicketCol, sessionDateCol);
        sessionsTableView.setOnMouseClicked((e)->{
            try {
                clickOnTable(sessionsTableView.getSelectionModel().getSelectedItem());
            } catch (NullPointerException ex) {
                System.out.println(ex.getStackTrace());
            }
        });
    }
    
    private void clickOnTable(FilmSession session) { 
        this.selectedFilmSession = session;
        this.quantityTicketEdit.setText(Double.toString(session.getTicketQuantity()));
        this.diffusionDateEdit.setValue(session.getDiffusionDate().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        editFilmSessionTimeSpinner.getValueFactory().setValue(LocalDateTime.ofInstant(session.getDiffusionDate().getTime().toInstant(), ZoneId.systemDefault()).toLocalTime());
    }
    
    private void updateSessionTable() {
        this.sessions =  this.filmSessionDAO.getFilmSessionByMovieId(this.movie.getId());
        this.sessionsTableView.getItems().clear();
        this.sessionsTableView.setItems(FXCollections.observableArrayList(this.sessions));
    }

}
