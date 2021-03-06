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
 * details: DAO for the table Movie in the database, manage the insertion / selection / update / deletion of different movie in the DB
 */
public class MovieDAO {
    private Connection connection;  
    
    /**
     * Constructor
     */
    public MovieDAO() {
        this.connection = DBConnection.getConnection(); 
    }
    
    /**
     * Add a movie in the database
     * @param movie
     * @return boolean: result
     */
    public boolean addMovie(Movie movie) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movie (details, diffusionStart, diffusionEnd, ticketPrice, title, author, releaseDate, discountId, movieGenreId, movieImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, movie.getDetails());
            preparedStatement.setTimestamp(2, new Timestamp(movie.getDiffusionStart().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(movie.getDiffusionEnd().getTime()));
            preparedStatement.setDouble(4, movie.getTicketPrice());
            preparedStatement.setString(5, movie.getTitle());
            preparedStatement.setString(6, movie.getAuthor());
            preparedStatement.setTimestamp(7, new Timestamp(movie.getRealeaseDate().getTime()));
            if(movie.getDiscount() != null){
                preparedStatement.setInt(8, movie.getDiscount().getId());
            } else {
                preparedStatement.setNull(8, java.sql.Types.NULL);
            }
            preparedStatement.setInt(9, movie.getGenre().ordinal());
            preparedStatement.setString(10, movie.getImage());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     *  Retrieve movies from the database
     * @return ArrayList of Movie: movies
     */
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
                        result.getDate("releaseDate"),
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
    
    /**
     * Retrieve a movie by its ID
     * @param id
     * @return Movie: result
     */
    public Movie getMovieById(int id) {
        Movie movie = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Movie WHERE idMovie = "+id+";"); 
            result.next();
            Discount discount = Integer.valueOf(result.getInt("discountId")) != 0 ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
            
            
            movie= new Movie(
                    result.getInt("idMovie"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getDate("releaseDate"),
                    discount,
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
    
    /**
     *  Retrieve a movie by its author and by its title
     * @param title
     * @param author
     * @return Movie: result
     */
    public Movie getMovieByTitleAuthor(String title, String author) {
        Movie movie = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Movie WHERE title = '"+title+"' AND author = '" + author + "';"); 
            result.next();
            Discount discount = Integer.valueOf(result.getInt("discountId")) != 0 ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
            
            
            movie= new Movie(
                    result.getInt("idMovie"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getDate("releaseDate"),
                    discount,
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
    
    /**
     * Update a movie in the database
     * @param movie
     * @return boolean: result
     */
    public boolean updateMovie(Movie movie) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Movie SET diffusionStart = ?, diffusionEnd = ?, ticketPrice = ? WHERE idMovie = ?");
            ps.setTimestamp(1, new Timestamp(movie.getDiffusionStart().getTime()));
            ps.setTimestamp(2, new Timestamp(movie.getDiffusionEnd().getTime()));
            ps.setDouble(3, movie.getTicketPrice());
            ps.setInt(4, movie.getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Delete a movie from the database
     * @param movie
     * @return boolean: result
     */
    public boolean deleteMovie(Movie movie) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movie WHERE idMovie = ?;");            
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
