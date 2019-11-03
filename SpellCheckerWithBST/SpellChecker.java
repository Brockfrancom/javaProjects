/**
 * Assignment 9 for CS 1410
 * This program demonstrates using a binary search tree for spell checking
 *
 * @author Brock Francom
 */
import java.util.ArrayList;

public class SpellChecker {
    public static void main(String[] args) {

        testTree();
        read(); // This calls the driver program to read in the dictionary and letter and spell check.
    }

    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }


    // This is the driver program to spell check Letter against Dictionary.
    public static void read() {
        BinarySearchTree<String> myTree = new BinarySearchTree<>();
        ArrayList<String> words = new ArrayList<>(); //dictionary of words

        //adding words
        try {
            java.util.Scanner input = new java.util.Scanner(new java.io.File("Dictionary.txt"));
            while (input.hasNext()) {
                String line = input.next();
                words.add(line);
            }
            java.util.Collections.shuffle(words, new java.util.Random(System.currentTimeMillis()));
            for (int i = 0; i < words.size();i++) {
                myTree.insert(words.get(i));
            }

        }
        catch (java.io.IOException ex) {
            System.out.println("File exception " + ex.getMessage());
        }
        System.out.println(" ");
        reportTreeStats(myTree);

        //read in letter
        try {
            java.util.Scanner input = new java.util.Scanner(new java.io.File("Letter.txt"));
            while (input.hasNext()) {
                String line = input.next();
                line = line.toLowerCase().replace(".","").replace(",","");
                line = line.replace(":", "").replace("!","").replace("?","");
                line = line.replace("(","").replace(")","").replace(";", "");
                line = line.replace("\"","").replace("\"","").replace(" ","");

                // if the word is in the dictionary, do nothing, else print word
                if (myTree.search(line)) {
                }
                else {
                    System.out.println(line);
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception " + ex.getMessage());
        }
    }
}

