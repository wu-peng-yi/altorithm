package com.example.algorithm.top100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 */
public class Eleven {
    public static void main(String[] args) {
        Eleven eleven = new Eleven();
        System.out.println(eleven.isValid("(]"));
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');

        Stack<Character> stack = new Stack<>();
        if (s == null || s.length() == 0) {
            return true;
        }
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
