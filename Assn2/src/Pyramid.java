/*
Brock Francom
A02052161
CS-2410
Andrew Brim
1/29/2019

This class is used for creating a Pyramid
 */
public class Pyramid extends Shape {
    private double side;

    public Pyramid(double side) {
        setSide(side);
    }
    private double getSide() {
        return side;
    }
    private void setSide(double side) {
        this.side = side;
    }
    public double getArea() {
        System.out.println("\nPyramid:");
        return ((side * side)/2) * 4;
    }
}
