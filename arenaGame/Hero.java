/**
 * Assignment 7 for CS 1410
 * This class creates hero objects
 *
 * @author Brock Francom
 */
import java.util.ArrayList;

public class Hero extends Entity {
    public String name;
    public ArrayList<Treasure> Inventory = new ArrayList<>();


    public Hero(String name, int x, int y) {
        this.name = name;
        super.pos = new Position(x,y);
    }

    public Position getPosition() {
        return super.getPosition();

    }

    public String getName() {
        return name;

    }

    public ArrayList<Treasure> getTreasures() {

        return Inventory;

    }
    public String toString(){
        int two = super.pos.x;
        int three = super.pos.y;
        String string = name + " standing at (" + two +", " + three + ")";
        return string;
    }

    public void attack(Entity i) {
        if (i instanceof Dragon && ((Dragon) i).getColor() == "Golden") {
            Inventory.add(Treasure.Coins);
        }
        else if (i instanceof Crate) {
            Inventory.add(((Crate) i).getTreasure());
        }
    }
}
