package com.example.algorithm.string;

/**
 * @author W
 * @date 2022-07-14
 * @see <a href="https://leetcode.cn/problems/add-strings/">...</a>
 */
public class 字符串相加_415 {
    public static void main(String[] args) {
        字符串相加_415 addString = new 字符串相加_415();
        String num1 = "123";
        String num2 = "11";
        System.out.println(addString.addString(num1, num2));
    }

    public String addString(String num1, String num2) {
        StringBuffer result = new StringBuffer();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        //进位
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = n1 + n2 + carry;
            result.append(temp % 10);
            carry = temp / 10;
            //移动指针
            i--;
            j--;
        }
        return result.reverse().toString();
    }
}
