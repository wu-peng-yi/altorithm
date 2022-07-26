package com.example.algorithm.linkedlist;

/**
 * @author W
 * @date 2022-07-17
 */
public class 删除链表的倒数第N个节点_19 {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(-1,head);
        ListNode slow = res;
        ListNode fast = res;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return res.next;
    }
}
