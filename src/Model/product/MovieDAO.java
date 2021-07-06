package Model.product;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class MovieDAO {
    private Connection connection;  
    
    public MovieDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addMovie(Movie movie) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Movie (details, diffusionStart, diffusionEnd, ticketPrice, title, author, releaseDate, discountId, movieGenreId, movieImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, movie.getDetails());
            preparedStatement.setTimestamp(2, new Timestamp(movie.getDiffusionStart().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(movie.getDiffusionEnd().getTime()));
            preparedStatement.setDouble(4, movie.getTicketPrice());
            preparedStatement.setString(5, movie.getTitle());
            preparedStatement.setString(6, movie.getAuthor());
            preparedStatement.setTimestamp(7, new Timestamp(movie.getRealeaseDate().getTime()));
            preparedStatement.setInt(8, movie.getDiscount().getId());
            preparedStatement.setInt(9, movie.getGenre().ordinal());
            preparedStatement.setString(10, movie.getImage());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public ArrayList<Movie> getMovies() {
        ResultSet result = null;
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            result = connection.createStatement().executeQuery("SELECT * FROM Movie");
            while (result.next()) {      
                Discount discount = result.getInt("discountId") != 0 ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
                movies.add(new Movie(
                        result.getInt("idMovie"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDate("realeaseDate"),
                        discount,
                        result.getString("details"),
                        result.getDate("diffusionStart"),
                        result.getDate("diffusionEnd"),
                        result.getDouble("ticketPrice"),
                        MovieGenre.values()[result.getInt("movieGenreId")-1],
                        result.getString("movieImage")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public Movie getMovieById(int id) {
        Movie movie = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Movie WHERE idMovie = "+id+";"); 
            //Discount discount = Integer.valueOf(result.getInt("discountId")) != null ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
            result.next();
            movie= new Movie(
                    result.getInt("idMovie"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getDate("releaseDate"),
                    //discount,
                    null,
                    result.getString("details"),
                    result.getDate("diffusionStart"),
                    result.getDate("diffusionEnd"),
                    result.getDouble("ticketPrice"),
                    MovieGenre.values()[result.getInt("movieGenreId")-1],
                    result.getString("movieImage")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }
    
}
