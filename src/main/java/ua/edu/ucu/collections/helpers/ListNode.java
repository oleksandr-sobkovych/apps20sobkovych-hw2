package ua.edu.ucu.collections.helpers;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ListNode {
    private Object data;
    private ListNode next;

    public ListNode(Object data) {
        this.data = data;
        this.next = null;
    }
}