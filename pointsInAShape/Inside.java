/**
 * Assignment 3 for CS 1410
 * This program determines if points are contained within circles or rectangles.
 *
 * @author James Dean Mathias
 * @author Brock Francom, A02052161
 *
 * NOTE: The assignment called for reportPoint, reportCircle, and reportRectangle methods.
 * These methods are present but never called.
 *
 * Thanks!!
 */
public class Inside {
    /**
     * This is the primary driver code to test the "inside" capabilities of the
     * various functions.
     */
    public static void main(String[] args) {
        double[] ptX = { 1, 2, 3, 4 };
        double[] ptY = { 1, 2, 3, 4 };
        double[] circleX = { 0, 5 };
        double[] circleY = { 0, 5 };
        double[] circleRadius = { 3, 3 };
        double[] rectLeft = { -2.5, -2.5 };
        double[] rectTop = { 2.5, 5.0 };
        double[] rectWidth = { 6.0, 5.0 };
        double[] rectHeight = { 5.0, 2.5 };

        //This is the start of the report
        System.out.println("--- Report of Points and Circles --- \n");
        // This calls the function and prints the results
        int i = 0;
        while (i < 4) {
            if (isPointInsideCircle(ptX[i], ptY[i], circleX[0], circleY[0], circleRadius[0])) {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is inside Circle(" + circleX[0] + "," + circleY[0] + ") Radius: " + circleRadius[0]);
            }
            else {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is outside Circle(" + circleX[0] + "," + circleY[0] + ") Radius: " + circleRadius[0]);
            }
            i++;
        }
        // This calls the function and prints the results
        i = 0;
        while (i < 4) {
            if (isPointInsideCircle(ptX[i], ptY[i], circleX[1], circleY[1], circleRadius[1])) {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is inside Circle(" + circleX[1] + "," + circleY[1] + ") Radius: " + circleRadius[1]);
            }
            else {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is outside Circle(" + circleX[1] + "," + circleY[1] + ") Radius: " + circleRadius[1]);
            }
            i++;
        }
        //This is the second half of the report
        System.out.println("\n--- Report of Points and Rectangles --- \n");
        // This calls the function and prints the results
        i = 0;
        while (i < 4) {
            if (isPointInsideRectangle(ptX[i], ptY[i], rectLeft[0], rectTop[0], rectWidth[0], rectHeight[0])) {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is inside Rectangle(" + rectLeft[0] + "," + rectTop[0] + "," + (rectLeft[0] + rectWidth[0]) + "," + (rectTop[0] - rectHeight[0]) + ")");
            }
            else {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is outside Rectangle(" + rectLeft[0] + "," + rectTop[0] + "," + (rectLeft[0] + rectWidth[0]) + "," + (rectTop[0] - rectHeight[0]) + ")");
            }
            i++;
        }
        // This calls the function and prints the results
        i = 0;
        while (i < 4) {
            if (isPointInsideRectangle(ptX[i], ptY[i], rectLeft[1], rectTop[1], rectWidth[1], rectHeight[1])) {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is inside Rectangle(" + rectLeft[1] + "," + rectTop[1] + "," + (rectLeft[1] + rectWidth[1]) + "," + (rectTop[1] - rectHeight[1]) + ")");
            }
            else {
                System.out.println("Point(" + ptX[i] + "," + ptY[i] + ") is outside Rectangle(" + rectLeft[1] + "," + rectTop[1] + "," + (rectLeft[1] + rectWidth[1]) + "," + (rectTop[1] - rectHeight[1]) + ")");
            }
            i++;
        }
    }
    // this is a method to report a point, it is not used in the program
    static void reportPoint(double x, double y) {
        System.out.print("Point(" + x + ", " + y + ")");
    }
    // this is a method to report a circle, it is not used in the program
    static void reportCircle(double x, double y, double r) {
        System.out.print("Circle(" + x + ", " + y + ")Radius: " + r);
    }
    // this is a method to report a rectangle, it is not used in the program
    static void reportRectangle(double left, double top, double width, double height) {
    System.out.print("Rectangle(" + left + "," + (left + width) + "," + top + "," + (top - height) + ")");
    }
    // this method finds whether a point is in a circle or not. On the circle counts as inside the circle
    static boolean isPointInsideCircle(double ptX, double ptY, double circleX, double circleY, double circleRadius) {
        double a = ptX - circleX;
        double b = ptY - circleY;
        double c = Math.pow((Math.pow(a,2) + Math.pow(b,2)), (.5));
        if (c <= circleRadius) {
            return true;
        }
        else {
            return false;
        }

    }
    // this method finds whether a point is in a rectangle or not. On the rectangle counts as inside the rectangle.
    static boolean isPointInsideRectangle(double ptX, double ptY, double rLeft, double rTop, double rWidth, double rHeight) {
        if ((ptY >= (rTop - rHeight)) && (ptY <= rTop) && (ptX >= rLeft) && (ptX <= (rLeft + rWidth))) {
            return true;
        }
        else {
            return false;
        }
    }

}
