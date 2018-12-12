package collections;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapPriorityQueueTest {
    private final Queue<Integer> queue = new HeapPriorityQueue();

    @Test
    void tryToAddElement() {
        assertTrue(queue.isEmpty(), "Queue should be empty by default");
        queue.insert(10);
        assertFalse(queue.isEmpty(), "Queue shouldn't be empty after the insertion of an element");
    }

    @Test
    void tryToAddTwoElements() {
        assertTrue(queue.isEmpty(), "Queue should be empty by default");
        queue.insert(10);
        queue.insert(20);
        assertEquals(2, queue.size(), "Queue should has two elements inside");
    }

    @Test
    void tryToGetAFewElementsFromQueueInAnOrderedWay() {
        assertTrue(queue.isEmpty(), "Queue should be empty by default");
        queue.insert(5);
        queue.insert(10);
        queue.insert(15);
        queue.insert(20);
        assertEquals(4, queue.size(), "Invalid queue size");
        // try to get max element
        assertEquals(new Integer(20), queue.max(), "Invalid max element");
        // getting elements from the queue
        assertEquals(new Integer(20), queue.delMax());
        assertEquals(new Integer(15), queue.delMax());
        assertEquals(new Integer(10), queue.delMax());
        assertEquals(new Integer(5), queue.delMax());
        assertTrue(queue.isEmpty());
    }

    @Test
    @RepeatedTest(100)
    void tryToAddALotOfElements() {
        assertTrue(queue.isEmpty(), "Queue should be empty by default");
        // add elements to the queue
        final Random random = new Random();
        IntStream.generate(random::nextInt)
                .limit(100_000)
                .forEach(queue::insert);
        // try to get all elements
        int[] currentMax = new int[]{ queue.max() };
        IntStream.generate(queue::delMax)
                .limit(queue.size())
                .forEach((int i) -> {
                    assertTrue(currentMax[0] >= i, () -> String.format("Invalid order of elements %s and %s", currentMax[0], i));
                    currentMax[0] = i;
                });
    }
}