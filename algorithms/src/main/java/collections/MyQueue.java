package collections;

import java.util.Iterator;

public class MyQueue<T> implements Iterable<T> {
    private int count = 0;
    private Object[] storage;

    public MyQueue() {
        storage = new Object[16];
    }

    public void add(T item) {
        ensureCapacity();
        storage[count++] = item;
    }

    /**
     * Doesn't remove element from the head of the queue.
     * @return
     */
    public T peek() {
        if (count == 0) {
            return null;
        }
        return (T) storage[count - 1];
    }

    /**
     * Removes element from the head of the queue.
     * @return
     */
    public T poll() {
        if (count == 0) {
            return null;
        }
        return (T) storage[count--];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void ensureCapacity() {
        if (count == storage.length) {
            Object[] newStorage = new Object[storage.length << 1];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < MyQueue.this.count;
            }

            @Override
            public T next() {
                return (T) MyQueue.this.storage[index++];
            }
        };
    }
}
