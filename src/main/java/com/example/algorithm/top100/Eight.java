package com.example.algorithm.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class Eight {
    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 数组排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int mid = i + 1;
            int right = nums.length - 1;
            while (mid < right) {
                int sum = nums[i] + nums[mid] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    mid ++;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[mid], nums[right])));
                    while (mid < right && nums[mid] == nums[mid + 1]) {
                        mid++;
                    }
                    while (mid < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    mid ++;
                    right--;
                }
            }
        }
        return res;
    }
}
