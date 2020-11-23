package ua.edu.ucu.collections;

import ua.edu.ucu.collections.exceptions.EmptyListException;
import ua.edu.ucu.collections.exceptions.EmptyQueueException;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList list;

    public Queue() {
        this.list = new ImmutableLinkedList();
    }

    public Object peek() {
        try {
            return this.list.getLast();
        } catch (EmptyListException e) {
            throw new EmptyQueueException("no elements to peek at");
        }
    }

    public Object dequeue() {
        try {
            Object toReturn = peek();
            this.list = this.list.removeLast();
            return toReturn;
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException("no elements to dequeue");
        }
    }

    public void enqueue(Object e) {
        this.list = this.list.addFirst(e);
    }

    @Override
    public String toString() {
        return "Queue[" + list.toString() + "]";
    }
}
