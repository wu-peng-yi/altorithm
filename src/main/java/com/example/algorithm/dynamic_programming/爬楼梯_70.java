package com.example.algorithm.dynamic_programming;

public class 爬楼梯_70 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划，同时优化空间
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int pre2 = 1;
        int pre1 = 1;
        int curr;
        for (int i = 1; i < n; i++) {
            curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }
}
