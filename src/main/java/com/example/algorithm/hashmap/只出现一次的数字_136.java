package com.example.algorithm.hashmap;

import java.util.*;

/**
 * @author W
 * @date 2022-07-18
 */
public class 只出现一次的数字_136 {
    public static void main(String[] args) {

    }

    //方法四，位运算
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    //方法三，set去重，数学解法，(set的和乘以 2 - 单个元素 = 数组和)
    public int singleNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;
        for (int num : nums) {
            set.add(num);
            arraySum += num;
        }
        return 4;
    }

    //使用Hashmap,时间复杂度降低
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, 1);
            }
        }
        return map.keySet().iterator().next();
    }

    //暴力破解法
    public int singleNumber1(int[] nums) {
        //定义一个列表，保存出现过一次的元素
        List<Integer> list = new ArrayList<>();

        //这里要用包装类型接收，因为如果是基本类型，会被当做索引去删除
        for (Integer num : nums) {
            if (list.contains(num)) {
                list.remove(num);
            } else {
                list.add(num);
            }
        }
        return list.get(0);
    }
}
