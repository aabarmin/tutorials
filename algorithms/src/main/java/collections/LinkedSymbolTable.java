package collections;

import java.util.ArrayList;
import java.util.List;

/**
 * A symbol table implementation base on a linked-list.
 */
public class LinkedSymbolTable<K, V> implements SymbolTable<K, V> {
    private Node<K, V> first = null;
    private Node<K, V> last = null;

    @Override
    public void put(K k, V v) {
        Node<K, V> current = first;
        if (current == null) {
            this.first = new Node<>(null, k, v);
            this.last = this.first;
            // it's a first node in a table
            return;
        }
        while (current != null) {
            if (current.key.equals(k)) {
                // change the value
                current.value = v;
                break;
            }
            current = current.next;
        }
        // add as last
        Node newNode = new Node<>(this.last, k, v);
        this.last.next = newNode;
        this.last = newNode;
    }

    @Override
    public V get(K k) {
        Node<K, V> current = this.first;
        while (current != null) {
            if (current.key.equals(k)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void delete(K k) {
        Node<K, V> current = this.first;
        while (current != null) {
            if (current.key.equals(k)) {
                if (current == first) {
                    // it's a first node
                    Node<K, V> next = current.next;
                    first = next;
                    if (next != null) {
                        next.prev = first;
                    }
                } else if (current.next == null) {
                    // it's a last node
                    Node<K, V> prev = current.prev;
                    if (prev != null) {
                        // remove the last node
                        prev.next = null;
                    }
                    last = prev;
                } else {
                    // node in the middle
                    Node<K, V> prev = current.prev;
                    Node<K, V> next = current.next;
                    prev.next = next;
                    next.prev = prev;
                }
                break;
            }
            current = current.next;
        }
    }

    @Override
    public boolean contains(K k) {
        Node<K, V> current = this.first;
        while (current != null) {
            if (current.key.equals(k)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<K, V> current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        Node<K, V> current = first;
        while (current != null) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }

    private class Node<K, V> {
        private Node<K, V> prev;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(Node<K, V> prev, K key, V value) {
            this.prev = prev;
            this.key = key;
            this.value = value;
        }
    }
}
