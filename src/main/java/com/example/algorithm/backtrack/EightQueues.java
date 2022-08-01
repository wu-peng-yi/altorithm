package com.example.algorithm.backtrack;

import java.util.*;

public class EightQueues {
    public static void main(String[] args) {

    }

    Set<Integer> cols = new HashSet<>();
    Set<Integer> diags1 = new HashSet<>();
    Set<Integer> diags2 = new HashSet<>();

    public List<int[]> eightQueues() {
        List<int[]> res = new ArrayList<>();

        //用一个int[8]保存一组解
        int[] solution = new int[8];

        //先做初始填充，表示皇后没有填充
        Arrays.fill(solution, -1);

        //定义回溯方法
        backTrack(res, solution, 0);
        return res;
    }

    private void backTrack(List<int[]> res, int[] solution, int row) {
        //处理递归结束场景
        if (row >= 8) {
            res.add(Arrays.copyOf(solution, 8));
        } else {
            //当前行，考察可能的皇后位置
            for (int column = 0; column < 8; column++) {
                //判断是否冲突
                if (cols.contains(column)) {
                    continue;
                }
                int diag1 = row - column;
                int diag2 = row + column;
                if (diags1.contains(diag1)) {
                    continue;
                }
                if (diags2.contains(diag2)) {
                    continue;
                }

                //不冲突，可以放置皇后
                solution[row] = column;
                cols.add(column);
                diags2.add(diag2);
                diags1.add(diag1);

                //递归
                backTrack(res,solution,row + 1);

                //回溯，将状态回滚
                solution[row] = -1;
                cols.remove(column);
                diags1.remove(diag1);
                diags2.remove(diag2);
            }
        }
    }

    //暴力法
    public List<int[]> eightQueues1() {
        List<int[]> res = new ArrayList<>();

        //用一个int[8]保存一组解
        int[] solution = new int[8];

        //遍历所有情况
        for (solution[0] = 0; solution[0] < 8; solution[0]++) {

        }
        return null;
    }
}
