package com.huifer.data.tree;

/**
 * <p>Title : TreeNode </p>
 * <p>Description : 树节点</p>
 *
 * @author huifer
 * @date 2019-04-24
 */
public class TreeNode<T> {

    T data;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode() {
    }

    /**
     * 左节点增加
     *
     * @param data
     */
    public void addLeft(T data) {
        TreeNode<T> left = new TreeNode<>(data);
        this.leftChild = left;
    }

    /**
     * 右节点增加
     *
     * @param data
     */
    public void addRight(T data) {
        TreeNode<T> right = new TreeNode<>(data);
        this.rightChild = right;
    }


}
