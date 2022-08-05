package com.example.algorithm.bit_operator;

public class 二的幂_231 {
    public static void main(String[] args) {

    }

    public boolean isPowerOfTwo1(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & n - 1) == 0;
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}
