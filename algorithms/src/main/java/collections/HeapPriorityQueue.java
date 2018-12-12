package collections;

public class HeapPriorityQueue implements Queue<Integer> {
    private int[] storage;
    private int elements = 0;
    private final boolean debug;

    public HeapPriorityQueue(boolean debug) {
        this.debug = debug;
    }

    public HeapPriorityQueue() {
        this(false);
        storage = new int[16];
    }

    @Override
    public void insert(Integer value) {
        // insert at the end of heap
        // storage[0] is unused element
        ensureCapacity();
        storage[++elements] = value;
        // restore heap conditions
        swimUp(elements);
        // for debug purposes
        assertSorted(1);
    }

    private void ensureCapacity() {
        if (elements == storage.length - 1) {
            int[] newStorage = new int[elements * 2];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }
    }

    @Override
    public Integer max() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return storage[1];
    }

    @Override
    public Integer delMax() {
        // get the largest element
        final int max = max();
        // move the least element to the top of the heap
        exchange(1, elements);
        // decrement the heap size
        elements--;
        // restore heap conditions using sinkDown
        if (!isEmpty()) {
            sinkDown(1);
        }
        // for debug purposes
        assertSorted(1);
        // return the largest element
        return max;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return elements;
    }

    private void sinkDown(int index) {
        int j = index * 2;
        if (j > elements) {
            // out of bounds
            return;
        }
        // get the largest of children
        if (j < elements && isLess(j, j + 1)) {
            j++;
        }
        if (isLess(index, j)) {
            exchange(index, j);
            sinkDown(j);
        }
    }

    private void assertSorted(int index) {
        if (!debug) {
            return;
        }
        int j = index * 2;
        if (j > elements) {
            return;
        }
        if (isLess(index, j)) {
            throw new RuntimeException(String.format("Invalid order on a left child: %s and %s",
                    storage[index], storage[j]));
        }
        assertSorted(j);
        if (j + 1 > elements) {
            return;
        }
        if (isLess(index, j + 1)) {
            throw new RuntimeException(String.format("Invalid order on a right child: %s and %s",
                    storage[index], storage[j + 1]));
        }
        assertSorted(j + 1);
    }

    private void swimUp(int index) {
        while (index > 1 && isLess(index / 2, index)) {
            exchange(index / 2, index);
            index = index / 2;
        }
    }

    private void exchange(int leftIndex, int rightIndex) {
        int buffer = storage[leftIndex];
        storage[leftIndex] = storage[rightIndex];
        storage[rightIndex] = buffer;
    }

    private boolean isEqual(int leftIndex, int rightIndex) {
        return storage[leftIndex] == storage[rightIndex];
    }

    private boolean isLess(int leftIndex, int rightIndex) {
        return storage[leftIndex] < storage[rightIndex];
    }
}
