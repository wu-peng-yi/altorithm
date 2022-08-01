package com.example.algorithm.backtrack;

import java.util.*;

public class 全排列_46 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> solution = new ArrayList<>();

        //从0位置开始填充
        backTrack(nums,res,solution,0);
        return res;
    }

    //辅助集合
    Set<Integer> set = new HashSet<>();

    private void backTrack(int[] nums, List<List<Integer>> res, List<Integer> solution, int i) {
        int n = nums.length;
        if (i >= n) {
            res.add(new ArrayList<>(solution));
        } else {
            //对当前位置选择数字
            for (int j = 0; j < n; j++) {
                if (!set.contains(nums[j])) {
                    //没用过，直接填入
                    solution.add(nums[j]);
                    set.add(nums[j]);

                    backTrack(nums,res,solution,i++);

                    solution.remove(i);
                    set.remove(nums[j]);
                }

            }

        }
    }


}
