package Model.product;

import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 */
public class Product {
    private int id;
    private String title;
    private String author;
    private Date realeaseDate;
    private Discount discount;

    public Product(int id, String title, String author, Date realeaseDate, Discount discount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.realeaseDate = realeaseDate;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public Discount getDiscount() {
        return discount;
    }
    
}
