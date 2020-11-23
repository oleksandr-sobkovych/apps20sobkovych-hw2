package ua.edu.ucu.collections.helpers;

import lombok.Getter;

@Getter
public class CopyResult {
    private final ListNode head;
    private final ListNode tail;
    private final ListNode rest;

    public CopyResult(ListNode head, ListNode tail, ListNode rest) {
        this.head = head;
        this.tail = tail;
        this.rest = rest;
    }
}
