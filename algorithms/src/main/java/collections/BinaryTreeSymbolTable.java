package collections;

import java.util.ArrayList;
import java.util.List;

/**
 * A symbol tree implementation based on the binary tree.
 */
public class BinaryTreeSymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private Node<K, V> root = null;

    @Override
    public void put(K k, V v) {
        root = put(root, k, v);
    }

    private Node<K, V> put(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<>(key, value, 1);
        }
        if (key.compareTo(current.key) > 0) {
            current.right = put(current.right, key, value);
        } else if (key.compareTo(current.key) < 0) {
            current.left = put(current.left, key, value);
        } else {
            current.value = value;
        }
        current.size = 1 + size(current.left) + size(current.right);
        return current;
    }

    @Override
    public V get(K k) {
        return get(root, k);
    }

    private V get(Node<K, V> current, K key) {
        if (current == null) {
            return null;
        }
        if (key.compareTo(current.key) > 0) {
            return get(current.right, key);
        } else if (key.compareTo(current.key) < 0) {
            return get(current.left, key);
        } else {
            return current.value;
        }
    }

    @Override
    public void delete(K k) {
        root = delete(root, k);
    }

    private Node<K, V> delete(Node<K, V> current, K key) {
        if (current == null) {
            return null;
        }
        if (key.compareTo(current.key) > 0) {
            // goto right to remove the node
            current.right = delete(current.right, key);
        } else if (key.compareTo(current.key) < 0) {
            // goto left to remove the node
            current.left = delete(current.left, key);
        } else {
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            Node<K, V> t = current;
            current = min(t.right);
            current.right = deleteMin(t.right);
            current.left = t.left;
        }
        current.size = 1 + size(current.right) + size(current.left);
        return current;
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
            throw new RuntimeException("Three is empty");
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
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        root = deleteMin(root);
    }

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
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> current) {
        if (current.right == null) {
            return current.left;
        }
        current.right = deleteMax(current.right);
        current.size = 1 + size(current.left) + size(current.right);
        return current;
    }

    @Override
    public int size(K lo, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        final List<K> keys = new ArrayList<>();
        keys(root, keys, lo, hi);
        return keys;
    }

    private void keys(Node<K, V> current, List<K> keys, K lo, K hi) {
        if (current == null) {
            return;
        }
        final int compareLo = lo.compareTo(current.key);
        final int compareHi = hi.compareTo(current.key);
        // need to get keys between lo and hi
        if (compareLo < 0) {
            // need to go deeper by the left branch
            keys(current.left, keys, lo, hi);
        }
        if (compareLo <= 0 && compareHi >= 0) {
            keys.add(current.key);
        }
        if (compareHi > 0) {
            keys(current.right, keys, lo, hi);
        }
    }

    private class Node<K, V> {
        private final K key;
        private V value;

        private Node<K, V> left;
        private Node<K, V> right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
}
