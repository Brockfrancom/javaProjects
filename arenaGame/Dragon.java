/**
 * Assignment 7 for CS 1410
 * This class creates dragon objects
 *
 * @author Brock Francom
 */
public class Dragon extends Entity {
    public String color;
    public Position pos;



    public Dragon(String color, int posX, int posY) {
        this.color = color;
        super.pos = new Position(posX,posY);
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public String getColor() {
        return this.color;
    }
    public String toString(){
        int two = super.pos.x;
        int three = super.pos.y;
        String string = "The " + color +" dragon breathing fire at (" + two + ", " + three + ")";
        return string;
    }

}
