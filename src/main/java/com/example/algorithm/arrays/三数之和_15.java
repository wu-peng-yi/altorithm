package com.example.algorithm.arrays;

import java.util.*;

/**
 * @author W
 * @date 2022-07-11
 */
public class 三数之和_15 {
    public static void main(String[] args) {

    }

    /**
     * 双指针,先找到核心值，使用双指针从两边查找满足条件的两个值
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //数组排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            int target = nums[i];
            //如果当前数大于零，直接退出即可
            if (target > 0) {
                break;
            }
            //当前数据出现过，则只要满足条件的一定已经存到结果集，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //常规情况
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = target + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(target, nums[left], nums[right]));
                    left++;
                    right--;
                    //当前值已经出现过，不可重复计算
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return result;
    }


    /**
     * 用hash表保存结果
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();

        //遍历数组，寻找两个数
        for (int i = 0; i < n - 2; i++) {
            int thatNum = -nums[i];
            if (map.containsKey(thatNum)) {
                //如果已经存在，则找到了一组解
                ArrayList<Integer> tempList = new ArrayList<>(map.get(thatNum));
                tempList.add(nums[i]);
                result.add(tempList);
            }
            //当前数对应的两数组合都保存
            for (int j = 0; j < i; j++) {
                int newKey = nums[i] + nums[j];
                map.putIfAbsent(newKey, Arrays.asList(nums[i], nums[j]));
            }
        }
        return result;
    }

    //方法一：暴力法
    public List<List<Integer>> threeSums1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }

}
