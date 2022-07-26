package com.example.algorithm.slidingwindows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @author W
 * @date 2022-07-15
 */
public class 滑动窗口最大值_239 {
    public static void main(String[] args) {

    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        int[] left = new int[n];
        int[] right = new int[n];

        //左右扫描出数据
        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(nums[i], left[i - 1]);
            }
            int j = n - i - 1;
            if (j % k == k - 1 || j == n - 1) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        for (int i = 0; i < n - k + 1; i++) {
            result[i] = Math.max(right[i], left[i + k - 1]);
        }
        return result;
    }

    /**
     * 使用双向队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        //初始化双向队列，处理第一个窗口数据
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        result[0] = nums[deque.getFirst()];

        for (int i = k; i < nums.length; i++) {
            if (deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            result[i - k + 1] = deque.getFirst();
        }
        return result;
    }

    /**
     * 使用大顶堆,也会超时
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        //优先队列实现一个大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, ((o1, o2) -> o2 - o1));

        //构建大顶堆
        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        result[0] = maxHeap.peek();

        for (int i = 1; i <= nums.length - k; i++) {
            maxHeap.remove(nums[i - 1]);
            maxHeap.add(nums[i + k - 1]);
            result[i] = maxHeap.peek();
        }
        return result;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        //0 -> n - k 作为滑动窗口的起始位置
        for (int i = 0; i < n - k; i++) {
            //找窗口内的最大值
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(nums[j], max);
            }
            result[i] = max;
        }
        return result;
    }
}
