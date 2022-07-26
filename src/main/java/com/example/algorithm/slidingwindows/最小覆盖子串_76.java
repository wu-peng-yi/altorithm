package com.example.algorithm.slidingwindows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-16
 */
public class 最小覆盖子串_76 {
    public static void main(String[] args) {
        最小覆盖子串_76 min = new 最小覆盖子串_76();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(min.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0) {
            return "";
        }
        String minSubString = " ";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //动态滑动窗口
        int start = 0;
        int end = 1;
        Map<Character, Integer> subMap = new HashMap<>();
        //定义一个“贡献值”变量
        int count = 0;

        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);
            if (map.containsKey(newChar)) {
                subMap.put(newChar, subMap.getOrDefault(newChar, 0) + 1);

                if (subMap.get(newChar) <= map.get(newChar)) {
                    count++;
                }
            }

            while (count == t.length() && start < end) {
                if (" ".equals(minSubString) || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                //对要删除的字符频次减一
                char removeChar = s.charAt(start);

                if (subMap.containsKey(removeChar)) {
                    subMap.put(removeChar, subMap.getOrDefault(removeChar, 0) - 1);
                    if (subMap.get(removeChar) < map.get(removeChar)) {
                        count --;
                    }
                }
                //只要是覆盖子串，就要缩小窗口
                start++;
            }
            end++;
        }

        return minSubString;
    }

    /**
     * 滑动窗口优化
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow3(String s, String t) {
        if (s.length() == 0) {
            return "";
        }
        String minSubString = " ";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //动态滑动窗口
        int start = 0;
        int end = 1;
        Map<Character, Integer> subMap = new HashMap<>();
        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);

            if (map.containsKey(newChar)) {
                subMap.put(newChar, subMap.getOrDefault(newChar, 0) + 1);
            }

            while (check(map, subMap) && start < end) {
                if (" ".equals(minSubString) || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                //对要删除的字符频次减一
                char removeChar = s.charAt(start);

                if (subMap.containsKey(removeChar)) {
                    subMap.put(removeChar, subMap.getOrDefault(removeChar, 0) - 1);
                }
                //只要是覆盖子串，就要缩小窗口
                start++;
            }
            end++;

        }
        return minSubString;
    }

    public String minWindow2(String s, String t) {
        if (s.length() == 0) {
            return "";
        }
        String minSubString = " ";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //动态滑动窗口
        int start = 0;
        int end = t.length();
        while (end <= s.length()) {
            //统计s子串保存出现的频次
            Map<Character, Integer> subMap = new HashMap<>();
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                int count = subMap.getOrDefault(c, 0);
                subMap.put(c, count + 1);
            }

            //如果当前子串符合要求，并且比之前的子串要短就替换
            if (check(map, subMap)) {
                if (" ".equals(minSubString) || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                //只要是覆盖子串，就要缩小窗口
                start++;
            } else {
                end++;
            }
        }
        return minSubString;
    }

    // 暴力法
    public String minWindow1(String s, String t) {
        if (s.length() == 0) {
            return "";
        }
        String minSubString = "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int n = s.length();
        int m = t.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + m; j <= n; j++) {
                //统计s子串保存出现的频次
                Map<Character, Integer> subMap = new HashMap<>();
                for (int k = i; k < j; k++) {
                    char c = s.charAt(k);
                    int count = subMap.getOrDefault(c, 0);
                    subMap.put(c, count + 1);
                }

                //如果当前子串符合要求，并且比之前的子串要短就替换
                if (check(map, subMap) && ("".equals(minSubString) || j - i < minSubString.length())) {
                    minSubString = s.substring(i, j);
                }
            }
        }
        return minSubString;
    }

    public boolean check(Map<Character, Integer> tFreq, Map<Character, Integer> subStringFreq) {
        // 遍历t中每个字符的频次，与substring比较
        for (char c : tFreq.keySet()) {
            if (subStringFreq.getOrDefault(c, 0) < tFreq.get(c)) {
                return false;
            }
        }
        return true;
    }
}
