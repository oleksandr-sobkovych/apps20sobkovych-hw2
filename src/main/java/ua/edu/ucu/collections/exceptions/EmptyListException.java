package ua.edu.ucu.collections.exceptions;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}
