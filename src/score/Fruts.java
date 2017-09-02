package score;

/**
 * Created by 1 on 24.08.2017.
 */
public class Fructs {
    private Type type;

    private enum Type {Strawberry, Apple, Pear, Banana, Orange, Pineapple, Limon, Kiwi, Garnet, Peach};
    private int shelfLife;
    private String date;
    private double price;

    public Fructs(Type type, int shelfLife, String date, double price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

}
