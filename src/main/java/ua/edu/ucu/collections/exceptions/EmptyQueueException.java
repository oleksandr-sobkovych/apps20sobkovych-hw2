package ua.edu.ucu.collections.exceptions;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String errorMessage) {
        super(errorMessage);
    }
}
