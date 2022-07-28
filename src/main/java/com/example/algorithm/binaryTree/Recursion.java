package com.example.algorithm.binaryTree;

public class Recursion {
    public static void main(String[] args) {
        factorial(6);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // 尾递归
    public static int factorial2(int n, int acc) {
        if (n == 0) {
            return acc;
        }
        return factorial2(n - 1,n * acc);
    }
}
