/**
 * Assignment 9 for CS 1410
 * This class is used to create and perform operations on a binary search tree
 *
 * @author Brock Francom
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode root;
    private int nodeNumber = 0;
    private int leafNodes = 0;

    private class TreeNode {
        public E value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }
    // insert a value into the tree
    public boolean insert(E value) {
        if (root == null) {
            root = new TreeNode(value);
            return true;
        }
        else {
            TreeNode parent = null;
            TreeNode node = root;
            while (node != null) {
                parent = node;
                if ((node.value.compareTo(value) < 0)) {
                    node = node.right;
                }
                else if (node.value.equals(value)) {
                    return false;
                }
                else {
                    node = node.left;
                }
            }
            TreeNode newNode = new TreeNode(value);
            if (parent.value.compareTo(value) < 0) {
                parent.right = newNode;
                return true;
            }
            else {
                parent.left = newNode;
                return true;
            }
        }
    }
    // recursively display the tree in order
    public void display(E s) {
        System.out.println(" ");
        System.out.println(s);
        display(root);
    }
    // 2nd half of recursion
    private void display(TreeNode node) {
        if (node != null) {
            display(node.left);
            System.out.println(node.value);
            display(node.right);
        }
    }
    // remove a value from the tree if it is there, else do nothing
    public boolean remove(E value) {
        if (search(value)) {
            TreeNode parent = null;
            TreeNode node = root;
            boolean done = false;
            while (!done) {
                if (node.value.compareTo(value) < 0) {
                    parent = node;
                    node = node.right;
                } else if (node.value.compareTo(value) > 0) {
                    parent = node;
                    node = node.left;
                } else {
                    done = true;
                }
            }

            // Case for the no left child
            if (node.left == null) {
                if (parent == null) {
                    root = node.right;
                } else {
                    if (parent.value.compareTo(value) < 0) {
                        parent.right = node.right;
                    } else {
                        parent.left = node.right;
                    }
                }
            } else {
                TreeNode parentOfRight = node;
                TreeNode rightMost = node.left;
                while (rightMost.right != null) {
                    parentOfRight = rightMost;
                    rightMost = rightMost.right;
                }
                node.value = rightMost.value;
                if (parentOfRight.right == rightMost) {
                    parentOfRight.right = rightMost.left;
                } else {
                    parentOfRight.left = rightMost.left;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    // recursively count the number of nodes
    public int numberNodes() {
        nodeNumber = 0;
        TreeNode node = root;
        numberNodes(node);
        return nodeNumber;
    }
    // 2nd half of the recursion
    private void numberNodes(TreeNode node) {
        if (node == null) {
            return;
        }
        if ((node.left == null) && (node.right == null)) {
            nodeNumber += 1;
        }
        else {
            nodeNumber += 1;
            numberNodes(node.left);
            numberNodes(node.right);
        }

    }
    // recursively find the height of the tree
    public int height() {
        TreeNode node = root;

        return Math.max(height(node.right), height(node.left));
    }
    // 2nd half of the recursion
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        else if (node.left == null && node.right == null) {
            return 1;
        }
        return 1 + Math.max(height(node.right), height(node.left));
    }
    // recursively find the number of leaf nodes
    public int numberLeafNodes() {
        leafNodes = 0;
        numberLeafNodes(root);
        return leafNodes;
    }
    // 2nd half of the recursion
    private void numberLeafNodes(TreeNode node) {
        if ((node.left == null) && (node.right == null)) {
            leafNodes += 1;
        }
        else if ((node.left == null) && (node.right != null)) {
            numberLeafNodes(node.right);
        }
        else if ((node.left != null) && (node.right == null)) {
            numberLeafNodes(node.left);
        }
        else {
            numberLeafNodes(node.left);
            numberLeafNodes(node.right);
        }
    }
    // search the tree for a value
    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;
        while (!found && node != null) {
            if (node.value.equals(value)) {
                found = true;
            }
            else if (node.value.compareTo(value) < 0) {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }
        return found;
    }
}
