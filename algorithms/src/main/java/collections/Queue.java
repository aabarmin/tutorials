package collections;

public interface Queue<KEY extends Comparable<KEY>> {
    void insert(KEY v);

    KEY max();

    KEY delMax();

    boolean isEmpty();

    int size();
}
