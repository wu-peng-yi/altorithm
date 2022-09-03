package com.example.algorithm.top100;

/**
 * 最长回文子串
 */
public class Five {
    public static void main(String[] args) {
        Five five = new Five();
        System.out.println(five.longestPalindrome("cbbd"));
    }

    /**
     * 动态规划
     * dp[l][r] 存储 l -> r 是否为回文
     * 那么 如果 dp[l + 1][r - 1] 为回文 && s[l] == s[r] 则 dp[l][r] 也为回文
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int length = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[length][length];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                // (i - j <= 2) 表示要么只有自己一个节点或者回文数为偶数，就不用判断之前的动态了
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxStart = j;
                        maxEnd = i;
                        maxLen = i - j + 1;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     * 中心扩散法
     * 从每个点开始往两边找
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int length = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int resLeft = 0;  // 因为是要返回最长回文子串，所以需要记录结果开始截取的位置
        int maxLen = 0;
        for (int i = 0; i < length; i++) {
            //当前节点当作中心节点，所以左右节点要放在两边
            left = i - 1;
            right = i + 1;
            // 找到左边和右边第一个 不等于 中心节点的位置
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                len++;
            }
            while (right < length && s.charAt(right) == s.charAt(i)) {
                right++;
                len++;
            }
            //向两边扩散，判断是否回文
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left --;
                right++;
                len += 2;
            }
            if (len > maxLen) {
                maxLen = len;
                resLeft = left;
            }
            len = 1;
        }
        return s.substring(resLeft + 1,resLeft + maxLen + 1);
    }
}
