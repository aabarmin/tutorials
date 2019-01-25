package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HashTable<K, V> implements SymbolTable<K, V> {
    private int size = 0;
    private final HashTable.Bucket[] table;

    public HashTable() {
        this.table = new HashTable.Bucket[16];
    }

    @Override
    public void put(K k, V v) {
        final Bucket bucket = getBucket(k);
        bucket.put(new Entry<>(k, v));
        this.size++;
    }

    @Override
    public V get(K k) {
        final Bucket bucket = getBucket(k);
        if (bucket == null) {
            return null;
        }
        return bucket.get(k);
    }

    @Override
    public void delete(K k) {
        final Bucket bucket = getBucket(k);
        bucket.delete(k);
        size--;
    }

    @Override
    public boolean contains(K k) {
        final Bucket bucket = getBucket(k);
        return bucket.contains(k);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterable<K> keys() {
        return Arrays.stream(this.table)
                .filter((b) -> b != null)
                .map(Bucket::keys)
                .reduce(new ArrayList<>(), (current, keys) -> {
                    current.addAll(keys);
                    return current;
                });
    }

    private int getIndex(K k) {
        return Math.abs(k.hashCode() % (this.table.length - 1));
    }

    private HashTable.Bucket getBucket(K k) {
        final int index = getIndex(k);
        Bucket bucket = table[index];
        if (bucket == null) {
            bucket = new Bucket();
            this.table[index] = bucket;
        }
        return bucket;
    }

    private class Bucket {
        private List<Entry<K, V>> entries = new LinkedList<>();

        public void put(Entry<K, V> entry) {
            final int foundIndex = entries.indexOf(entry);
            if (foundIndex == -1) {
                entries.add(entry);
            } else {
                entries.get(foundIndex).value = entry.value;
            }
        }

        public V get(K key) {
            for (Entry<K, V> entry : entries) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            return null;
        }

        public Collection<K> keys() {
            return entries.stream()
                    .map(Entry::getKey)
                    .collect(Collectors.toList());
        }

        public void delete(K key) {
            final Iterator<Entry<K, V>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                final Entry<K, V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    iterator.remove();
                    break;
                }
            }
        }

        public boolean contains(K key) {
            for (Entry<K, V> entry : entries) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
