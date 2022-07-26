package com.example.algorithm.sort;

/**
 * @author W
 * @date 2022-07-22
 */
public class QuickSort {
    //分治策略，进行递归调用
    public static void qSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        //找到一个pivot,把数组划分成两部分,返回索引位置
        int index = partition(nums, start, end);

        //递归排序左右两部分
        qSort(nums, start, index - 1);
        qSort(nums, index + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        //以第一个元素作为中心点
        int pivot = nums[start];
        //双指针
        int left = start, right = end;
        int position = start;
        while (left < right) {
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            while (left < right && nums[right] > pivot) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
            } else {
                //如果相遇，填入pivot
                // 要判断当前位置和pivot的大小，确定到底填入哪个位置
                if (nums[left] < pivot) {
                    position = left;
                    swap(nums, start, left);
                } else {
                    position = left - 1;
                    swap(nums, start, left - 1);
                }
            }
        }
        return position;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
