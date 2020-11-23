package ua.edu.ucu.collections.exceptions;

import ua.edu.ucu.collections.immutable.ImmutableList;

public class ListExceptionHandler {
    public void checkIndex(int index, int min, int max, String message) {
        if ((index < min) || (index > max)) {
            throw new IndexOutOfBoundsException(message);
        }
    }

    public void checkEmpty(ImmutableList list, String message) {
        if (list.isEmpty()) {
            throw new EmptyListException(message);
        }
    }
}
