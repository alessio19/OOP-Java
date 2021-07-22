package Model.product;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 * details: super Class of every product available in the cinema
 * (For now there is only movies but we choose to have a project open for evolution)
 */
public class Product {
    private int id;
    private String title;
    private String author;
    private Date realeaseDate;
    private Discount discount;

    /**
     * Constructor
     * @param id
     * @param title
     * @param author
     * @param realeaseDate
     * @param discount
     */
    public Product(int id, String title, String author, Date realeaseDate, Discount discount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.realeaseDate = realeaseDate;
        this.discount = discount;
    }

    /**
     *  getter
     * @return int: id
     */
    public int getId() {
        return id;
    }

    /**
     * getter
     * @return String: title
     */
    public String getTitle() {
        return title;
    }

    /**
     *  getter
     * @return String: author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *  getter
     * @return Date: releaseDate
     */
    public Date getRealeaseDate() {
        return realeaseDate;
    }

    /**
     *  getter
     * @return Discount: discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     *  toString
     * @return String
     */
    @Override
    public String toString() {
        return title + " - " + author;
    }
    
    
    
}
