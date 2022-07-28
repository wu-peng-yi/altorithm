package com.example.algorithm.practice;

import com.example.algorithm.linkedlist.ListNode;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        ListNode node = new ListNode(1);
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        listNode.next = listNode1;
        node.next = listNode;
        int[] res = testMain.reversePrint1(node);
        for (int re : res) {
            System.out.println(re);
        }
    }

    /**
     * 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        int length = s.length();
        if (length == 0 || length == 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(s.charAt((n + i) + length) % length);
        }
        return sb.toString();
    }

    /**
     * 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    public int[] reversePrint1(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        getArray(head);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    List<Integer> list = new ArrayList<>();

    private void getArray(ListNode head) {
        if (head == null) {
            return;
        }
        getArray(head.next);
        list.add(head.val);
    }

    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        ListNode temp;
        while (head != null) {
            temp = head.next;
            head.next = res;
            res = head;
            head = temp;
        }
        return res;
    }

    Map<Node, Node> cacheNodes = new HashMap<>();

    /**
     * 拷贝链表
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (!cacheNodes.containsKey(head)) {
            Node headNew = new Node(head.val);
            cacheNodes.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cacheNodes.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}