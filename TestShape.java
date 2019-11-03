import java.util.ArrayList;
/*
Brock Francom
A02052161
CS-2410
Andrew Brim
1/29/2019

This class is used to test the shape class by creating a list of shape objects
and calling the overridden getArea method on each object.
 */
public class TestShape {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Square(5));
        shapes.add(new Square(10));
        shapes.add(new Triangle(2));
        shapes.add(new Triangle(6.2));
        shapes.add(new Pyramid(1));
        shapes.add(new Pyramid(40));
        shapes.add(new Cube(30));
        shapes.add(new Cube(55));

        for (Shape shape:shapes) {
            System.out.println(shape.getArea());
        }
    }
}
