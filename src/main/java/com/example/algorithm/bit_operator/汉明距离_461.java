package com.example.algorithm.bit_operator;

public class 汉明距离_461 {
    public static void main(String[] args) {

    }

    public int hammingDistance2(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            xor &= (xor - 1);
            count ++;
        }
        return count;
    }

    public int hammingDistance1(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            if ((xor & 1) == 1) {
                count ++;
            }
            xor >>= 1;
        }
        return count;
    }

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

}
