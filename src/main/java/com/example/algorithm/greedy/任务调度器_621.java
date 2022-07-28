package com.example.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 任务调度器_621 {
    public static void main(String[] args) {

    }

    /**
     * 方法二：构造法
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval1(char[] tasks, int n) {
        //用hashmap统计每个任务出现的次数
        Map<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }

        //任务种类数量
        int t = countMap.size();

        int maxCount = 0;
        int maxNum = 0;

        //计算maxCount
        for (Integer value : countMap.values()) {
            maxCount = Math.max(maxCount, value);
        }

        //计算maxNum
        for (Integer count : countMap.values()) {
            if (count == maxCount) {
                maxNum++;
            }
        }

        //按照公式直接返回
        return Math.max(tasks.length,(maxCount - 1) * (n + 1) + maxNum);
    }

    /**
     * 方法一：模拟法
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        //用hashmap统计每个任务出现的次数
        Map<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }

        //任务种类数量
        int t = countMap.size();

        //定义两个状态列表
        ArrayList<Integer> restCount = new ArrayList<>(countMap.values()); //任务剩余次数
        ArrayList<Integer> nextAvailableTime = new ArrayList<>(Collections.nCopies(t, 1)); //下一次任务可执行时间

        int time = 0;

        //遍历任务选择执行
        for (int i = 0; i < tasks.length; i++) {
            time++;
            //获取所有任务中最早可执行时间
            int minNextAvailableTime = Integer.MAX_VALUE;
            for (int j = 0; j < t; j++) {
                if (restCount.get(j) != 0) {
                    minNextAvailableTime = Math.min(minNextAvailableTime, nextAvailableTime.get(j));
                }
            }

            //直接推进时间，执行任务
            time = Math.max(time, minNextAvailableTime);

            //选取可执行次数最多的任务执行
            int maxRestCountTask = -1;
            for (int j = 0; j < t; j++) {
                if (restCount.get(j) > 0 && nextAvailableTime.get(j) <= time) {
                    if (maxRestCountTask == -1 || restCount.get(j) > restCount.get(maxRestCountTask)) {
                        maxRestCountTask = j;
                    }
                }
            }

            //执行任务，更新状态列表
            nextAvailableTime.set(maxRestCountTask, time + n + 1);
            restCount.set(maxRestCountTask, restCount.get(maxRestCountTask) - 1);
        }
        return time;
    }
}
