package com.example.algorithm.greedy;

public class 跳跃游戏II_45 {
    public static void main(String[] args) {

    }

    /**
     * 正向跳跃改进，考虑能够跳到的最远的两步----只用遍历一次数组
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int steps = 0;

        //定义双指针，分别指向跳一步和跳两步最远的位置
        int firstFarthest = 0;
        int secondFarthest = firstFarthest;

        //不停寻找下一步的合适位置
//        while (firstFarthest <= nums.length - 1) {
        //遍历当前位置和第一步最远位置之间的所有元素并且寻找第二步
        for (int i = 0; i < nums.length - 1; i++) {
            //如果比当前第二步最远距离大，就更新
            if (i + nums[i] > secondFarthest) {
                secondFarthest = i + nums[i];
            }
            if (i == firstFarthest) {
                steps++;
                firstFarthest = secondFarthest;
            }
        }

//        }
        return steps;
    }

    /**
     * 正向跳跃，考虑能够跳到的最远的两步
     *
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int steps = 0;
        int currPosition = 0;

        //定义双指针，分别指向跳一步和跳两步最远的位置
        int firstFarthest = 0;
        int secondFarthest = firstFarthest;

        //不停寻找下一步的合适位置
        while (firstFarthest <= nums.length - 1) {
            //遍历当前位置和第一步最远位置之间的所有元素并且寻找第二步
            for (int i = currPosition; i <= firstFarthest; i++) {
                //如果比当前第二步最远距离大，就更新
                if (i + nums[i] > secondFarthest) {
                    secondFarthest = i + nums[i];
                    currPosition = i;
                }
            }

            steps++;
            firstFarthest = secondFarthest;
        }
        return steps;
    }

    /**
     * 方法一，方向跳跃
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        //定义一个变量保存跳跃步数
        int steps = 0;

        //定义循环变量从后往前跳
        int currPosition = nums.length - 1;
        while (currPosition > 0) {
            //从前往后遍历数组元素，找到当前距离最远的
            for (int i = 0; i < currPosition; i++) {
                if ((nums[i] + i) >= currPosition) {
                    currPosition = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
