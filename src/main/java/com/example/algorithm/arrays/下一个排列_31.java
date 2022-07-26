package com.example.algorithm.arrays;

import java.util.Arrays;

/**
 * @author W
 * @date 2022-07-12
 * @see <a href="https://leetcode.cn/problems/next-permutation/">...</a>
 */
public class 下一个排列_31 {
    public static void main(String[] args) {
        int[] input = {2, 4, 3, 3, 2, 1};
        下一个排列_31 main = new 下一个排列_31();
        main.nextPermutation(input);
        for (int i : input) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 方法改进，将降序数组的翻转提取出来
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        //从后往前，找升序子序列
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // k 为需要调整的最高位
        if (k == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            i++;
        }
        //交换 i - 1和k的数
        swap(nums, i - 1, k);
        //index = k 之后的剩余部分变成升序排列
        int start = k + 1;
        int end = n - 1;
        reverse(nums, start, end);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    //定义一个翻转数组的方法
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * 一次遍历，从后往前找，找到这个数组中的最后一个升序子序列，调整它以后即为下一个排列
     *
     * @param nums
     */
    public void nextPermutation1(int[] nums) {
        int n = nums.length;

        //从后往前，找升序子序列
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // k 为需要调整的最高位
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }
        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            i++;
        }
        //交换 i - 1和k的数
        int temp = nums[i - 1];
        nums[i - 1] = nums[k];
        nums[k] = temp;
        //index = k 之后的剩余部分变成升序排列
        int start = k + 1;
        int end = n - 1;
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
