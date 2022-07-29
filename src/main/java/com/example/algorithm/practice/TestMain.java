package com.example.algorithm.practice;

import com.example.algorithm.linkedlist.ListNode;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        int[] arr = new int[]{0,1,3,4,5};
        int num = testMain.missingNumber(arr);
        System.out.println(num);
    }

    /**
     * 0 ~ (n - 1) 缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else if (nums[mid] > mid) {
                right = mid - 1;
            }
        }
        return left;
    }

    public int search1(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    public int helper(int[] nums, int target) {
        int left = 0;
        int right = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 在排序数组中查找数字
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                count ++;
                left = mid - 1;
                right = mid + 1;
                while (left >= 0 && nums[left] == target) {
                    count ++;
                    left--;
                }
                while (left < nums.length && nums[right] == target) {
                    count ++;
                    right++;
                }
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return count;
    }

    /**
     * 找到重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }

    /**
     * 找到重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            } else {
                map.put(num, 1);
            }
        }
        return -1;
    }


    /**
     * 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        int length = s.length();
        if (length == 0 || length == 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(s.charAt((n + i) + length) % length);
        }
        return sb.toString();
    }

    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 从尾到头打印链表
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    public int[] reversePrint1(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        getArray(head);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    List<Integer> list = new ArrayList<>();

    private void getArray(ListNode head) {
        if (head == null) {
            return;
        }
        getArray(head.next);
        list.add(head.val);
    }

    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        ListNode temp;
        while (head != null) {
            temp = head.next;
            head.next = res;
            res = head;
            head = temp;
        }
        return res;
    }

    Map<Node, Node> cacheNodes = new HashMap<>();

    /**
     * 拷贝链表
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (!cacheNodes.containsKey(head)) {
            Node headNew = new Node(head.val);
            cacheNodes.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cacheNodes.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
