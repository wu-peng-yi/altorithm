package com.example.algorithm.stackAndQueue;

import java.util.Stack;

/**
 * @author W
 * @date 2022-07-20
 * @see <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/"></a>
 */
public class 柱状图中的最大矩形_84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        柱状图中的最大矩形_84 height = new 柱状图中的最大矩形_84();
        System.out.println(height.largestRectangleArea(heights));
    }

    //单调栈优化
    public int largestRectangleArea(int[] heights) {
        //定义保存最大面积
        int largestArea = 0;

        //定义两个数组保存每个柱子的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        //初始化rights为哨兵
        for (int i = 0; i < n; i++) {
            rights[i] = n;
        }

        //定义栈
        Stack<Integer> stack = new Stack<>();
        //遍历所有数据，作为当前高度，先找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                //栈顶元素如果小于当前元素，那么他的右边界就是当前元素
                rights[stack.peek()] = i;
                stack.pop();
            }
            //所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }
        return largestArea;
    }

    //使用单调栈
    public int largestRectangleArea4(int[] heights) {
        //定义保存最大面积
        int largestArea = 0;

        //定义两个数组保存每个柱子的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        //定义栈
        Stack<Integer> stack = new Stack<>();
        //遍历所有数据，作为当前高度，先找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            //所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();
        //遍历所有数据，作为当前高度，先找右边界
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            //所有大于等于当前高度的元素全部弹出，找到了左边界
            rights[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }
        return largestArea;
    }

    //双指针法改进
    public int largestRectangleArea3(int[] heights) {
        //定义保存最大面积
        int largestArea = 0;

        //定义两个数组保存每个柱子的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        for (int i = 0; i < n; i++) {
            int height = heights[i];

            int left = i - 1;

            //左指针左移
            while (left >= 0) {
                if (heights[left] < height) {
                    break;
                }
                left = lefts[left];  //如果左边柱子更高，直接跳到左边界柱子判断
            }
            lefts[i] = left;
        }

        for (int i = n - 1; i >= 0; i++) {
            int height = heights[i];
            int right = i + 1;

            //左指针左移
            while (right < n) {
                if (heights[right] < height) {
                    break;
                }
                right = rights[right];  //如果右边柱子更高，直接跳到右边界柱子判断
            }
            rights[i] = right;
        }

        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }

        return largestArea;
    }

    //双指针法
    public int largestRectangleArea2(int[] heights) {
        //定义保存最大面积
        int largestArea = 0;

        for (int i = 0; i < heights.length; i++) {
            //保存当前高度
            int height = heights[i];
            int left = i, right = i;
            while (left >= 0) {
                if (heights[left] < height) {
                    break;
                }
                left--;
            }
            while (right <= heights.length - 1) {
                if (heights[right] < height) {
                    break;
                }
                right++;
            }
            //计算当前矩形面积
            int currArea = (right - left - 1) * height;
            largestArea = Math.max(largestArea, currArea);
        }
        return largestArea;
    }

    //暴力法,固定宽度
    public int largestRectangleArea1(int[] heights) {
        //定义保存最大面积
        int largestArea = 0;

        for (int left = 0; left < heights.length; left++) {
            //定义右边界
            int currentHeight = heights[left];
            for (int right = left; right < heights.length; right++) {
                // 确定当前矩形的高度
                currentHeight = Math.min(currentHeight, heights[right]);
                //计算当前矩形面积
                int currArea = (right - left + 1) * currentHeight;
                largestArea = Math.max(largestArea, currArea);
            }
        }
        return largestArea;
    }
}
