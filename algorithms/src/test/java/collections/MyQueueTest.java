package collections;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyQueueTest {
    @Test
    void shouldBeEmptyByDefault() {
        final MyQueue<Integer> queue = new MyQueue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldNotBeEmptyIfContainsElement() {
        final MyQueue<Integer> queue = new MyQueue<>();
        queue.add(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void tryToAdd20Elements() {
        final MyQueue<Integer> queue = new MyQueue<>();
        IntStream.range(0, 20).forEach(queue::add);
        assertFalse(queue.isEmpty());
    }

    @Test
    void tryToAddAndThenRemove20Elements() {
        final MyQueue<Integer> queue = new MyQueue<>();
        IntStream.range(0, 20).forEach(queue::add);
        IntStream.range(0, 20).forEach(i -> queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void tryToAddAndThenPeek20Elements() {
        final MyQueue<Integer> queue = new MyQueue<>();
        IntStream.range(0, 20).forEach(queue::add);
        IntStream.range(0, 20).forEach(i -> queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    void tryToPollMoreElementsThanWasAdded() {
        final MyQueue<Integer> queue = new MyQueue<>();
        IntStream.range(0, 20).forEach(queue::add);
        IntStream.range(0, 30).forEach(i -> queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void iteratorTest() {
        final MyQueue<Integer> queue = new MyQueue<>();
        IntStream.range(0, 10).forEach(queue::add);
        queue.forEach(System.out::println);
    }
}