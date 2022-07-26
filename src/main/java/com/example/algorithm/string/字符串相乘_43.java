package com.example.algorithm.string;


/**
 * @author W
 * @date 2022-07-14
 */
public class 字符串相乘_43 {
    public static void main(String[] args) {
        字符串相乘_43 mul = new 字符串相乘_43();
        String num1 = "123";
        String num2 = "456";
        System.out.println(mul.multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        //有一个为0，结果为0
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int[] resultArray = new int[num1.length() + num2.length()];

        //双重遍历
        for (int i = num1.length() - 1; i >= 0 ; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j --) {
                int n2 = num2.charAt(j) - '0';
                int product = n1 * n2;

                //保存到结果数组
                int sum = product + resultArray[i + j + 1];
                resultArray[i + j + 1] = sum % 10;
                resultArray[i + j] = sum / 10;
            }
        }
        return "";
    }

    /**
     * 双重循环，每次num1的值与num2的每一位进行乘积运算，再累加，但是要加'0'
     * @param num1
     * @param num2
     * @return
     */
    public String multiply1(String num1, String num2) {
        //有一个为0，结果为0
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String result = "0";
        String addZero = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            //取出当前数位
            int n2 = num2.charAt(i) - '0';
            StringBuffer currenResult = new StringBuffer();
            //定义进位
            int carry = 0;
            currenResult.append(addZero);
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0';
                int tempRes = n2 * n1 + carry;
                currenResult.append(tempRes % 10);
                carry = tempRes / 10;
            }
            if (carry != 0) {
                currenResult.append(carry);
            }
            result = addString(result,currenResult.reverse().toString());
            addZero += "0";
        }
        return result;
    }

    private String addString(String num1, String num2) {
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
