package com.example.algorithm.dfs_bfs;

import java.util.*;

public class 课程表_207 {
    public static void main(String[] args) {

    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //存储邻接矩阵
        Map<Integer, ArrayList<Integer>> followCourses = new HashMap<>();

        //遍历先决条件
        for (int[] prerequisite : prerequisites) {
            ArrayList<Integer> followCourse = followCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followCourse.add(prerequisite[0]);
            followCourses.put(prerequisite[1], followCourse);
        }

        //定义一个栈，优先搜索最后学习的课程
        Stack<Integer> lastCourses = new Stack<>();

        //定义一个数组
        boolean[] isSearched = new boolean[numCourses];
        boolean canFinish = true;

        // 遍历每一个节点 dfs
        for (int i = 0; i < numCourses && canFinish; i++) {
            if (!lastCourses.contains(i)) {
                //不在栈内就搜索,递归方法
                canFinish = dfs(followCourses, lastCourses, isSearched, i);

            }
        }
        return canFinish;
    }

    private boolean dfs(Map<Integer, ArrayList<Integer>> followCourses, Stack<Integer> lastCourses, boolean[] isSearched, int i) {
        // 当前节点在路径中出现
        isSearched[i] = true;

        for (Integer followCourse : followCourses.getOrDefault(i, new ArrayList<>())) {
            if (isSearched[followCourse]) {
                return false;
            } else {
                if (!dfs(followCourses, lastCourses, isSearched, followCourse)) {
                    return false;
                };
            }
        }

        // 后序节点处理完毕，当前节点入栈
        isSearched[i] = false;
        return true;
    }

    // bfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 保存所有节点入度
        int[] inDegrees = new int[numCourses];

        //存储邻接矩阵
        Map<Integer, ArrayList<Integer>> followCourses = new HashMap<>();

        //遍历先决条件
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++; //后修课程入度 + 1

            ArrayList<Integer> followCourse = followCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followCourse.add(prerequisite[0]);
            followCourses.put(prerequisite[1], followCourse);
        }

        // 保存当前可以学习的课程
        LinkedList<Integer> selectableCourses = new LinkedList<>();

        // 启动bfs搜索
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                selectableCourses.offer(i);
            }
        }

        int finishedCoursesNum = 0;

        //学习课程，后序课程入度 - 1，将变更后可学习课程入队
        while (!selectableCourses.isEmpty()) {
            Integer course = selectableCourses.poll();
            finishedCoursesNum++;

            for (Integer followCourse : followCourses.getOrDefault(course, new ArrayList<>())) {
                inDegrees[followCourse]--;

                if (inDegrees[followCourse] == 0) {
                    selectableCourses.offer(followCourse);
                }
            }
        }

        return finishedCoursesNum == numCourses;
    }
}
