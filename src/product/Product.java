package product;

import java.util.Date;

public abstract class Product {
    protected int id;
    protected String title;
    protected String author;
    protected Date realeaseDate;
    protected Discount discount;
    protected int quantityleft;

    public Product(int id, String title, String author, Date realeaseDate, Discount discount, int quantityleft) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.realeaseDate = realeaseDate;
        this.discount = discount;
        this.quantityleft = quantityleft;
    }
}
