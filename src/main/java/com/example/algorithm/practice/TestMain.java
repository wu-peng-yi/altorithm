package com.example.algorithm.practice;

import com.example.algorithm.linkedlist.ListNode;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        int[][] arr = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(testMain.lengthOfLongestSubstring("abcabcbb"));
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.next;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
                break;
            } else {
                temp = temp.next;
            }
        }
        return dummy.next;
    }

    public int lengthOfLongestSubstring1(String s) {
        //存储字符与位置索引的关系
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        int temp = 0;

        for (int i = 0; i < s.length(); i++) {
            int j = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);
            temp = temp < i - j ? temp + 1 : i - j;
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * 最长不含重复字符的子字符串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        int length = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < length) {
            char c = s.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.get(c) > 1) {
                char removeChar = s.charAt(left);
                map.put(removeChar, map.get(removeChar) - 1);

                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 把数字翻译成字符串
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        String s = String.valueOf(num);
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            String temp = s.substring(i - 2, i);
            dp[i] = (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) ? dp[i - 2] + dp[i - 1] : dp[i - 1];
        }
        return dp[n];
    }

    /**
     * 礼物的最大价值
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = grid[i - 1][j - 1] + Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][m];
    }

    /**
     * 连续子数组的最大和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }
        return max;
    }

    /**
     * 股票最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] dp = new int[prices.length + 1];
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length + 1; i++) {
            minPrice = Math.min(minPrice, prices[i - 1]);
            maxProfit = Math.max(maxProfit, prices[i - 1] - minPrice);
        }
        return maxProfit;
    }

    /**
     * 跳台阶
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int test(int n, int m, int[] an, int[] bn, int[] cn) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1500;
        }
        /**
         * i == 2 j == 4
         * dp[1][4] = 1600
         * dp[1][0] = 1501 + 10
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= cn[i - 1]) {
                    int x = j - cn[i - 1];
                    int t = dp[i - 1][j - cn[i - 1]];
                    int t1 = Math.max(an[i - 1], bn[i - 1]);
                    int m1 = dp[i - 1][j];
                    dp[i][j] = Math.max(dp[i - 1][j], (dp[i - 1][j - cn[i - 1]] + Math.max(an[i - 1], bn[i - 1])));
                } else {
                    dp[i][j] = dp[i - 1][j] + an[i - 1];
                }
            }
        }
        System.out.println();
        return dp[n][m];
    }

    /**
     * 是否对称树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    /**
     * 二叉树的镜像
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }

    /**
     * 树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return recursion(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean recursion(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return recursion(A.left, B.left) && recursion(A.right, B.right);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            int queueSize = queue.size();

            for (int i = queueSize; i > 0; i--) {
                TreeNode tempNode = queue.poll();
                if (flag) {
                    temp.add(tempNode.val);
                } else {
                    temp.addFirst(tempNode.val);
                }
                if (tempNode.left != null) {
                    queue.offer(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.offer(tempNode.right);
                }
            }
            res.add(temp);
            flag = !flag;
        }
        return res;
    }

    /**
     * 从上到下打印二叉树II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode tempNode = queue.poll();
                temp.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.offer(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.offer(tempNode.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 从上到下打印二叉树
     *
     * @param root
     * @return
     */
    public int[] levelOrder1(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return new int[]{};
        }
        queue.offer(root);
        List<Integer> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            temp.add(tempNode.val);

            if (tempNode.left != null) {
                queue.offer(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.offer(tempNode.right);
            }
        }
        int[] res = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        return res;
    }


    /**
     * 第一个只出现一次的字符
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     * 旋转数组重的最小数字
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int n = numbers.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (numbers[mid] < numbers[r]) {
                r = mid;
            } else if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return numbers[l];
    }

    /**
     * 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 0 || m == 0) {
            return false;
        }
        int rowIndex = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] <= target && matrix[i][m - 1] >= target) {
                rowIndex = i;
                int l = 0;
                int r = m - 1;
                while (l <= r) {
                    int mid = (r - l) / 2 + l;
                    if (matrix[rowIndex][mid] == target) {
                        return true;
                    } else if (matrix[rowIndex][mid] < target) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 0 ~ (n - 1) 缺失的数字
     *
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
     *
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
                count++;
                left = mid - 1;
                right = mid + 1;
                while (left >= 0 && nums[left] == target) {
                    count++;
                    left--;
                }
                while (left < nums.length && nums[right] == target) {
                    count++;
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
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




