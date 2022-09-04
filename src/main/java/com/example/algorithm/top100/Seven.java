package com.example.algorithm.top100;

/**
 * 乘最多水的容器
 */
public class Seven {
    public static void main(String[] args) {

    }

    /**
     * 双指针，从两边开始，向内收缩，因为 宽度减小，所以 只能收缩 height低的那一边
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxCap = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            maxCap = Math.max(maxCap, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxCap;
    }
}
