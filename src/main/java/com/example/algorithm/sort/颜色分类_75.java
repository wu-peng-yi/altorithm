package com.example.algorithm.sort;

public class 颜色分类_75 {
    public static void main(String[] args) {

    }

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int i = left;

        while (left < right && i <= right) {
            while (i <= right && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[right];
                nums[right] = temp;
                right--;
            }
            //如果是0要换到头部
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
            }
            i++;
        }
    }

    /**
     * 计数排序方式
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 基于选择排序
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {
        int n = nums.length;

        int curr = 0;

        //将所有零放到头部
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[curr];
                nums[curr] = temp;
                curr++;
            }
        }

        //将所有一放到头部
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[curr];
                nums[curr] = temp;
                curr++;
            }
        }
    }
}
