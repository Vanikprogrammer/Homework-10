package score;

/**
 * Created by 1 on 24.08.2017.
 */
public class Fruts {
    private Type type;

    public enum Type {Strawberry, Apple, Pear, Banana, Orange, Pineapple, Limon, Kiwi, Garnet, Peach};
    private int shelfLife;
    private String date;
    private double price;

    public Fruts(Type type, int shelfLife, String date, double price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }
    public Fruts(Type strawberry){}

    public String getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getShelfLife() {
        return shelfLife;
    }
}
