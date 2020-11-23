package ua.edu.ucu.collections.exceptions;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String errorMessage) {
        super(errorMessage);
    }
}
