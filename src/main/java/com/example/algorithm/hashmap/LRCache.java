package com.example.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-19
 */
public class LRCache {
    //定义双向链表的节点类
    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //定义hash表
    private Map<Integer, Node> hashMap = new HashMap<>();

    //本身的大小
    private int capacity;
    //当前已有的大小
    private int size;
    //头尾指针
    private Node head, tail;

    public LRCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        //虚拟节点
        this.head = new Node();
        this.tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //从hash表中查找
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }
        //如果存在。当前节点移到链表末尾
        moveToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = hashMap.get(key);
        //如果存在，修改value并移动到末尾
        if (node != null) {
            node.value = value;
            moveToTail(node);
        }
        //如果不存在，创建新的节点，插入到末尾
        else {
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode);
            addToTail(newNode);
            size++;

            //如果size超出capacity限制,要删除链表头结点
            if (size > capacity) {
                Node head = removeHead();
                hashMap.remove(head.key);
                size--;
            }
        }
    }

    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToTail(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private Node removeHead() {
        Node realHead = this.head.next;
        removeNode(realHead);
        return realHead;
    }
}

