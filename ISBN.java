/**
 * @author Brock Francom
 * A02052161
 * ISBN
 * This computes the ISBN-10 number using the first 9 digits.
 */

import java.util.Scanner;

public class ISBN {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first 9 digits of an ISBN: ");
        int number = input.nextInt();
// find each digit
        int d1 = (number / (int)(Math.pow(10, 8)) % 10);
        int d2 = (number / (int)(Math.pow(10, 7)) % 10);
        int d3 = (number / (int)(Math.pow(10, 6)) % 10);
        int d4 = (number / (int)(Math.pow(10, 5)) % 10);
        int d5 = (number / (int)(Math.pow(10, 4)) % 10);
        int d6 = (number / (int)(Math.pow(10, 3)) % 10);
        int d7 = (number / (int)(Math.pow(10, 2)) % 10);
        int d8 = (number / (int)(Math.pow(10, 1)) % 10);
        int d9 = (number / (int)(Math.pow(10, 0)) % 10);
// calculate 10th digit
        int d10 = (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11;
// print message
        if (d10 == 10) {
            System.out.println("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + "X");
        }
        else {
            System.out.println("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10);
        }
    }
}
