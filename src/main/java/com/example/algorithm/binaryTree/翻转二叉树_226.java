package com.example.algorithm.binaryTree;

public class 翻转二叉树_226 {
    public static void main(String[] args) {

    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        //先处理根结点，再交换左右子节点
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 先序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }

        //先处理根结点，再交换左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree1(root.left);
        invertTree1(root.right);

        return root;
    }
}
