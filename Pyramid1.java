/**
 * @author Brock Francom
 * A02052161
 * Pyramid1
 * This constructs a pyramid with the inputed number of rows.
 */

import java.util.Scanner;

public class Pyramid1 {
    public static void main(String[] args) {
        //get user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int lines = input.nextInt();
        //convert input to string to get the correct formatting length
        String str = "" + lines;
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
            for (int k = i; k >= 1; k--) {
                System.out.printf(format, k);
            }
            // create the right of the pyramid
            for (int m = 2; m <= i; m++) {
                System.out.printf(format, m);
            }
            // next row
            System.out.println();
        }
    }
}