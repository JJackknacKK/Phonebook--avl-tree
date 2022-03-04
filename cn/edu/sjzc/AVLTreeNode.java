package cn.edu.sjzc;

// AVLTreeNode
class AVLTreeNode<T extends Comparable<T>> {
    T key; // key of AVLTreeNode
    int height; // current height
    AVLTreeNode<T> left; // left node
    AVLTreeNode<T> right; // right node

    public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}
