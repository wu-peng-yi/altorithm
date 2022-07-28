package com.example.algorithm.binaryTree;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class 平衡二叉树_110 {

    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        return balancedHeight(root) > -1;
    }

    public int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //递归计算左右子树
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

        if (rightHeight == -1 || leftHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    //定义一个计算树高度的方法
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

}
