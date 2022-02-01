package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {


    private final double a;
    private final double b;

    public HashQuadraticProbing() {
        super();
        this.a = 1;
        this.b = 1;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int k = key % m;
        int hash = (int) ((k + a * i + b * i * i) % m);
        hash = hash < 0 ? -hash : hash;
        return hash;
    }
}
