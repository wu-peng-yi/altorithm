package com.example.algorithm.top100;

/**
 * 正则表达式匹配
 */
public class Six {
    public static void main(String[] args) {

    }

    /**
     * 动态规划
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];
        // s 为空，p 为空 能匹配
        dp[0][0] = true;
        // s 不为空，p 为空 不能匹配，默认为false
        // s 为空，p不为空，只要考虑 * 能匹配的字符
        for (int j = 1; j < cp.length + 1; j++) {
            if (cp[j - 1] == '*') {
                // 根据题目，p 只会出现例如  "A*B*"这样的情况，所以能拿dp[0][j - 2] 的值
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i < cs.length + 1; i++) {
            for (int j = 1; j < cp.length + 1; j++) {
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') {
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }
}
