package com.example.algorithm.dynamic_programming;

public class 打家劫舍_198 {
    public static void main(String[] args) {
        打家劫舍_198 rob = new 打家劫舍_198();
        int[] arr = {2,7,9,3,1};
        System.out.println(rob.rob(arr));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
}
