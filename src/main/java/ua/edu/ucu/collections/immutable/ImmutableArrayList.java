package ua.edu.ucu.collections.immutable;

import ua.edu.ucu.collections.exceptions.ListExceptionHandler;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private static final ListExceptionHandler HANDLER =
            new ListExceptionHandler();
    private final Object[] data;

    public ImmutableArrayList(Object... data) {
        this.data = new Object[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        HANDLER.checkIndex(index, 0, size(),
                "index for adding elements out of bounds");
        Object[] newData = new Object[size()+c.length];
        System.arraycopy(this.data, 0, newData, 0, index);
        System.arraycopy(c, 0, newData, index, c.length);
        System.arraycopy(this.data, index, newData,
                index+c.length, size()-index);
        return new ImmutableArrayList(newData);
    }

    @Override
    public Object get(int index) {
        HANDLER.checkEmpty(this, "cannot get element in empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for getting element out of bounds");
        return this.data[index];
    }

    @Override
    public ImmutableList remove(int index) {
        HANDLER.checkEmpty(this, "cannot remove element from empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for removing element out of bounds");
        Object[] newData = new Object[size()-1];
        System.arraycopy(this.data, 0, newData, 0, index);
        System.arraycopy(this.data, index+1, newData, index, size()-index-1);
        return new ImmutableArrayList(newData);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        HANDLER.checkEmpty(this, "cannot set element in empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for setting element out of bounds");
        Object[] newData = new Object[size()];
        System.arraycopy(this.data, 0, newData, 0, size());
        newData[index] = e;
        return new ImmutableArrayList(newData);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size(); i++) {
            if (this.data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.data.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.data.length == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.data, size());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(data[i]);
            if (i < size()-1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
