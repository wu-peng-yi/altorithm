package com.example.algorithm.dynamic_programming;

public class KnapsackProblem {
    public static void main(String[] args) {

    }

    /**
     * 动态规划优化--滚动数组
     * @return
     */
    public int maxValue(int capacity,int[] weights, int[] values) {
        int n = weights.length;
        //只保留一行
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j > 0; j++) {
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    /**
     * 动态规划
     */
    public int maxValue1(int capacity,int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }
}
