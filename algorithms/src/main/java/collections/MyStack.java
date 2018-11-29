package collections;

public class MyStack<T> {
    private Node first;
    private Node last;

    public void push(T item) {
        Node<T> l = this.last;
        Node<T> newNode = new Node(item, this.last, null);
        if (l == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
    }

    public T peek() {
        return (T) last.value;
    }

    public T pop() {
        if (this.last == null) {
            return null;
        }
        Node theLast = this.last;
        Node thePrev = theLast.prev;
        if (thePrev != null) {
            thePrev.next = null;
        }
        this.last = thePrev;
        return (T) theLast.value;
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
