package com.example.algorithm.dynamic_programming;

public class Fibonacci {
    public static void main(String[] args) {

    }

    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        //定义一个状态数组，其实就是保存fib(n)的计算结果
        int[] state = new int[n];
        state[0] = state[1] = 1;

        for (int i = 2; i < n; i++) {
            state[i] = state[i - 1] + state[i - 2];
        }
        return state[n - 1];
    }
}
