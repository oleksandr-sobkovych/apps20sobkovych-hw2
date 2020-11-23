package ua.edu.ucu.collections.helpers;

import lombok.Getter;

@Getter
public class MakeResult {
    private final ListNode head;
    private final ListNode tail;

    public MakeResult(ListNode head, ListNode tail) {
        this.head = head;
        this.tail = tail;
    }
}
