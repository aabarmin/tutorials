package collections;

import java.util.ArrayList;
import java.util.Collection;

public class RedBlackTreeSymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root = null;

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<>(key, value, 1, RED);
        }
        final int compare = key.compareTo(current.key);
        if (compare > 0) {
            current.right = put(current.right, key, value);
        } else if (compare < 0) {
            current.left = put(current.left, key, value);
        } else {
            current.value = value;
        }

        // need to fix colors of the tree
        if (isRed(current.right) && !isRed(current.left)) {
            // a red edge is on the right side but should be on the left
            current = rotateLeft(current);
        }
        if (isRed(current.left) && isRed(current.left.left)) {
            // current nodes is a 4-node, it should be split into two 2-node
            current = rotateRight(current);
        }
        if (isRed(current.left) && isRed(current.right)) {
            // flip colors
            flipColors(current);
        }

        current.size = 1 + size(current.left) + size(current.right);
        return current;
    }

    private void flipColors(Node<K, V> current) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;
    }

    private Node<K, V> rotateRight(Node<K, V> current) {
        final Node<K, V> x = current.left;
        x.color = current.color;
        current.left = x.right;
        x.right = current;
        x.color = current.color;
        x.right.color = RED;
        x.size = current.size;
        current.size = 1 + size(current.left) + size(current.right);
        return x;
    }

    private Node<K, V> rotateLeft(Node<K, V> current) {
        Node<K, V> x = current.right;
        current.right = x.left;
        x.left = current;
        x.color = current.color;
        x.left.color = RED;
        x.size = current.size;
        current.size = 1 + size(current.left) + size(current.right);
        return x;
    }

    private boolean isRed(Node<K, V> current) {
        if (current == null) {
            return BLACK;
        }
        return current.color == RED;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> current, K key) {
        if (current == null) {
            return null;
        }
        final int compare = key.compareTo(current.key);
        if (compare < 0) {
            return get(current.left, key);
        } else if (compare > 0) {
            return get(current.right, key);
        } else {
            return current.value;
        }
    }

    @Override
    public void delete(K key) {
        if (!contains(key)) {
            return;
        }
        if (!isRed(root) && !isRed(root)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node<K, V> delete(Node<K, V> current, K key) {
        return null;
    }

    @Override
    public boolean contains(K k) {
        return get(k) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node<K, V> current) {
        if (current == null) {
            return 0;
        }
        return current.size;
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    @SuppressWarnings("Duplicates")
    private void keys(Node<K, V> current, Collection<K> keys, K lo, K hi) {
        if (current == null) {
            return;
        }
        final int compareLo = lo.compareTo(current.key);
        final int compareHi = hi.compareTo(current.key);
        if (compareLo < 0) {
            keys(current.left, keys, lo, hi);
        }
        if (compareLo <= 0 && compareHi >= 0) {
            keys.add(current.key);
        }
        if (compareHi > 0) {
            keys(current.right, keys, lo, hi);
        }
    }

    @Override
    public K min() {
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        return min(root).key;
    }

    private Node<K, V> min(Node<K, V> current) {
        if (current.left == null) {
            return current;
        }
        return min(current.left);
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        return max(root).key;
    }

    private Node<K, V> max(Node<K, V> current) {
        if (current.right == null) {
            return current;
        }
        return max(current.right);
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int rank) {
        return null;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    @SuppressWarnings("Duplicates")
    private Node<K, V> deleteMin(Node<K, V> current) {
        if (current.left == null) {
            return current.right;
        }
        current.left = deleteMin(current.left);
        current.size = 1 + size(current.left) + size(current.right);
        return current;
    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(K lo, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        final Collection<K> keys = new ArrayList<>();
        keys(root, keys, lo, hi);
        return keys;
    }

    private class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int size = 0;
        private boolean color = false;

        public Node(K key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }
}
