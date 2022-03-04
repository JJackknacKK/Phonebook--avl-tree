package cn.edu.sjzc;

public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot; // root node

    public AVLTree() {
        mRoot = null;
    }

    // get height of AVLTree
    private int height(AVLTreeNode<T> root) {
        if (root != null)
            return root.height;

        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    // search with key value

    // time complexity: o(log(n))
    private AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
        if (x == null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    // left left rotation of AVLTree
    // time complexity: o(1)
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;
    }

    // right right rotation of AVLTree
    // time complexity: o(1)
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;
    }

    // left right rotation of AVLTree
    // time complexity: o(1)
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
        k3.left = rightRightRotation(k3.left);

        return leftLeftRotation(k3);
    }

    // right left rotation of AVLTree
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1) {
        k1.right = leftLeftRotation(k1.right);

        return rightRightRotation(k1);
    }

    // insert a new node into AVLTree
    // time complexity: o(logn)
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) throws Exception {
        if (tree == null) {
            // if current node is null, create a new tree node
            tree = new AVLTreeNode<T>(key, null, null);
        } else {
            int cmp = key.compareTo(tree.key);

            if (cmp < 0) {
                // if inserted node value is less than current node, insert it into left node
                tree.left = insert(tree.left, key);
                // if it's unbalanced, balance the tree
                if (height(tree.left) - height(tree.right) > 1) {
                    if (key.compareTo(tree.left.key) < 0)
                        tree = leftLeftRotation(tree);
                    else
                        tree = leftRightRotation(tree);
                }
            } else if (cmp > 0) {
                // if inserted node value is larger than current node value, insert it into
                // right node
                tree.right = insert(tree.right, key);
                // if it's unbalanced, balance the tree
                if (height(tree.right) - height(tree.left) == 2) {
                    if (key.compareTo(tree.right.key) > 0)
                        tree = rightRightRotation(tree);
                    else
                        tree = rightLeftRotation(tree);
                }
            } else {
                // Print out error message when inserting same value node, throw exception
                System.out.println("ERROR: Don't insert the key with same value!");
                throw new Exception("insert node with same value");
            }
        }
        tree.height = max(height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    public boolean insert(T key) {
        try {
            mRoot = insert(mRoot, key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // delete the node from AVLTree
    // time complexity: o(logn)
    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        if (tree == null || z == null)
            return null;

        int cmp = z.key.compareTo(tree.key);
        if (cmp < 0) {
            // the node to be removed is in the left side of AVLTree
            tree.left = remove(tree.left, z);
            // after remove, if it's unbalanced, balance the tree
            if (height(tree.right) - height(tree.left) > 1) {
                AVLTreeNode<T> r = tree.right;
                if (height(r.left) > height(r.right))
                    tree = rightLeftRotation(tree);
                else
                    tree = rightRightRotation(tree);
            }
        } else if (cmp > 0) {
            // the node to be removed is in the right side of AVLTree
            tree.right = remove(tree.right, z);
            // after remove, if it's unbalanced, balance the tree
            if (height(tree.left) - height(tree.right) > 1) {
                AVLTreeNode<T> l = tree.left;
                if (height(l.right) > height(l.left))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {
            // deleted node was found
            if ((tree.left != null) && (tree.right != null)) {
                // when left node and right node both not null
                if (height(tree.left) > height(tree.right)) {
                    AVLTreeNode<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                } else {
                    AVLTreeNode<T> min = maximum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            } else {
                // when left or right node is null
                // just replace it with left or right node
                tree = (tree.left != null) ? tree.left : tree.right;
            }
        }

        return tree;
    }

    // time complexity: o(logn) find minimum value
    private AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        AVLTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    // time complexity: o(logn) find maximum value
    private AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        AVLTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    // remove the node that contains value of key
    // true: successfully remove the node
    // false: the node to be removed was not found
    public boolean remove(T key) {
        AVLTreeNode<T> z;
        if ((z = search(mRoot, key)) != null) {

            mRoot = remove(mRoot, z);
            return true;
        }
        return false;
    }
}
