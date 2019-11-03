/**
 * Assignment 7 for CS 1410
 * This class controls entity objects
 *
 * @author Brock Francom
 */
public class Entity {
    public int count = 0;
    public Position pos = new Position();

    public Entity() {
    }

    public Entity(int posX, int posY) {
        pos = new Position(posX,posY);
    }

    public void addEntity() {
        this.count += 1;
    }

    public void loseEntity() {
        this.count -= 1;
    }

    public int getEntityCount() {
        return this.count;
    }

    public Position getPosition() {
        return pos;
    }


    public void setPosition(int posX, int posY) {
        pos.setPosition(posX, posY);

        //pos = new Position(posX,posY);
    }
}
