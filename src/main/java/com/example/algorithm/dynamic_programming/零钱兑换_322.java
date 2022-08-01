package com.example.algorithm.dynamic_programming;

public class 零钱兑换_322 {
    public static void main(String[] args) {
        零钱兑换_322 coin = new 零钱兑换_322();
        int[] arr = {1, 2};
        System.out.println(coin.coinChange(arr, 5));
    }

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            int minCount = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != -1) {
                    minCount = Math.min(minCount, dp[i - coin] + 1);
                }
                dp[i] = minCount == Integer.MAX_VALUE ? -1 : minCount;
            }
        }
        return dp[amount];
    }
}
