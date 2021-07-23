package Model.filmSession;

import Model.product.Movie;
import java.util.Calendar;

/**
 * @author Alessio
 * @author Adam
 * details: Class containing the information of a film session in the cinema
 */
public class FilmSession {
    
    private int idFilmSession;
    private Movie movie;    
    private Calendar diffusionDate;
    private int ticketQuantity;

    /**
     * Constructor
     * @param idFilmSession
     * @param movie
     * @param diffusionDate
     * @param ticketQuantity
     */
    public FilmSession(int idFilmSession, Movie movie, Calendar diffusionDate, int ticketQuantity) {
        this.idFilmSession = idFilmSession;
        this.movie = movie;
        this.diffusionDate = diffusionDate;
        this.ticketQuantity = ticketQuantity;
    }

    /**
     * getter
     * @return int: ID
     */
    public int getIdFilmSession() {
        return idFilmSession;
    }

    /**
     * getter
     * @return Movie: movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * setter
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * getter
     * @return Calendar: diffusionDate
     */
    public Calendar getDiffusionDate() {
        return diffusionDate;
    }

    /**
     * getter
     * @return int: ticketQuantity
     */
    public int getTicketQuantity() {
        return ticketQuantity;
    }

    /**
     * setter
     * @param ticketQuantity
     */
    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    /**
     * setter
     * @param diffusionDate
     */
    public void setDiffusionDate(Calendar diffusionDate) {
        this.diffusionDate = diffusionDate;
    }

    /**
     * toString
     * @return String: movie name
     */
    @Override
    public String toString() {
        return movie.toString();
    }
    
}
