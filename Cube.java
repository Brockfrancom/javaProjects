/*
Brock Francom
A02052161
CS-2410
Andrew Brim
1/29/2019

This class is used for creating a Cube
 */
public class Cube extends Shape {
    private double side;

    public Cube(double side) {
        setSide(side);
    }
    private double getSide() {
        return side;
    }
    private void setSide(double side) {
        this.side = side;
    }
    public double getArea() {
        System.out.println("\nCube:");
        return (side * side) * 6;
    }
}
