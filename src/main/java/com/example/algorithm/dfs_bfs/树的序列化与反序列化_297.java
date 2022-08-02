package com.example.algorithm.dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 树的序列化与反序列化_297 {
    public static void main(String[] args) {

    }

    // Encodes a tree to a single string.
    // 序列化方法
    public String serialize(TreeNode root) {
        // 定义一个StringBuffer来保存序列化结果
        StringBuffer result = new StringBuffer();
        result.append("[");
        dfs_serialize(root, result);
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    private void dfs_serialize(TreeNode root, StringBuffer result) {
        if (root == null) {
            result.append("null,");
            return;
        }

        //当前根结点
        result.append(root.val).append(",");
        dfs_serialize(root.left, result);
        dfs_serialize(root.right, result);
    }

    // Decodes your encoded data to tree.
    // 反序列化方法
    public TreeNode deserialize(String data) {
        // 先将数据进行切分
        String[] dataArr = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));

        String firstElement = dataList.getFirst().substring(1);
        dataList.removeFirst();
        dataList.addFirst(firstElement);

        String lastElement = dataList.getLast().substring(0, dataList.getLast().length() - 1);
        dataList.removeLast();
        dataList.addLast(lastElement);

        return dfs_deserialize(dataList);

    }

    private TreeNode dfs_deserialize(LinkedList<String> dataList) {
        // 基准情况
        if ("null".equals(dataList.getFirst())) {
            dataList.removeFirst();
            return null;
        }
        // 获取当前节点
        TreeNode node = new TreeNode(Integer.valueOf(dataList.getFirst()));
        dataList.removeFirst();
        node.left = dfs_deserialize(dataList);
        node.right = dfs_deserialize(dataList);
        return node;
    }
}
