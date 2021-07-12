/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.filmSession;

import Model.dataAccessModule.DBConnection;
import Model.product.MovieDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Alessio
 */
public class FilmSessionDAO {
    
    private Connection connection;  

    public FilmSessionDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public FilmSession getFilmSessionById(int id) {
        FilmSession filmSession = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession WHERE idFilmSession = "+id+";");             
            result.next();
            filmSession = new FilmSession(
                    result.getInt("idFilmSession"),
                    (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                    result.getDate("diffusionDate"),
                    result.getInt("ticketQuantity")
            );            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSession;
    }
    
    public ArrayList<FilmSession> getFilmSessions() {
        ArrayList<FilmSession> filmSessions = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession;");    
            while (result.next()) { 
                filmSessions.add(new FilmSession(
                        result.getInt("idFilmSession"),
                        (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                        result.getDate("diffusionDate"),
                        result.getInt("ticketQuantity")
                ));      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSessions;
    }
    
    public boolean addFilmSession(FilmSession filmSession) {
        PreparedStatement preparedStatement = null;         
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO FilmSession (idMovie, diffusionDate, ticketQuantity) VALUES (?, ?, ?);");            
            preparedStatement.setInt(1, filmSession.getMovie().getId());
            preparedStatement.setTimestamp(2, new Timestamp(filmSession.getDiffusionDate().getTime()));
            preparedStatement.setInt(3, filmSession.getTicketQuantity());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public ArrayList<FilmSession> getFilmSessionByMovieId(int id) {
        ArrayList<FilmSession> filmSessions = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM FilmSession WHERE idMovie = "+id+";");             
            while (result.next()) { 
                filmSessions.add(new FilmSession(
                        result.getInt("idFilmSession"),
                        (new MovieDAO().getMovieById(result.getInt("idMovie"))),
                        result.getDate("diffusionDate"),
                        result.getInt("ticketQuantity")
                ));      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmSessions;
    }
    
}
