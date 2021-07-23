package Model.product;

/**
 * @author Alessio
 * @author Adam
 * details: class containing the information mandatory to have a discount of a movie
 */
public class Discount {
    private int id;
    private double value;

    /**
     * Constructor
     * @param id
     * @param value
     */
    public Discount(int id, double value) {
        this.id = id;
        this.value = value;
    }
    
    /**
     * Constructor
     * @param value
     */
    public Discount(double value) {
        this.value = value;
    }

    /**
     * getter
     * @return int: ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter
     * @return double: value
     */
    public double getValue() {
        return value;
    }

    /**
     * toString
     * @return String: percentage
     */
    @Override
    public String toString() {
        return this.value*100 + "%";
    }
    
    
}
