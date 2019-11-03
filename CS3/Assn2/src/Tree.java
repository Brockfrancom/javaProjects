/**
* @author Brock Francom
* A02052161
* CS-2420
* Vicki Allan
* 2/2/2019
* Program 2 - Recursion
*
* This program is a demonstration of recursion and a binary tree.
*/

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer

// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 *
 * @author Me
 */

import java.util.Random;


import java.util.ArrayList;

import static java.lang.StrictMath.max;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     *
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super( message );
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";

    /**
     * Public method to create an empty tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Public method to create a tree from ArrayList of elements
     * @param arr list of items to add to the tree
     */
    public Tree(ArrayList<E> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            insert( arr.get( i ) );
        }
    }

    /**
     * Public method to create a tree from array  of elements
     * @param arr list of items to add to the tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            insert( arr[i] );
        }
    }

    /**
     * Public method to count leaf nodes
     * @return number of leaf nodes
     */
    public int countFringe() {
        return countFringe( root );
    }

    /**
     * Public method to find predecessor of the curr node.
     * Uses curr, a local variable set by contains.
     * @return String representation of predecessor
     */
    public String predecessor() {
        if (curr == null) curr = root;
        curr = predecessor( curr );
        if (curr == null) return "null";
        else return curr.toString();
    }

    /**
     * Insert into the tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert( x, root, null );
    }

    /**
     * Find an item in the tree.
     *
     * @param v the item to search for.
     * @return true if found.
     */
    public boolean contains(E v) {
        return contains( v, root );
    }

    /**
     * Find an item in the tree.
     *
     * @param v the item to search for.
     * @return the closest node if not found.
     */
    public BinaryNode<E> findClosest(E v) {
        return findClosest( root, v );

    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Make a deep copy of the tree.
     */
    public void clone(Tree<E> t) {
        this.root = cloneIt( t.root, null );
    }

    /**
     * Return a string displaying the tree contents as a tree.
     */
    public String prettyTree() {
        if (root == null)
            //System.out.println(treeName + " Empty tree\n");
            return (treeName + " Empty tree\n");
        else
            /*
            System.out.println(treeName + ENDLINE);
            prettyTree(root, "");
            */
            return treeName + ENDLINE + prettyTree(root, "" );
    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String traverse() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + traverse( root );
    }

    /**
     * Returns number of nodes in the tree
     *
     */
    public int countNodes() {
        return countNodes( root );
    }

    public int nodesInLevel(int level) {
        return nodesInLevel( root, level );
    }

    public String findKthInOrder(int k) {
        BinaryNode<E> kth = findKthInOrder( root, k );
        if (kth == null) return "NONE";
        else return kth.toString();
    }

    public boolean isIsomorphic(Tree<E> t2) {
        return isIsomorphic( this.root, t2.root );
    }

    public boolean isQuasiIsomorphic(Tree<E> t2) {
        return isQuasiIsomorphic( this.root, t2.root );
    }

    public int width() {
        return width(this.root);
    }















    /* PRIVATE */
    /**
     * @author Brock Francom
     *    This routine runs in O(n log n)
     * @param t the root of the tree
    */

    private int countFringe(BinaryNode<E> t) {
        if (t.left == null && t.right == null) {
            return 1;
        }
        var total = 0;
        if (t.right != null) {
            total += countFringe(t.right);
        }
        if (t.left != null) {
            total += countFringe(t.left);
        }
        return total;
    }

    /**
     * @author Brock Francom
     *    This routine runs in O(n log n)
     * @param t the root of the tree
     */
    private int countNodes(BinaryNode<E> t) {
        if (t == null) {
            return 0;
        }
        var total = 0;
        if (t.right != null) {
            total += countNodes(t.right);
        }
        if (t.left != null) {
            total += countNodes(t.left);
        }
        total += 1;
        return total;
    }

    /**
     * @author Brock Francom
     *    This routine runs in O(1)
     * @param t the root of the tree
     */
    private BinaryNode<E> predecessor(BinaryNode<E> t) {
        return t.parent;
    }

    /**
     * @author Brock Francom
     * Internal method to insert into a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree:
     * a=1, b=2, k=0
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> insert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>( x, null, null, parent, 0, 0, 0);
        int compareResult = x.compareTo( t.element );
        if (compareResult < 0) {
            t.leftSize += 1;
            t.left = insert( x, t.left, t );
            t.height += 1;
        }
        else {
            t.rightSize += 1;
            t.right = insert( x, t.right, t );
            t.height += 1;
        }
        return t;
    }

    /**
     * @author Brock Francom
     * Internal method to find an item in a subtree.
     *
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree:
     * a=1, b=2, k=0
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;
        int compareResult = x.compareTo( t.element );
        if (compareResult < 0)
            return contains( x, t.left );
        else if (compareResult > 0)
            return contains( x, t.right );
        else {
            curr = t;
            return true;    // Match
        }
    }

    /**
     * Internal method to find an item or the closest node in a subtree.
     *    This routine runs in O(??)
     * @param v is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private BinaryNode<E> findClosest(BinaryNode<E> t, E v) {

        return null;
    }

    /**
     * @author Brock Francom
     * Internal method to print a subtree in sorted order.
     *    This routine runs in O(n)
     * @param t the node that roots the subtree.
     * @param indent a string of blanks associated with the level of the node
     */
    private String prettyTree(BinaryNode<E> t, String indent) {
        if (t==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append( prettyTree(t.right,indent + "  ") );
        sb.append(indent + t.toString() + "\n");
        sb.append( prettyTree(t.left,indent + "  ") );
        return sb.toString();
    }

    /**
     * @author Brock Francom
     * Internal method to return a string of items in the tree in order
     *    This routine runs in O(n)
     * @param t the node that roots the subtree.
     */
    private String traverse(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append( traverse( t.left ) );
        sb.append( t.element.toString() + " " );
        sb.append( traverse( t.right ) );
        return sb.toString();
    }

    /**
     * @author Brock Francom
     * Internal method to count number of nodes at level
     *    This routine runs in O(log n)
     * @param t the node that roots the subtree.
     * @param level, root is level 0
     * @return number of nodes in subtree t at level
     */
    private int nodesInLevel(BinaryNode<E> t, int level) {
        if (level == 0) {
            return 1;
        }
        var total = 0;
        if (t.right != null && level != 0) {
            total += nodesInLevel(t.right, level - 1);
        }
        if (t.left != null && level != 0) {
            total += nodesInLevel(t.left, level - 1);
        }
        return total;
    }

    /**
     * @author Brock Francom
     * Internal method to find the kth value in the tree (by order
     *    This routine runs in O(log(n)) if it's balanced.
     * @param t the node that roots the subtree.
     * @param k, which item is wanted, by order
     * @return kth successor node
     */
    private BinaryNode<E> findKthInOrder(BinaryNode<E> t, int k) {
        if (t == null)
            return null;
        if ((t.leftSize + 1) == k) {
            return t;
        }
        if (t.leftSize + 1 < k) {
            return findKthInOrder(t.right, k-(t.leftSize+1));
        }
        else {
            return findKthInOrder(t.left, k);
        }
    }

    /**
     * @author Brock Francom
     * Internal method to determine if two trees are isomorphic
     *    This routine runs in O(log n)
     * @param t1 one tree
     * @param t2 second tree
     * @return true if t2 and t2 are isomorphic
     */
    private boolean isIsomorphic(BinaryNode<E> t1, BinaryNode<E> t2) {
        if (t1==null && t2==null) {
            return true;
        }
        if (t1.left == null && t2.left != null) {
            return false;
        }
        if (t1.right == null && t2.right != null) {
            return false;
        }
        return (isIsomorphic(t1.left,t2.left) && isIsomorphic(t1.right, t2.right));
    }

    /**
     * @author Brock Francom
     * Internal method to determine if two trees are quasi- isomorphic
     *    This routine runs in O(log n)
     * @param t1 one tree
     * @param t2 second tree
     * @return true if t2 and t2 are quasi isomorphic
     */
    private boolean isQuasiIsomorphic(BinaryNode<E> t1, BinaryNode<E> t2) {
        if (t1==null && t2==null) {
            return true;
        }
        if (t1.left == null && t1.right == null) {
            if (t2.left == null && t2.right == null) {
                return true;
            }
            else {
                return false;
            }
        }
        if (t1.right != null && t2.right != null) {
            return isQuasiIsomorphic(t1.right,t2.right);
        }
        if (t1.right != null && t2.left != null) {
            return isQuasiIsomorphic(t1.right,t2.left);
        }
        if (t1.left != null && t2.left != null) {
            return isQuasiIsomorphic(t1.left,t2.left);
        }
        if (t1.left != null && t2.right != null) {
            return isQuasiIsomorphic(t1.left,t2.right);
        }
        return false;
    }

    private BinaryNode<E> lca(E i, E i1) {
        if (this.root == null) {
            return null;
        }
        var node = this.root;
        return lca(node, i, i1);
    }
    /**
     * @author Brock Francom
     * Internal method to determine the lca
     *    This routine runs in O(log n)
     * @param i one int minimum
     * @param i1 second int maximum
     * @return lca node
     */
    private BinaryNode<E> lca(BinaryNode<E> node, E i, E i1) {
        if (node == null) {
            return null;
        }
        if (i.compareTo(node.element) == 0 || i1.compareTo(node.element) == 0) {
            return node;
        }
        BinaryNode left = lca(node.left, i, i1);
        BinaryNode right = lca(node.right, i, i1);
        if (left != null && right != null) {
            return node;
        }
        if (left == null && right == null) {
            return null;
        }
        if (left != null){
            return left;
        }
        else {
            return right;
        }
    }

    /**
     * @author Brock Francom
     * Internal method to determine the width of tree rooted at t
     *    This routine runs in O(n), because the height function returns a value; it is not recursive.
     * @param t one node
     * @return the width of the tree
     */
    private int width (BinaryNode<E> t) {
        if (t == null) {
            return 0;
        }
        int leftW = width(t.left);
        int rightW = width(t.right);
        int rootW  = height(t.left) + height(t.right) + 1;
        return max(rootW, max(leftW, rightW));
    }
    /**
     * @author Brock Francom
     * Internal method to determine the height of tree rooted at t
     *    This routine runs in O(1)
     * @param t one node
     * @return the height of the tree
     */
    private int height(BinaryNode<E> t) {
        if (t == null) {
            return 0;
        }
        return t.height;
    }

    /**
     * @author Brock Francom
     * Internal method to determine clone the tree
     *    This routine runs in O(n)
     * @param t tree
     * @param parent parent node of tree to be created
     * @return cloned tree
     */
    private BinaryNode<E> cloneIt(BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null) {
            return t;
        }
        BinaryNode temp = t;
        return temp;
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        int height;

        // Constructors
        BinaryNode(AnyType theElement) {
            this( theElement, null, null, null, 0, 0, 0);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt, int leftSize, int rightSize, int height) {
            element = theElement;
            left = lt;
            right = rt;
            this.leftSize = leftSize;
            this.rightSize = rightSize;
            parent = pt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node
        int leftSize;
        int rightSize;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            //sb.append( "Node:" );
            sb.append( element );
            if (parent == null) {
                sb.append( "< No parent >" );
            } else {
                sb.append( "<" );
                sb.append( parent.element );
                sb.append( ">");
            }
            sb.append( " [" );
            sb.append( leftSize );
            sb.append( "," );
            sb.append( rightSize );
            sb.append( "]" );
            return sb.toString();
        }
    }

    /**
     * The tree root.
     */
    private BinaryNode<E> root;
    private BinaryNode<E> curr;
    private String treeName;

    // Test program
    public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random( seed );  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        int val = 460;
        final int SIZE = 8;
        Integer[] v0 = {25, 10, 60, 55, 45};
        Integer[] v7 = {30, 15, 65, 75, 83};
        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 10, 75, 80, 20, 10, 5, 7, 61, 62, 63};
        ArrayList v2 = new ArrayList();
        ArrayList v3 = new ArrayList();
        ArrayList v4 = new ArrayList();
        ArrayList v5 = new ArrayList();
        ArrayList v6 = new ArrayList();


        for (int i = 0; i < SIZE; i++) {
            int t = generator.nextInt( 100 );
            //System.out.println( " t is " + t );
            v2.add( t );
            v3.add( t + generator.nextInt( 5 ) );
            v4.add( t + 18 );
            v5.add( 100 - t );
        }
        for (int i = 0; i < SIZE * SIZE; i++) {
            int t = generator.nextInt( 2000 );
            v6.add( t );
        }
        v6.add( val );
        Tree<Integer> tree0 = new Tree<Integer>( v0, "Tree0:" );
        Tree<Integer> tree1 = new Tree<Integer>( v1, "Tree1:" );
        Tree<Integer> tree2 = new Tree<Integer>( v2, "Tree2:" );
        Tree<Integer> tree3 = new Tree<Integer>( v3, "Tree3:" );
        Tree<Integer> tree4 = new Tree<Integer>( v4, "Tree4:" );
        Tree<Integer> tree5 = new Tree<Integer>( v5, "Tree5:" );
        Tree<Integer> tree6 = new Tree<Integer>( v6, "Tree6:" );
        Tree<Integer> tree7 = new Tree<Integer>( v7, "Tree7:" );
        Tree<Integer> tree8 = new Tree<Integer>( "Tree8:" );

        System.out.println( tree0.prettyTree() );
        System.out.println( tree0.traverse() );

        tree8.clone( tree0 );

        System.out.println( tree8.prettyTree( ) );
        tree8.makeEmpty();
        System.out.println( "Now Empty " + tree8.prettyTree(  ) );
        System.out.println("Not destroyed "+ tree0.prettyTree( ) );

        System.out.println( tree1.prettyTree(  ) );
        System.out.println( "Fringe count=" + tree1.countFringe() );

        System.out.println( tree6.prettyTree(  ) );
        System.out.println( "Size of Tree 6 " + tree6.countNodes() + ENDLINE );
        tree6.contains( val );  //Sets the current node inside the tree6 class.
        System.out.println( "In Tree6, starting at " + val + ENDLINE );
        System.out.println( tree6.prettyTree( ) );

        int predCount=5;  // how many predecessors do you want to see?
        for (int i = 0; i < predCount; i++) {
            System.out.println( "The next predecessor is " + tree6.predecessor() );
        }

        System.out.println( tree4.prettyTree(  ) );
        System.out.println( "Number nodes at level " + 0 + " is " + tree4.nodesInLevel( 0 ) );
        int myLevel = 3;
        System.out.println( "Number nodes at level " + myLevel + " is " + tree4.nodesInLevel( myLevel ) );
        myLevel = 4;
        System.out.println( "Number nodes at level " + myLevel + " is " + tree4.nodesInLevel( myLevel ) );

        System.out.println( tree1.prettyTree(  ) );
        int k = 1;
        System.out.println(tree1.traverse());
        System.out.println( "In tree1, the " + k + "st smallest value is  " + tree1.findKthInOrder( k ) );
        k = 7;
        System.out.println( "In tree1, the " + k + "th smallest value is  " + tree1.findKthInOrder( k ) );
        k = 12;
        System.out.println( "In tree1, the " + k + "th smallest value is  " + tree1.findKthInOrder( k ) );

        System.out.println("The width of  tree1 is " +tree1.width() +ENDLINE);
        System.out.println( tree2.prettyTree( ) );
        System.out.println("The width of  tree2 is " +tree2.width() +ENDLINE);


        System.out.println( tree3.prettyTree( ) );
        System.out.println( tree4.prettyTree( ) );
        System.out.println( tree5.prettyTree( ) );

        if (tree2.isIsomorphic( tree3 )) System.out.println( "Trees 2 and 3 are Isomorphic" );
        if (tree2.isIsomorphic( tree4 )) System.out.println( "Trees 2 and 4 are Isomorphic" );
        if (tree3.isIsomorphic( tree4 )) System.out.println( "Trees 3 and 4 are Isomorphic" );
        if (tree0.isIsomorphic( tree1 )) System.out.println( "Trees 2 and 1 Are Isomorphic" );
        if (tree2.isQuasiIsomorphic( tree3 )) System.out.println( "Trees 2 and 3 Are Quasi-Isomorphic" );
        if (tree2.isQuasiIsomorphic( tree5 )) System.out.println( "Trees 2 and 5 Are Quasi-Isomorphic" );
        if (tree2.isQuasiIsomorphic( tree5 )) System.out.println( "Trees 2 and 4 Are Quasi-Isomorphic" );
        if (tree0.isQuasiIsomorphic( tree7 )) System.out.println( "Trees 0 and 7 Are Quasi-Isomorphic" );

        System.out.println( tree1.prettyTree( ) );
        System.out.println( "Least Common Ancestor of (56,61) " + tree1.lca( 56, 61 ) + ENDLINE );
        System.out.println( "Least Common Ancestor (58,55) " + tree1.lca( 58, 55 ) + ENDLINE );
    }
}
