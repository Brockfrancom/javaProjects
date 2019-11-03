/**
 * @author Brock Francom
 * A02052161
 * Quadratic
 * This computes the roots of a quadratic by entering the leading coefficients of each term.
 */

import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
// computes the discriminant
        double discriminant = (b * b) - (4 * a * c);
// computes the roots
        double r1 = (- b + Math.pow(discriminant, .5)) / (2 * a);
        double r2 = (- b - Math.pow(discriminant, .5)) / (2 * a);
// prints out a message
        if (discriminant > 0) {
            System.out.println("There are two roots for the quadratic equation with these coefficients.");
            System.out.println("r1 = " + r1);
            System.out.println("r2 = "+ r2);
        }
        else if (discriminant == 0) {
            System.out.println("There is one root for the quadratic equation with these coefficients.");
            System.out.println("r1 = " + r1);
        }
        else if (discriminant < 0) {
            System.out.println("There are no roots for the quadratic equation with these coefficients.");
        }
    }
}