package com.example.algorithm.top100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 */
public class Three {
    public static void main(String[] args) {
        Three three = new Three();
        System.out.println(three.lengthOfLongestSubstring1("tmmzuxt"));
    }

    /**
     * hashmap 优化，减少遍历次数
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int left = 0;
        int right = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c,right);
            right++;
            res = Math.max(res, right - left);
        }
        return res;
    }


    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int left = 0;
        int right = 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        while (left <= right && right < n) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                right++;
            } else {
                set.remove(s.charAt(left++));
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
