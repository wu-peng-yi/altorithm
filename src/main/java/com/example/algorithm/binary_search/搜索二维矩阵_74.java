package com.example.algorithm.binary_search;

/**
 * @author W
 * @date 2022-07-13
 */
public class 搜索二维矩阵_74 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        搜索二维矩阵_74 search = new 搜索二维矩阵_74();
        System.out.println(search.searchMatrix(matrix, target));
    }

    /**
     * 当做一维数组来看
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        /*int[] temp = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i * n + j] = matrix[i][j];
            }
        }*/
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int midElement = matrix[mid / n][mid % n];
            if (midElement < target) {
                left = mid + 1;
            } else if (midElement > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
