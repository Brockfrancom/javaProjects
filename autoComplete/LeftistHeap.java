/**
 * Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allen
 * 3/25/2019
 *
 * Programming Exercise 5 - AutoComplete
 *
 * Code to create a leftist heap.
 */

public class LeftistHeap {

    private LeftistHeapNode root;

    // Inner class for the nodes
    class LeftistHeapNode {
        long frequency;
        Term element;
        LeftistHeapNode left;
        LeftistHeapNode right;

        public LeftistHeapNode(Term term) {
            this(term, null, null);
        }
        public LeftistHeapNode(Term term, LeftistHeapNode left, LeftistHeapNode right) {
            this.element = term;
            this.left = left;
            this.right = right;
            this.frequency = term.freq;
        }
    }

    // Constructor
    public LeftistHeap() {
        root = null;
    }

    // Insert function
    public void insert(Term term) {
        root = merge(new LeftistHeapNode(term), root);
    }

    // Merge function
    public void merge(LeftistHeap two) {
        if (this == two)
            return;
        root = merge(root, two.root);
        two.root = null; // delete the heap after it has been merged
    }

    // Merge part 2
    private LeftistHeapNode merge(LeftistHeapNode one, LeftistHeapNode two) {
        if (one == null)
            return two;
        if (two == null)
            return one;
        // If one has a smaller frequency than 2, swap the heaps.
        if (one.frequency < two.frequency) {
            LeftistHeapNode temp = one;
            one = two;
            two = temp;
        }

        one.right = merge(one.right, two); // merge

        // Adjust the kids
        if(one.left == null) {
            one.left = one.right;
            one.right = null;
        }
        else {
            if(one.left.frequency < one.right.frequency) {
                LeftistHeapNode temp = one.left;
                one.left = one.right;
                one.right = temp;
            }
        }
        return one; // return the new heap
    }

    // This was mainly used for debugging, to see the heap structure.
    public void inorder() {
        inorder(root, "");
        System.out.println();
    }

    // This was mainly used for debugging, to see the heap structure.
    private void inorder(LeftistHeapNode r, String indent) {
        if (r != null) {
            inorder(r.left, indent+"   ");
            System.out.printf("%s %s\n", indent, r.element);
            inorder(r.right, indent+"   ");
        }
    }

    // This returns the word with the biggest frequency value. (root)
    public String deleteMax() {
        try {
            String max = this.root.element.word;
            this.root = merge(this.root.right, this.root.left); //Set the root to be the merged children.
            return max;
        }
        catch (Exception e) { // Catches the case when the max is deleted from an empty tree.
            Term t = new Term("-----", 1);
            return t.word; // returns "-----"
        }
    }
    // This returns the word with the biggest frequency value. (root) With info
    // Used to make sure the order is right on deletion.
    public Term deleteMaxWithInfo() {
        try {
            Term max = this.root.element;
            this.root = merge(this.root.right, this.root.left); //Set the root to be the merged children.
            return max;
        }
        catch (Exception e) { // Catches the case when the max is deleted from an empty tree.
            Term t = new Term("-----", 1);
            return t; // returns "-----"
        }
    }

}
