/**
 * Assignment 7 for CS 1410
 * This class creates crate objects
 *
 * @author Brock Francom
 */
public class Crate extends Entity {
    public Treasure treasure;
    public Position pos;


    public Crate(Treasure treasure, int x, int y) {
        this.treasure = treasure;
        super.pos = new Position(x,y);

    }

    public Treasure getTreasure() {
        return treasure;

    }

    public Position getPosition() {
        return super.getPosition();

    }
    public String toString(){

        String one = " ";
        if (treasure == Treasure.Coins)
            one = "Coins";
        else if (treasure == Treasure.Food)
            one = "Food";
        else if (treasure == Treasure.Rags)
            one = "Rags";
        else if (treasure == Treasure.Statue)
            one = "Statue";
        else if (treasure == Treasure.Wood)
            one = "Wood";

        int two = super.pos.x;
        int three = super.pos.y;
        String string = "Crate with " + one + " at (" + two + ", " + three + ")";
        return string;
    }

}
