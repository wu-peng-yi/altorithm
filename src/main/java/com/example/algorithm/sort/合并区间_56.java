package com.example.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 合并区间_56 {
    public static void main(String[] args) {

    }

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals,(o1, o2) -> {
            return o1[0] - o2[0];
        });
        //遍历排序后的区间，逐个合并
        for (int[] interval : intervals) {
            //记录当前的左右边界
            int left = interval[0];
            int right = interval[1];

            int length = result.size();
            if (length == 0 || left > result.get(length - 1)[1]) {
                result.add(interval);
            } else {
                int mergedLeft = result.get(length - 1)[0];
                int mergedRight = Math.max(result.get(length - 1)[1],right);
                result.get(length - 1)[1] = mergedRight;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
