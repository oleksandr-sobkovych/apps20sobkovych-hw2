package ua.edu.ucu.collections.immutable;
import ua.edu.ucu.collections.exceptions.ListExceptionHandler;
import ua.edu.ucu.collections.helpers.ListNode;
import ua.edu.ucu.collections.helpers.CopyResult;
import ua.edu.ucu.collections.helpers.MakeResult;

public class ImmutableLinkedList implements ImmutableList {
    private static final ListExceptionHandler HANDLER =
            new ListExceptionHandler();
    private final ListNode start;
    private final ListNode end;
    private final int size;

    public ImmutableLinkedList(Object... data) {
        MakeResult nodes = makeAll(data);
        this.start = nodes.getHead();
        this.end = nodes.getTail();
        this.size = data.length;
    }

    private ImmutableLinkedList(ListNode head, ListNode tail, int size) {
        this.start = head;
        this.end = tail;
        this.size = size;
    }

    private MakeResult makeAll(Object[] data) {
        if (data.length < 1) {
            return new MakeResult(null, null);
        }
        ListNode head = new ListNode(data[0]);
        ListNode pointer = head;
        for (int i = 1; i < data.length; i++) {
            pointer.setNext(new ListNode(data[i]));
            pointer = pointer.getNext();
        }
        return new MakeResult(head, pointer);
    }

    private CopyResult copyAll(ListNode from, int count) {
        if (count < 1) {
            return new CopyResult(null, null, from);
        }
        ListNode copiedHead = new ListNode(from.getData());
        ListNode newPointer = copiedHead;
        ListNode oldPointer = from.getNext();
        for (int i = 1; i < count; i++) {
            newPointer.setNext(new ListNode(oldPointer.getData()));
            oldPointer = oldPointer.getNext();
            newPointer = newPointer.getNext();
        }
        return new CopyResult(copiedHead, newPointer, oldPointer);
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
        if (c.length < 1) {
            return new ImmutableArrayList(this.toArray());
        }
        ListNode head, tail;
        CopyResult copiedFirst = copyAll(this.start, index);
        MakeResult inserted = makeAll(c);
        head = copiedFirst.getHead();
        if (head == null) {
            head = inserted.getHead();
        } else {
            copiedFirst.getTail().setNext(inserted.getHead());
        }
        CopyResult copiedLast = copyAll(copiedFirst.getRest(), size()-index);
        inserted.getTail().setNext(copiedLast.getHead());
        tail = copiedLast.getTail();
        if (tail == null) {
            tail = inserted.getTail();
        }
        return new ImmutableLinkedList(head, tail, size()+c.length);
    }

    @Override
    public Object get(int index) {
        HANDLER.checkEmpty(this, "cannot get element in empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for getting element out of bounds");
        ListNode pointer = this.start;
        for (int i = 0; i < index; i++) {
            pointer = pointer.getNext();
        }
        return pointer.getData();
    }

    @Override
    public ImmutableList remove(int index) {
        HANDLER.checkEmpty(this, "cannot remove element from empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for removing element out of bounds");
        ListNode head, tail;
        CopyResult headCopy = copyAll(this.start, index);
        CopyResult tailCopy = copyAll(headCopy.getRest(), size()-index);
        tail = tailCopy.getTail();
        if (headCopy.getHead() != null) {
            head = headCopy.getHead();
            headCopy.getTail().setNext(tailCopy.getHead().getNext());
            if (tailCopy.getHead() == tailCopy.getTail()) {
                tail = headCopy.getTail();
            }
        } else {
            head = tailCopy.getHead().getNext();
        }
        return new ImmutableLinkedList(head, tail, size()-1);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        HANDLER.checkEmpty(this, "cannot set element in empty list");
        HANDLER.checkIndex(index, 0, size()-1,
                "index for setting element out of bounds");
        CopyResult headCopy = copyAll(this.start, index);
        CopyResult tailCopy = copyAll(headCopy.getRest(), size()-index);
        tailCopy.getHead().setData(e);
        if (headCopy.getHead() != null) {
            headCopy.getTail().setNext(tailCopy.getHead());
            return new ImmutableLinkedList(headCopy.getHead(),
                    tailCopy.getTail(), size());
        }
        return new ImmutableLinkedList(tailCopy.getHead(),
                tailCopy.getTail(), size());
    }

    @Override
    public int indexOf(Object e) {
        ListNode pointer = this.start;
        for (int i = 0; i < size(); i++) {
            if (pointer.getData().equals(e)) {
                return i;
            }
            pointer = pointer.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] data = new Object[size()];
        ListNode pointer = this.start;
        for (int i = 0; i < size(); i++) {
            data[i] = pointer.getData();
            pointer = pointer.getNext();
        }
        return data;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        HANDLER.checkEmpty(this, "cannot get head element from empty list");
        return this.start.getData();
    }

    public Object getLast() {
        HANDLER.checkEmpty(this, "cannot get tail element from empty list");
        return this.end.getData();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(size()-1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode pointer = this.start;
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(pointer.getData());
            if (i < size()-1) {
                stringBuilder.append(", ");
            }
            pointer = pointer.getNext();
        }
        return stringBuilder.toString();
    }
}
