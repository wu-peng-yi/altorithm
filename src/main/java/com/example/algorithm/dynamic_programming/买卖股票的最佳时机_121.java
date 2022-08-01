package com.example.algorithm.dynamic_programming;

public class 买卖股票的最佳时机_121 {
    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }


    /**
     * 方法一：暴力法
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i; j < prices.length; j++) {
                int curr = prices[j] - prices[i];
                maxprofit = Math.max(maxprofit, curr);
            }
        }
        return maxprofit;
    }
}
