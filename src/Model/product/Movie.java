package Model.product;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 */
public class Movie extends Product{
    private String details;
    private Date diffusionStart, diffusionEnd; 
    private double ticketPrice;
    private MovieGenre genre;
    private String image;

    public Movie(int id, String title, String author, Date realeaseDate, Discount discount, String details, Date diffusionStart, Date diffusionEnd, double ticketPrice, MovieGenre genre, String image) {
        super(id, title, author, realeaseDate, discount);
        this.details = details;
        this.diffusionStart = diffusionStart;
        this.diffusionEnd = diffusionEnd;
        this.ticketPrice = ticketPrice;
        this.genre = genre;
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Date getDiffusionStart() {
        return diffusionStart;
    }

    public Date getDiffusionEnd() {
        return diffusionEnd;
    }

    public String getImage() {
        return image;
    }

    public void setDiffusionStart(Date diffusionStart) {
        this.diffusionStart = diffusionStart;
    }

    public void setDiffusionEnd(Date diffusionEnd) {
        this.diffusionEnd = diffusionEnd;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
}
