import java.util.ArrayList;

/**
 * Assignment 4 for CS 1410
 * This program evaluates the bubble and selection sorts versus each other.
 *
 * @author James Dean Mathias
 * @author Brock Francom
 *
 */
// This is the main program, it calls the sorting methods, and then accesses the instances to report the stats.
public class SortingDriver {
    public static void main(String[] args) {
        System.out.println("--- Timing Results ---\n");
        int howMany = 1000;
        int i = 0;
        while (i < 10) {
            int[] list1 = generateNumbers(howMany);
            SortingStats ss1 = bubbleSort(list1);
            long time1 = ss1.getTimeInMs();
            System.out.println("Number of items      : " + howMany);
            System.out.println("Bubble sort time     : " + time1 + " ms"); // ^^These lines are for the bubble sort.^^

            int[] list2 = generateNumbers(howMany);
            SortingStats ss2 = selectionSort(list2);
            long time2 = ss2.getTimeInMs();
            System.out.println("Selection sort time  : " + time2 + " ms"); //^^These lines are for the selection sort.^^
            System.out.println();
            howMany += 1000; // Increase list size.
            i++;
        }

    }
// creates a list of size howMany.
    public static int[] generateNumbers(int howMany) {
        int[] intArray = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            intArray[i] = (int)(Math.random() * 10000);
        }
        return intArray;
    }
// Method for bubble sort
    public static SortingStats bubbleSort(int[] data) {
        SortingStats ss1 = new SortingStats();
        long time1 = System.currentTimeMillis(); // to calculate the time
        boolean didSwap = false;
        int howFar = data.length - 1;
        do {
            didSwap = false;
            for (int item = 0; item < howFar; item++) {
                if (data[item] > data[item + 1]) {
                    int temp = data[item];
                    data[item] = data[item + 1];
                    data[item + 1] = temp;
                    didSwap = true;
                    ss1.incrementSwapCount();
                }
                ss1.incrementCompareCount();
            }
            howFar--;
        } while (didSwap);
        long time2 = System.currentTimeMillis(); //to calculate the time
        long diff = time2 - time1; //to calculate the time
        ss1.setTime(diff); //to calculate the time
        return ss1;
    }
// Method to perform selection sort
    public static SortingStats selectionSort(int[] data) {
        SortingStats ss2 = new SortingStats();
        long time1 = System.currentTimeMillis(); //to calculate the time
        for (int start = 0; start < (data.length - 1); start++) {
            int minPos = start;
            for (int scan = start; scan < data.length; scan++) {
                if (data[scan] < data[minPos]) {
                    minPos = scan;
                }
                ss2.incrementCompareCount();
            }
            int temp = data[start];
            data[start] = data[minPos];
            data[minPos] = temp;
            ss2.incrementSwapCount();
        }
        long time2 = System.currentTimeMillis(); //to calculate the time
        long diff = time2 - time1; //to calculate the time
        ss2.setTime(diff); //to calculate the time
        return ss2;
    }
}
