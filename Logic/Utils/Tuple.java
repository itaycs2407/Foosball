package Utils;

public class Tuple<X , Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X GetX(){return x;}
    public Y GetY(){return y;}
}
