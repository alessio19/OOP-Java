package Model.product;

/**
 * @author Alessio
 * @author Adam
 */
public class Discount {
    private int id;
    private double value;

    public Discount(int id, double value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }
}
