package com.example.algorithm.arrays;

/**
 * @author W
 * @date 2022-07-12
 * @see <a href="https://leetcode.cn/problems/rotate-image/">...</a>
 */
public class 旋转图像_48 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        旋转图像_48 rotate = new 旋转图像_48();
        System.out.println("matrix1");
        rotate.print(matrix);
        rotate.print(matrix2);
    }

    private void print(int[][] matrix) {
        for (int[] line : matrix) {
            for (int i : line) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 方法三，方法二的简单改进
     */

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 方法二，分治思想，分为两个子矩阵分别考虑
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        //只需要遍历 1/4 矩阵
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 对于matrix[i][j],需要找到不同的四个矩阵中对应的另外三个位置和元素
                int[] temp = new int[4];
                int row = i;
                int col = j;

                // 行列转换规律，row + newCol = n - 1, col = newRow
                // (1,0) -> (0,2) -> (2,3) -> (3,1)
                for (int k = 0; k < 4; k++) {
                    temp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                //再次遍历要处理的四个位置，将旋转之后的数据填入
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = temp[(k + 3) % 4];
                }
            }
        }
    }

    /**
     * 方法一，数学方法(矩阵转置后翻转)
     * 1.可以先转置矩阵，行 -> 列
     * 2.翻转每一行
     * <p>
     * 时间复杂度 O(n²)
     * 空间复杂度 O(1)  只需要一个temp变量临时存储
     *
     * @param matrix 原生矩阵
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;

        //1.转置矩阵(对角线元素不动，其他以对角线对称交换)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
