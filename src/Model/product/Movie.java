package Model.product;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 */
public class Movie extends Product{
    private String details;
    private Date diffusion; 
    private double ticketPrice;
    private MovieGenre genre;

    public Movie(int id, String title, String author, Date realeaseDate, Discount discount, int quantityleft, String details, Date diffusion, double ticketPrice, MovieGenre genre) {
        super(id, title, author, realeaseDate, discount, quantityleft);
        this.details = details;
        this.diffusion = diffusion;
        this.ticketPrice = ticketPrice;
        this.genre = genre;
    }

    public String getDetails() {
        return details;
    }

    public Date getDiffusion() {
        return diffusion;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public MovieGenre getGenre() {
        return genre;
    }
}
