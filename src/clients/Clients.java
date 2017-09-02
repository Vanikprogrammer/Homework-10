package clients;

/**
 * Created by 1 on 02.09.2017.
 */
public class Clients {
    String name;
    private Type type;
    public enum Type {Strawberry, Apple, Pear, Banana, Orange, Pineapple, Limon, Kiwi, Garnet, Peach};
    double count;

    public Type getType() {
        return type;
    }

    public double getCount() {
        return count;
    }
}
