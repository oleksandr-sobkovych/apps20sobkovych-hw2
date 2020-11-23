package ua.edu.ucu.collections;

import ua.edu.ucu.collections.exceptions.EmptyListException;
import ua.edu.ucu.collections.exceptions.EmptyQueueException;
import ua.edu.ucu.collections.exceptions.EmptyStackException;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;

    public Stack() {
        this.list = new ImmutableLinkedList();
    }

    public Object peek() {
        try {
            return this.list.getFirst();
        } catch (EmptyListException e) {
            throw new EmptyStackException("no elements to peek at");
        }
    }

    public Object pop() {
        try {
            Object toReturn = peek();
            this.list = this.list.removeFirst();
            return toReturn;
        } catch (EmptyStackException e) {
            throw new EmptyStackException("no elements to pop from stack");
        }
    }

    public void push(Object e) {
        this.list = this.list.addFirst(e);
    }

    @Override
    public String toString() {
        return "Stack[" + list.toString() + "]";
    }
}
