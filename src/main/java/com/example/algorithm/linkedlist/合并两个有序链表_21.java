package com.example.algorithm.linkedlist;

/**
 * @author W
 * @date 2022-07-17
 */
public class 合并两个有序链表_21 {
    public static void main(String[] args) {

    }

    //递归
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //比较头结点
        if(list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
        }
        return list1.val <= list2.val ? list1 : list2;
    }

    //迭代
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null) {
            temp.next = list1;
        }
        if (list2 != null) {
            temp.next = list2;
        }
        return dummy.next;
    }
}
