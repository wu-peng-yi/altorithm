package com.example.algorithm.binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-13
 */
public class 寻找重复数_287 {
    public static void main(String[] args) {

    }

    /**
     * 优化双指针
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        //定义快慢指针
        int fast = 0;
        int slow = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        //循环结束，都是相遇点
        int before = 0;
        int after = slow;
        while (after != before) {
            before = nums[before];
            after = nums[after];
        }
        return nums[after];
    }

    /**
     * 二分查找
     *
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        //定义左右指针
        int left = 1;
        int right = nums.length - 1;
        while (left <= right) {
            //计算中间值
            int mid = (left + right) / 2;
            //对当前mid计算count
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            //判断count和mid本身关系
            if (count <= mid) {
                //count <= mid 自身，说明mid比target小，左指针右移
                left = mid + 1;
            } else {
                right = mid;
            }
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    /**
     * 排序方法
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        //遍历数组元素，遇到与前一个相同的就是重复元素
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 使用hashmap保存
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                return num;
            } else {
                countMap.put(num, 1);
            }
        }
        return -1;
    }

}
