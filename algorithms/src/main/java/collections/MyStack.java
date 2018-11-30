package collections;

public class MyStack<T> {
    private Node first;
    private Node last;

    /**
     * Add item as a last one.
     *
     * @param item
     */
    public void push(T item) {
        if (this.last == null) {
            // list is empty, new node is a first and last at the same moment
            final Node<T> node = new Node<>(item, null, null);
            this.first = node;
            this.last = node;
        } else {
            // add a node as a last one
            final Node<T> node = new Node<>(item, this.last, null);
            this.last = node;
        }
    }

    public T peek() {
        return (T) last.value;
    }

    public T pop() {
        if (this.last == null) {
            return null;
        }
        // last element will be returned
        final Node<T> toReturn = this.last;
        // need to rearrange elements, previous will be last one now
        if (this.last.prev != null) {
            // this is not a last node
            final Node<T> newLast = this.last.prev;
            newLast.next = null;
            this.last = newLast;
        } else {
            // this was the last node in a list
            this.last = null;
            this.first = null;
        }
        // GC help, actually no need to do it now
        toReturn.next = null;
        toReturn.prev = null;
        return toReturn.value;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    private class Node<T> {
        private T value;
        private Node prev;
        private Node next;

        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
