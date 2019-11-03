/**
 * Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allen
 * 3/25/2019
 *
 * Programming Exercise 5 - AutoComplete
 *
 * Test cases to prove the heap class works.
 */

import java.util.Scanner;
import java.io.File;

public class ReadCode {
    public static void main(String[] args) {
        try {
            //Inserting into first queue
            LeftistHeap heap1 = new LeftistHeap();
            String filename1 = "src/small1.txt";
            Scanner reader1 = new Scanner(new File(filename1));
            while ((reader1.hasNext())) {
                String word = reader1.next();
                long freq = reader1.nextInt();
                Term term = new Term(word, freq);
                heap1.insert(term);
             }
            reader1.close();
            System.out.println("Heap 1:");
            heap1.inorder(); // print queue

            //Inserting into second queue
            LeftistHeap heap2 = new LeftistHeap();
            String filename2 = "src/small2.txt";
            Scanner reader2 = new Scanner(new File(filename2));
            while ((reader2.hasNext())) {
                String word = reader2.next();
                long freq = reader2.nextInt();
                Term term = new Term(word, freq);
                heap2.insert(term);
            }
            reader2.close();
            System.out.println("Heap 2:");
            heap2.inorder(); //print queue

            // Merge the heaps
            heap2.merge(heap1);

            //Print out heaps
            System.out.println("After merging heap 1 and 2:");
            heap2.inorder();

            //Delete the max and print tree
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo() + "\n");
            heap2.inorder();

            // Delete multiple maxes and print tree
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo());
            System.out.println("Max Deleted: " + heap2.deleteMaxWithInfo() + "\n");
            heap2.inorder();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}