package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.exceptions.EmptyQueueException;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue;

    @Before
    public void setUp() {
        this.queue = new Queue();
    }

    @Test
    public void testQueue() {
        assertNotNull(queue);
    }

    @Test(expected = EmptyQueueException.class)
    public void testEmptyPeek() {
        queue.peek();
    }

    @Test
    public void testPeekEnqueue() {
        Object expected = "Hello World!";
        queue.enqueue(expected);
        queue.enqueue(1);
        queue.enqueue(5.7d);
        assertEquals(expected, queue.peek());
    }


    @Test(expected = EmptyQueueException.class)
    public void testEmptyDequeue() {
        queue.dequeue();
    }

    @Test
    public void testEnqueueDequeueSequence() {
        queue.enqueue(1);
        queue.enqueue(2);
        Object unexpected = queue.dequeue();
        Object actual = queue.dequeue();
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void testEnqueueDequeueGeneral() {
        Object expected = "Hello World!";
        queue.enqueue(1);
        queue.enqueue(expected);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        assertEquals(expected, queue.peek());
    }

    @Test
    public void testToString() {
        String expected = "Queue[3, 2.5, 1]";
        queue.enqueue(1);
        queue.enqueue(2.5d);
        queue.enqueue(3);
        assertEquals(expected, queue.toString());
    }
}
