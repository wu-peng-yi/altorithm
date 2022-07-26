package com.example.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author W
 * @date 2022-07-22
 */
public class 数组中的第K个最大元素_215 {
    public static void main(String[] args) {
        数组中的第K个最大元素_215 find = new 数组中的第K个最大元素_215();

    }

    /**
     * 基于堆排序的选择
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        //保存堆的大小，初始化为n
        int heapSize = n;

        //构建大顶堆
        buildMaxHeap(nums, heapSize);

        //删除k - 1次堆顶元素
        for (int i = n - 1; i > n - k; i--) {
            //将堆顶元素放到当前堆的末尾
            QuickSort.swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void maxHeapify(int[] nums, int top, int heapSize) {
        //定义左右子节点
        int left = 2 * top + 1;
        int right = 2 * top + 2;

        //保存当前最大元素的索引位置
        int largest = top;

        //比较左右子节点，记录最大元素位置
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }
        //将最大元素换到堆顶
        if (largest != top) {
            QuickSort.swap(nums, top, largest);

            //递归调用，继续下沉
            maxHeapify(nums, largest, heapSize);
        }
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    /**
     * 快速选择，快速排序+二分查找
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int start, int end, int index) {
        //找到pivot位置
        int position = randomPosition(nums, start, end);


        if (position == index) {
            return nums[position];
        } else {
            return position > index ? quickSelect(nums, 0, position - 1, index) : quickSelect(nums, position + 1, nums.length - 1, index);
        }
    }

    private int randomPosition(int[] nums, int start, int end) {
        int randomIndex = new Random().nextInt(end - start + 1) + start;
        QuickSort.swap(nums, start, randomIndex);
        return QuickSort.partition(nums, start, end);
    }

    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
