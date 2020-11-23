package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.exceptions.EmptyStackException;

import static org.junit.Assert.*;

public class StackTest {

    private Stack stack;

    @Before
    public void setUp() {
        this.stack = new Stack();
    }

    @Test
    public void testStack() {
        assertNotNull(stack);
    }

    @Test(expected = EmptyStackException.class)
    public void testEmptyPeek() {
        stack.peek();
    }

    @Test
    public void testPeekPush() {
        Object expected = "Hello World!";
        stack.push(1);
        stack.push(5.7d);
        stack.push(expected);
        assertEquals(expected, stack.peek());
    }


    @Test(expected = EmptyStackException.class)
    public void testEmptyPop() {
        stack.pop();
    }

    @Test
    public void testPushPopSequence() {
        stack.push(1);
        stack.push(2);
        Object unexpected = stack.pop();
        Object actual = stack.pop();
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void testPushPopGeneral() {
        Object expected = "Hello World!";
        stack.push(1);
        stack.push(2);
        stack.push(expected);
        stack.push(3);
        stack.pop();
        assertEquals(expected, stack.peek());
    }

    @Test
    public void testToString() {
        String expected = "Stack[3, 2.5, 1]";
        stack.push(1);
        stack.push(2.5d);
        stack.push(3);
        assertEquals(expected, stack.toString());
    }
    
}
