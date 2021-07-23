package Model.filmSession;

import Model.dataAccessModule.DBConnection;
import Model.product.MovieDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Alessio
 * @author Adam
 * details: DAO for the table film session in the database, manage the insertion / update / selection and deletion of different film session in the DB
 */
public class FilmSessionDAO {
    
    private Connection connection;  

    /**
     * Constructor
     */
    public FilmSessionDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Retrieve a film session by its ID
     * @param id
     * @return FilmSession: result
     */
    public FilmSession getFilmSessionById(int id) {
        FilmSession filmSession = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession WHERE idFilmSession = "+id+";");             
            result.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(result.getTimestamp("diffusionDate").getTime());
            filmSession = new FilmSession(
                    result.getInt("idFilmSession"),
                    (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                    calendar,
                    result.getInt("ticketQuantity")
            );            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSession;
    }
    
    /**
     * Retrieve all film sessions
     * @return ArrayList of FilmSession: result
     */
    public ArrayList<FilmSession> getFilmSessions() {
        ArrayList<FilmSession> filmSessions = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession;");    
            while (result.next()) { 
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(result.getTimestamp("diffusionDate").getTime());
                filmSessions.add(new FilmSession(
                        result.getInt("idFilmSession"),
                        (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                        calendar,
                        result.getInt("ticketQuantity")
                ));      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSessions;
    }
    
    /**
     * Insert a film session in the database
     * @param filmSession
     * @return boolean: result
     */
    public boolean addFilmSession(FilmSession filmSession) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FilmSession (idMovie, diffusionDate, ticketQuantity) VALUES (?, ?, ?);");            
            preparedStatement.setInt(1, filmSession.getMovie().getId());
            preparedStatement.setTimestamp(2, new Timestamp(filmSession.getDiffusionDate().getTimeInMillis()));
            preparedStatement.setInt(3, filmSession.getTicketQuantity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieve all FilmSession of a movie
     * @param id
     * @return ArrayList of FilmSession: result
     */
    public ArrayList<FilmSession> getFilmSessionByMovieId(int id) {
        ArrayList<FilmSession> filmSessions = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession WHERE idMovie = "+id+" ORDER BY diffusionDate;");      
            while (result.next()) { 
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(result.getTimestamp("diffusionDate").getTime());
                filmSessions.add(new FilmSession(
                        result.getInt("idFilmSession"),
                        (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                        calendar,
                        result.getInt("ticketQuantity")
                ));      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSessions;
    }
    
    /**
     * Delete a film session based on its ID
     * @param id
     * @return boolean: result
     */
    public boolean deleteFilmSessionById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FilmSession WHERE idFilmSession = ?;");            
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update a film session
     * @param session
     * @return boolean: result
     */
    public boolean updateFilmSession(FilmSession session) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE FilmSession SET ticketQuantity = ?, diffusionDate = ? WHERE idFilmSession = ?;");            
            preparedStatement.setInt(1, session.getTicketQuantity());
            preparedStatement.setTimestamp(2, new Timestamp(session.getDiffusionDate().getTimeInMillis()));
            preparedStatement.setInt(3, session.getIdFilmSession());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
