package collections;

/**
 * A symbol table that supports ordering of the keys.
 */
public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {
    K min();

    K max();

    /**
     * Find the largest key that is less or equals to the provided key.
     * @param key
     * @return
     */
    K floor(K key);

    /**
     * Find the smallest key that is greater or equals to the provided key.
     * @param key
     * @return
     */
    K ceiling(K key);

    /**
     * Number of keys that less than the provided key.
     * @param key
     * @return
     */
    int rank(K key);

    /**
     * Select a key that has a provided rank.
     * @param rank
     * @return
     */
    K select(int rank);

    void deleteMin();

    void deleteMax();

    /**
     * The number of keys between lo and hi.
     * @param lo
     * @param hi
     * @return
     */
    int size(K lo, K hi);

    /**
     * A collection of keys between lo and hi in an ordered way.
     * @param lo
     * @param hi
     * @return
     */
    Iterable<K> keys(K lo, K hi);
}
