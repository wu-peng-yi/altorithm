package com.example.algorithm.slidingwindows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-16
 */
public class 找到字符串中所有字母异位词_438 {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        int[] pCharCounts = new int[26];
        for (char c : p.toCharArray()) {
            pCharCounts[c - 'a']++;
        }
        //定义左右指针
        int start = 0, end = 1;
        int[] subCharCounts = new int[26];
        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);
            subCharCounts[newChar - 'a']++;

            while (subCharCounts[newChar - 'a'] > pCharCounts[newChar - 'a'] && start < end) {
                char removeChar = s.charAt(start);
                subCharCounts[removeChar - 'a']--;
                start++;
            }
            if (end - start == p.length()) {
                result.add(start);
            }
            end++;
        }
        return result;
    }

    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();

        int[] pCharCounts = new int[26];

        for (char c : p.toCharArray()) {
            pCharCounts[c - 'a']++;
        }

        //遍历s, 长度为P.length
        for (int i = 0; i <= s.length() - p.length(); i++) {
            //判断异位词
            boolean isMatched = true;
            int[] subCharCounts = new int[26];
            for (int j = i; j < i + p.length(); j++) {
                subCharCounts[s.charAt(j) - 'a']++;
                if (subCharCounts[s.charAt(j) - 'a'] > pCharCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched) {
                result.add(i);
            }
        }
        return result;
    }
}
