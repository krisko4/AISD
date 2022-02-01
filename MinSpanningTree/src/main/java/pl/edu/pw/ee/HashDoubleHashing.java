package pl.edu.pw.ee;


public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T>{


    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }


    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int k = key % m;
        int k1 = 1 + (key % (m - 3));
        int hash = (k + i * k1) % m;
        hash = hash < 0 ? -hash : hash;
        return hash;
    }

}
