package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * @author W
 * @date 2022-07-22
 */
public class 数组中的第K个最大元素_215 {
    public static void main(String[] args) {
        数组中的第K个最大元素_215 find = new 数组中的第K个最大元素_215();

    }

    public int findKthLargest(int[] nums, int k) {

    }

    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
