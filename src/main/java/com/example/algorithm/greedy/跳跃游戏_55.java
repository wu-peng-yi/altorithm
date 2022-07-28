package com.example.algorithm.greedy;

public class 跳跃游戏_55 {
    public static void main(String[] args) {

    }

    public boolean canJump(int[] nums) {
        //定义一个变量，保存当前能够跳到的最远的位置
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= nums.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
