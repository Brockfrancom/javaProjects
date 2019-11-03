/**
 * Assignment 7 for CS 1410
 * This class controls positions in the game
 *
 * @author Brock Francom
 */
public class Position {
    public int x;
    public int y;


    public Position() {
    }


    public Position(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }
    public String toString() {
        int x = this.x;
        int y = this.y;

        return "(" + x + ", " + y +")";
    }


    public void setPosition(int posX, int posY) {
        x = posX;
        y = posY;
    }
}
