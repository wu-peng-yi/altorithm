package com.example.algorithm.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author W
 * @date 2022-07-19
 */
public class MyStack {
    //定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    //push
    public void push(int x) {
        //先存到2中
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }
}
