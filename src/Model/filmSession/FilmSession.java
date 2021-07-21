/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.filmSession;

import Model.product.Movie;
import java.util.Calendar;

/**
 *
 * @author Alessio
 */
public class FilmSession {
    
    private int idFilmSession;
    private Movie movie;    
    private Calendar diffusionDate;
    private int ticketQuantity;

    public FilmSession(int idFilmSession, Movie movie, Calendar diffusionDate, int ticketQuantity) {
        this.idFilmSession = idFilmSession;
        this.movie = movie;
        this.diffusionDate = diffusionDate;
        this.ticketQuantity = ticketQuantity;
    }

    public int getIdFilmSession() {
        return idFilmSession;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Calendar getDiffusionDate() {
        return diffusionDate;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public void setDiffusionDate(Calendar diffusionDate) {
        this.diffusionDate = diffusionDate;
    }

    @Override
    public String toString() {
        return movie.toString();
    }
    
}
