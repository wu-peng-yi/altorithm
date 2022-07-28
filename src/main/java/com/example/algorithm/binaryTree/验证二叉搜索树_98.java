package com.example.algorithm.binaryTree;

import java.util.ArrayList;
import java.util.List;



public class 验证二叉搜索树_98 {

    List<Integer> inOrderArray = new ArrayList<>();

    //利用中序遍历，如果单调递增，则为true
    public boolean isValidBST1(TreeNode root) {

        inOrder(root);

        for (int i = 1; i < inOrderArray.size(); i++) {
            if (inOrderArray.get(i) <= inOrderArray.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return ;
        }
        inOrder(root.left);
        inOrderArray.add(root.val);
        inOrder(root.right);
    }

    //先序遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validator(root, null, null);
    }

    //辅助校验用来递归调用
    public boolean validator(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) {
            return true;
        }
        if (lowerBound != null && root.val <= lowerBound) {
            return false;
        }
        if (upperBound != null && root.val >= upperBound) {
            return false;
        }

        return validator(root.left, lowerBound, root.val) && validator(root.right, root.val, upperBound);
    }
}
