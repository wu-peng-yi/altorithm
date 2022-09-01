package com.example.algorithm.practice;

import com.example.algorithm.linkedlist.ListNode;

import java.util.*;

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        res.append("[");
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode != null) {
                res.append(currentNode.val).append(",");
                queue.add(currentNode.left);
                queue.add(currentNode.right);
            } else {
                res.append("null,");
            }
        }
        res.substring(0, res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] valArr = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(valArr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            if (!"null".equals(valArr[index])) {
                currNode.left = new TreeNode(Integer.parseInt(valArr[index]));
                queue.add(currNode.left);
            }
            index++;
            if (!"null".equals(valArr[index])) {
                currNode.right = new TreeNode(Integer.parseInt(valArr[index]));
                queue.add(currNode.right);
            }
            index++;
        }
        return root;
    }
}

public class TestMain {

    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>(10);
        objects.add(2, "1");
        System.out.println(objects.get(0));
        TestMain testMain = new TestMain();
        System.out.println(testMain.strToInt("  4193 with words"));
    }

    public String[] permutation(String s) {
        return null;
    }

    /**
     * 滑动窗口的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        if (length <= 0) {
            return nums;
        }
        int[] res = new int[length - k + 1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        //窗口未生成
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[index++] = deque.peekFirst();
        //生成窗口后
        for (int i = k; i < length; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[index++] = deque.peekFirst();
        }
        return res;
    }

    public int strToInt(String str) {
        str = str.trim();
        StringBuffer sb = new StringBuffer();
        int index = 0;
        if (str.charAt(0) == '-') {
            index++;
            sb.append("-");
        } else if (str.charAt(0) == '+') {
            index++;
        }
        for (int i = index; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
                if (Integer.valueOf(Integer.parseInt(sb.toString())) >= Integer.MAX_VALUE || Integer.valueOf(Integer.parseInt(sb.toString())) <= Integer.MIN_VALUE) {
                    break;
                }
            } else {
                break;
            }
        }
        if (sb.length() == 0 || (sb.length() == 1 && (sb.charAt(0) == '-'))) {
            return 0;
        }
        return Integer.valueOf(sb.toString());
    }

    /**
     * 栈的压入，弹出序列
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && popped[i] == stack.peek()) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int l = 0;
        int t = 0;
        int r = matrix[0].length - 1;
        int b = matrix.length - 1;
        int index = 0;
        while (true) {
            // 从左到右
            for (int i = l; i <= r; i++) {
                res[index++] = matrix[t][i];
            }
            if (++t > b) {
                break;
            }
            // 从上到下
            for (int i = t; i <= b; i++) {
                res[index++] = matrix[r][i];
            }
            if (--r < l) {
                break;
            }
            // 从右到左
            for (int i = r; i >= l; i--) {
                res[index++] = matrix[b][i];
            }
            if (--b < t) {
                break;
            }
            // 从下到上
            for (int i = b; i >= t; i--) {
                res[index++] = matrix[i][l];
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }

    /**
     * 圆圈中最后剩下的数字
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }

    /**
     * 和为s的连续正数序列
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int left = 1;
        int right = 2;
        int sum = 3;
        while (left < right) {
            if (sum == target) {
                int[] tempArr = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    tempArr[i - left] = i;
                }
                res.add(tempArr);
            }
            if (sum < target) {
                right++;
                sum += right;
            } else {
                sum -= left;
                left++;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 剪绳子
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int x = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, x);
        }
        if (b == 1) {
            return (int) Math.pow(3, x - 1) * 4;
        }
        return (int) Math.pow(3, x) * 2;
    }

    /**
     * 构建乘积数组
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int res[] = new int[a.length];
        if (a == null || a.length == 0) {
            return a;
        }
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    /**
     * 数组中出现次数超过一半的数字
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int votes = 0;
        int x = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /**
     * 数组中数字出现的次数
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n ^= num;
        }
        int m = 1;
        while ((m & n) == 0) {
            m <<= 1;
        }
        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & m) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    /**
     * 不用乘除做加法
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1; //c 表示进位
            a ^= b;  //异或存储无进位的结果
            b = c;
        }
        return a;
    }

    /**
     * 二进制中1的个数
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    /**
     * 验证二叉树的后序遍历
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int p = left;
        while (postorder[p] < postorder[right]) {
            p++;
        }
        int temp = p;
        while (postorder[p] > postorder[right]) {
            p++;
        }
        return p == right && recur(postorder, left, temp - 1) && recur(postorder, temp, right - 1);
    }

    /**
     * 数值的整数次方
     *
     * @param x
     * @param n
     * @return
     */

    public double myPow(double x, int n) {
        long n1 = n;
        return n1 >= 0 ? quickMul(x, n1) : 1.0 / quickMul(x, -n1);
    }

    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double temp = quickMul(x, n / 2);
        return n % 2 == 0 ? temp * temp : temp * temp * x;
    }

    /**
     * 重建二叉树
     *
     * @param preorder 先序遍历结果
     * @param inorder 中序遍历
     * @return
     */
    public Map<Integer, Integer> treeMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            treeMap.put(inorder[i], i);
        }
        return recurBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode recurBuildTree(int[] preorder, int[] inorder, int pLeft, int pRight, int iLeft, int iRight) {
        if (pLeft > pRight) {
            return null;
        }
        //先序遍历的最左节点就是根结点
        int rootVal = preorder[pLeft];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = treeMap.get(rootVal);
        int leftTreeSize = inRootIndex - iLeft;
        root.left = recurBuildTree(preorder, inorder, pLeft + 1, pLeft + leftTreeSize, iLeft, inRootIndex - 1);
        root.right = recurBuildTree(preorder, inorder, pLeft + leftTreeSize + 1, pRight, iLeft + 1, iRight);
        return root;
    }

    /**
     * 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    /**
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                break;
            }
        }
        return root;
    }

    public int res = 0;

    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }

    /**
     * 是否为平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(right - left) < 2 ? Math.max(right, left) + 1 : -1;
    }

    /**
     * 树的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


    /**
     * 最小的K个数
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int n = arr.length;
        quickSortArr(arr, 0, n - 1, k);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        return Arrays.copyOf(arr, k);
    }

    private void quickSortArr(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int temp = arr[l];
        int i = l;
        int j = r;
        while (i < j) {
            while (arr[j] > arr[l] && i < j) {
                j--;
            }
            while (arr[i] < arr[l] && i < j) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, l);
        if (i == k - 1) {
            return;
        }
        quickSortArr(arr, l, i - 1, k);
        quickSortArr(arr, i + 1, r, k);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 扑克牌中的顺子
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            } else {
                if (repeat.contains(num)) {
                    return false;
                }
                min = Math.min(min, num);
                max = Math.max(max, num);
                repeat.add(num);
            }
        }
        return max - min > 5;
    }

    /**
     * 把数组排成最小的数
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        for (String str : strs) {
            System.out.print(str + "\t");
        }
        StringBuilder res = new StringBuilder();
        for (String s : strs)
            res.append(s);
        return res.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }


    /**
     * 二叉搜索树第k大的节点
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        int res = 0;
        kdfs(res, root, k);
        return res;
    }

    private void kdfs(int res, TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kdfs(res, root.right, k);
        if (--k == 0) {
            res = root.val;
            return;
        }
        kdfs(res, root.left, k);
    }


    /**
     * 二叉搜索树与双向链表
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        return null;
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int sum = 0;
        LinkedList<Integer> tempList = new LinkedList<>();
        backtrackPath(res, root, target, tempList, sum);
        return res;
    }

    private void backtrackPath(List<List<Integer>> res, TreeNode root, int target, LinkedList<Integer> tempList, int sum) {
        if (root.left == null && root.right == null) {
            if (sum + root.val == target) {
                ArrayList<Integer> list = new ArrayList<>(tempList);
                list.add(root.val);
                res.add(list);
            }
        } else {
            tempList.add(root.val);
            sum += root.val;
            if (sum >= target) {
                return;
            }
            if (root.left != null) {
                backtrackPath(res, root.left, target, tempList, sum);
            }
            if (root.right != null) {
                backtrackPath(res, root.right, target, tempList, sum);
            }
            tempList.remove(tempList.size() - 1);
            sum -= root.val;
        }
    }

    /**
     * 机器人的运动范围
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return movingCount(m, n, k, 0, 0, visited);
    }

    private int movingCount(int m, int n, int k, int row, int col, boolean[][] visited) {
        if (row < 0
                || row >= m ||
                col < 0 ||
                col >= n
                || bitNum(row) + bitNum(col) > k
                || visited[row][col]
        ) {
            return 0;
        }
        visited[row][col] = true;
        return 1 + movingCount(m, n, k, row + 1, col, visited) + movingCount(m, n, k, row, col + 1, visited);
    }

    private int bitNum(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }


    /**
     * 矩阵中的路径
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }


    /**
     * 翻转单词顺序
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        int j = s.length() - 1;
        int i = j;
        StringBuffer sb = new StringBuffer();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s.substring(i + 1, j + 1)).append(" ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }
        return sb.toString().trim();
    }

    /**
     * 和为s的两个数字
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (target == 0) {
            return new int[]{0, 0};
        }
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int res = nums[left] + nums[right];
            if (res == target) {
                return new int[]{nums[left], nums[right]};
            } else if (res < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{0, 0};
    }

    /**
     * 调整数组顺序，使奇数位于偶数前面
     */
    public int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }

    /**
     * 两个链表的第一个公共交点
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode t = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                t.next = l1;
                l1 = l1.next;
            } else {
                t.next = l2;
                l2 = l2.next;
            }
            t = t.next;
        }
        if (l1 != null) {
            t.next = l1;
        } else if (l2 != null) {
            t.next = l2;
        }
        return dummy.next;
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

/**
 * 数据流中的中位数
 */
class MedianFinder {

    private List<Integer> list;

    private int length;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList<>();
        length = 0;
    }

    public void addNum(int num) {
        list.add(num);
        length += 1;
        if (length == 1) {
            return;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (i > 0 && list.get(i - 1) > num) {
                list.set(i, list.get(i - 1));
            } else {
                list.set(i, num);
                break;
            }
        }

    }

    public double findMedian() {
        if (length % 2 == 0) {
            System.out.println("偶数");
            System.out.println(list.get(length / 2) + list.get(length / 2 - 1));
            return (list.get(length / 2) + list.get(length / 2 - 1)) / 2.0;
        } else {
            return list.get(length / 2);
        }
    }
}




