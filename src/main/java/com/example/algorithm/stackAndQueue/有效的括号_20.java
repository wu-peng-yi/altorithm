package com.example.algorithm.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-20
 */
public class 有效的括号_20 {
    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        //遍历所有字符依次判断
        for (char c : s.toCharArray()) {
            //左括号时将对应的右括号入栈
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != 'c') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
