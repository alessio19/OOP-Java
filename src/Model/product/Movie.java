package Model.product;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 * details: Class of the main product in the cinema 
 */
public class Movie extends Product{
    private String details;
    private Date diffusionStart, diffusionEnd; 
    private double ticketPrice;
    private MovieGenre genre;
    private String image;

    /**
     * Constructor
     * @param id
     * @param title
     * @param author
     * @param realeaseDate
     * @param discount
     * @param details
     * @param diffusionStart
     * @param diffusionEnd
     * @param ticketPrice
     * @param genre
     * @param image
     */
    public Movie(int id, String title, String author, Date realeaseDate, Discount discount, String details, Date diffusionStart, Date diffusionEnd, double ticketPrice, MovieGenre genre, String image) {
        super(id, title, author, realeaseDate, discount);
        this.details = details;
        this.diffusionStart = diffusionStart;
        this.diffusionEnd = diffusionEnd;
        this.ticketPrice = ticketPrice;
        this.genre = genre;
        this.image = image;
    }

    /**
     *  getter
     * @return String: details
     */
    public String getDetails() {
        return details;
    }

    /**
     *  getter
     * @return double: ticketPrice
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     *  getter
     * @return MovieGenre: genre
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     *  getter
     * @return Date: diffusionStart
     */
    public Date getDiffusionStart() {
        return diffusionStart;
    }

    /**
     * getter
     * @return Date: diffusionEnd
     */
    public Date getDiffusionEnd() {
        return diffusionEnd;
    }

    /**
     * getter
     * @return String: image
     */
    public String getImage() {
        return image;
    }

    /**
     * setter
     * @param diffusionStart
     */
    public void setDiffusionStart(Date diffusionStart) {
        this.diffusionStart = diffusionStart;
    }

    /**
     * setter
     * @param diffusionEnd
     */
    public void setDiffusionEnd(Date diffusionEnd) {
        this.diffusionEnd = diffusionEnd;
    }

    /**
     * setter
     * @param ticketPrice
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
        
}
