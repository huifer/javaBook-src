package com.huifer.data.tree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * <p>Title : TreeTools </p>
 * <p>Description : 树处理工具</p>
 *
 * @author huifer
 * @date 2019-04-28
 */
public class TreeTools {

    public static void main(String[] args) {
        TreeNode<Integer> tree = new TreeNode<>(1);
        tree.addLeft(2);
        tree.addRight(3);
        tree.leftChild.addRight(4);
        tree.rightChild.addRight(5);
        System.out.println("节点数量");
        System.out.println(TreeTools.getTreeSize(tree));
        System.out.println("树的深度");
        System.out.println(TreeTools.getTreeDepth(tree));
        System.out.println("前序遍历");
        preOrderTravel(tree);
        System.out.println("中序遍历");
        inorderTraversal(tree);
        System.out.println("后序遍历");
        postorderTraversal(tree);
        System.out.println("分层遍历");
        levelTravel(tree);
        System.out.println("K层节点数量");
        int numForKLevel = getNumForKLevel(tree, 2);
        System.out.println(numForKLevel);


        TreeNode<String> T2 = new TreeNode<>("A");
        T2.addLeft("B");
        T2.leftChild.addLeft("D");
        T2.leftChild.leftChild.addLeft("G");
        T2.leftChild.leftChild.addRight("H");

        T2.addRight("C");
        T2.rightChild.addLeft("E");
        T2.rightChild.addRight("F");
        T2.rightChild.leftChild.addRight("I");
        System.out.println("前序遍历");
        preOrderTravel(T2);
        System.out.println("中序遍历");
        inorderTraversal(T2);
        System.out.println("后序遍历");
        postorderTraversal(T2);
        System.out.println("=======================");
        System.out.println("层次遍历");
        levelTravel(T2);
        System.out.println();
        System.out.println("=======================");


        TreeNode<String> t3 = new TreeNode<>("A");
        t3.addLeft("B");
        t3.leftChild.addLeft("C");
        t3.leftChild.addRight("D");
        t3.addRight("E");
        t3.rightChild.addLeft("F");
        t3.rightChild.addRight("G");
        levelTravel(t3);

    }

    /**
     * 获取树的节点数量
     *
     * @param root 根节点
     * @return 节点数量
     */
    public static <T> int getTreeSize(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return getTreeSize(root.leftChild) + getTreeSize(root.rightChild) + 1;
    }

    /**
     * 获取树的深度
     *
     * @param root 根节点
     * @return 深度
     */
    public static <T> int getTreeDepth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.leftChild) + 1;
        int rightDepth = getTreeDepth(root.rightChild) + 1;
        return Math.max(leftDepth, rightDepth);
    }


    /**
     * 输出结点的数据
     *
     * @param node 结点
     */
    private static <T> void printNodeValue(TreeNode<T> node) {
        System.out.println(node.data + "\t");
    }

    /**
     * 前序遍历，注: 中序遍历 后序遍历将print位置调整即可
     */
    public static <T> void preOrderTravel(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        printNodeValue(root);
        preOrderTravel(root.leftChild);
        preOrderTravel(root.rightChild);
    }

    /**
     * 中序遍历，注: 中序遍历 后序遍历将print位置调整即可
     */
    public static <T> void inorderTraversal(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.leftChild);
        printNodeValue(root);
        inorderTraversal(root.rightChild);
    }

    /**
     * 后序遍历，注: 中序遍历 后序遍历将print位置调整即可
     */
    public static <T> void postorderTraversal(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.leftChild);
        postorderTraversal(root.rightChild);
        printNodeValue(root);
    }


    /**
     * 分层遍历
     */
    public static <T> void levelTravel(TreeNode<T> root) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> tem = queue.poll();
            printNodeValue(tem);
            if (tem.leftChild != null) {
                queue.offer(tem.leftChild);
            }
            if (tem.rightChild != null) {
                queue.offer(tem.rightChild);
            }
        }
    }

    /**
     * 返回k层的结点个数
     */
    public static <T> int getNumForKLevel(TreeNode<T> root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        int leftNum = getNumForKLevel(root.leftChild, k - 1);
        int rightNum = getNumForKLevel(root.rightChild, k - 1);

        return leftNum + rightNum;
    }


}
