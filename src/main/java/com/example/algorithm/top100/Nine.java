package com.example.algorithm.top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nine {
    public static void main(String[] args) {

    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        Map<Character,String> phoneMap = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backTrack(res,phoneMap,digits,0,new StringBuilder());
        return res;
    }

    private void backTrack(List<String> res, Map<Character, String> phoneMap, String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char digit = digits.charAt(index);
        String phone = phoneMap.get(digit);
        for (int i = 0; i < phone.length(); i++) {
            sb.append(phone.charAt(i));
            backTrack(res,phoneMap,digits,index++,sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
