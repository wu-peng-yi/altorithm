package com.example.algorithm.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * @author W
 * @date 2022-07-18
 */
public class 最长连续序列_128 {
    public static void main(String[] args) {
        最长连续序列_128 max = new 最长连续序列_128();
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(max.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            //保存当前元素作为起始点
            int currentNum = num;
            //当前长度
            int tempLength = 1;
            //只有当前元素的前驱不存在的情况下，才进行寻找连续序列操作
            if (set.contains(num - 1)) {
                continue;
            }
            //寻找后序数字
            while (set.contains(currentNum + 1)) {
                tempLength++;
                currentNum++;
            }
            maxLength = Math.max(maxLength, tempLength);
        }
        return maxLength;
    }

    public int longestConsecutive2(int[] nums) {
        int maxLength = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            //保存当前元素作为起始点
            int currentNum = num;
            //当前长度
            int tempLength = 1;
            //寻找后序数字
            while (set.contains(currentNum + 1)) {
                tempLength++;
                currentNum++;
            }
            maxLength = Math.max(maxLength, tempLength);
        }
        return maxLength;
    }

    //暴力法
    public int longestConsecutive1(int[] nums) {
        //定义一个结果变量
        int maxLength = 0;
        for (int num : nums) {
            //保存当前元素作为起始点
            int currentNum = num;
            //当前长度
            int tempLength = 1;
            //寻找后序数字
            while (contains(nums, currentNum + 1)) {
                tempLength++;
                currentNum++;
            }
            maxLength = Math.max(maxLength, tempLength);
        }
        return maxLength;
    }

    public boolean contains(int[] nums, int x) {
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }
}
