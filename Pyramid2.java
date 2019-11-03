/**
 * @author Brock Francom
 * A02052161
 * Pyramid2
 * This constructs a pyramid with the inputed number of rows.
 */

import java.util.Scanner;

public class Pyramid2 {
    public static void main(String[] args) {
        //get user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int lines = input.nextInt();

        //find the biggest number created and convert to string to format.
        int x = (int) Math.pow(2, (lines -1));
        String str = "" + x;
        int len = str.length();
        String format = "%" + (len + 1) + "s";

        // create the pyramid output
        for (int i = 1; i <= lines; i++) {
            int n = lines;
            // create the blank spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.printf(format, " ");
            }

            // create the left of the pyramid
            for (int m = 0; m < i; m++) {
                System.out.printf(format, (int)Math.pow(2, m));
            }

            // create the right of the pyramid
            for (int k = i - 2; k >= 0; k--) {
                System.out.printf(format, (int)Math.pow(2, k));
            }
            // next row
            System.out.println();
        }
    }
}