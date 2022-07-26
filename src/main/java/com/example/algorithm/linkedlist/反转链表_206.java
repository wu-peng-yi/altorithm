package com.example.algorithm.linkedlist;

import java.util.HashMap;
import java.util.List;

/**
 * @author W
 * @date 2022-07-17
 */
public class 反转链表_206 {
    public static void main(String[] args) {

    }

    //递归
    public ListNode reverseList(ListNode head) {
        //
        if (head == null || head.next == null) {
            return head;
        }
        ListNode restHead = head.next;
        ListNode reversedRest = reverseList(restHead);
        restHead.next = head;
        head.next = null;
        return reversedRest;
    }

    //迭代
    public ListNode reverseList2(ListNode head) {
        //定义2个指针，指向当前访问的节点
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            //临时保存当前节点的下一个节点
            ListNode tempNext = curr.next;
            curr.next = prev;

            prev = curr;
            curr = tempNext.next;
        }
        return prev;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode curr = null;
        ListNode temp;

        while (head != null) {
            temp = head.next;
            head.next = curr;
            curr = head;
            head = temp;
        }
        return curr;
    }
}
