/**
 * Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allen
 * 3/25/2019
 *
 * Programming Exercise 5 - AutoComplete
 *
 * This is the driver function for Auto Complete. User inputs desired terms
 * and output is displayed. If there are not enough words that match the users
 * search term, "-----" is displayed for each slot that should be filled.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class AutoComplete {
    private static ArrayList<Term> array = new ArrayList<>(); // Holds all the Terms

    public static void main(String[] args) {
        // Read in the file, generate a Term object for each word, and add it to array.
        try {
            String filename = "src/SortedWords.txt";
            Scanner reader = new Scanner( new File( filename ) );
            reader.next(); // skip the first line
            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextInt();
                Term term = new Term(word, freq);
                array.add(term);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get user input, and make sure it is valid. Tests are not for all cases.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a prefix and the number of words you want. \nExample \"acc 9\" or \"acc&all 9\": ");
        String target = input.next().toLowerCase();
        String target1 = null;
        String target2 = null;
        if (target.contains("&")) {
            String[] targets = target.split("&");
            char[] chars1 = targets[0].toCharArray();
            for (int i=0; i <chars1.length; i++) {
                if (Character.isDigit(chars1[i])) {
                    System.out.println("Invalid input, please try again.");
                    System.exit(0);
                }
            }
            target1 = targets[0];
            char[] chars2 = targets[1].toCharArray();
            for (int i=0; i <chars2.length; i++) {
                if (Character.isDigit(chars2[i])) {
                    System.out.println("Invalid input, please try again.");
                    System.exit(0);
                }
            }
            target2 = targets[1];
        }
        else {
            char[] chars = target.toCharArray();
            for (int i=0; i<chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    System.out.println("Invalid input, please try again.");
                    System.exit(0);
                }
            }
            target1 = target;
        }
        int numWords = 0;
        try {
            numWords = input.nextInt();
        }
        catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            System.exit(0);
        }


        // Generate a heap for each target, if only one target, two is an empty heap.
        LeftistHeap one = autoComplete(target1);
        LeftistHeap two = autoComplete(target2);

        // Merge the heaps
        two.merge(one);

        // Print out the top numWords words that the user looked for.
        // If you would like to see the returned list with the priority,
        // change deleteMax() to deleteMaxWithInfo()
        System.out.printf("Substring: %s, count=%s\n\n", target, numWords);
        for (int i=0; i<numWords; i++) {
            System.out.println(two.deleteMax());
        }
    }

    // Returns a heap of words that match the target
    private static LeftistHeap autoComplete(String target) {
        LeftistHeap h = new LeftistHeap();
        if (target == null) {
            return h;
        }
        for (int i = 0; i < array.size(); i++) {
            String word = array.get(i).word;
            if (word.startsWith(target)){
                h.insert(array.get(i));
            }
        }
        return h;
    }
}
