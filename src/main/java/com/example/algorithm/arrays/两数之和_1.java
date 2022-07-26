package com.example.algorithm.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-11
 */
public class 两数之和_1 {
    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int target = 9;

        两数之和_1 twoSum = new 两数之和_1();
        int[] output = twoSum.twoSum(input, target);
        for (int i : output) {
            System.out.print(i + "\t");
        }
    }

    //方法一，暴力法，穷举所有两数组合
    public int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        //双重for循环
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        //遍历数组，将数据全部保存如map
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        //再次遍历数组，寻找每个数对应的结果是否存在
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    public int[] twoSum(int[] nums, int target) {
        long startTime = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<>();
        //遍历数组，将数据全部保存如map
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("no solution");
    }
}
