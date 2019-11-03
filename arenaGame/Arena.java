/**
 * Assignment 7 for CS 1410
 * This program creates an arena and then moves the player and prints messages.
 *
 * @author Brock Francom
 */
import java.util.ArrayList;

public class Arena {
    private ArrayList<Entity[]> list;
    public Hero hero;
    int heroCount = 0;


    public Arena(int x, int y) {
        list = new ArrayList<>(x);
        for (int m = 0; m < x; m++) {
            list.add(new Entity[y]);
        }
    }

    public boolean add(Entity obj) {
        int x = obj.getPosition().x;
        int y = obj.getPosition().y;

        if ((obj instanceof Hero) && (heroCount == 0)) { //make sure there isn't a hero
            if (list.get(x)[y] == null) { //make sure there isn't something there
                list.get(x)[y] = obj; // put the object there
                this.hero = (Hero) obj;
                heroCount += 1;
                System.out.println("Successfully added '" + hero.toString() + "' to the game arena.");
                return true;
            } else
                return false;
        }
        else if ((obj instanceof Hero) && (heroCount != 0)) {
            System.out.println("Could not add '" + obj.toString() + "' because there is already a hero in the arena.");
            return false;
        }
        else {
            if (list.get(x)[y] == null) {//make sure there isn't something there
                list.get(x)[y] = obj;// put the object there
                System.out.println("Successfully added '" + obj.toString() + "' to the game arena.");
                return true;
            }
            else {
                System.out.println("Could not add '" + obj.toString() + "' because another entity is already there.");
                return false;
            }
        }

    }


    public int getEntityCount() {
        int count = 0;
        for (int i = 0; i < (list.size()); i++) {
            for (int m = 0; m < (list.get(0).length); m++) {
                if (list.get(i)[m] instanceof Entity){
                    count += 1;
                }
            }
        }
        return count;
    }

    public Object getDragonCount() {
        int count = 0;
        for (int i = 0; i < (list.size()); i++) {
            for (int m = 0; m < (list.get(0).length); m++ )
            if (list.get(i)[m] instanceof Dragon){
                count += 1;
            }
        }
        return count;
    }

    public Object getTreasureCount(Treasure type) {
        int coinCount = 0;
        int foodCount = 0;
        int ragsCount = 0;
        int statueCount = 0;
        int woodCount = 0;

        for (int i = 0; i < (list.size()); i++) {
            for (int m = 0; m < (list.get(0).length); m++) {
                if (list.get(i)[m] instanceof Crate) {
                    Treasure t = ((Crate) list.get(i)[m]).getTreasure();
                    if (t == Treasure.Coins) {
                        coinCount += 1;
                    } else if (t == Treasure.Food) {
                        foodCount += 1;
                    } else if (t == Treasure.Rags) {
                        ragsCount += 1;
                    } else if (t == Treasure.Statue) {
                        statueCount += 1;
                    } else if (t == Treasure.Wood) {
                        woodCount += 1;
                    }
                }
            }
        }
        if (type == Treasure.Coins)
            return coinCount;
        else if (type == Treasure.Food)
            return foodCount;
        else if (type == Treasure.Rags)
            return ragsCount;
        else if (type == Treasure.Statue)
            return statueCount;
        else if (type == Treasure.Wood)
            return woodCount;
        else
            return null;
    }

    public void moveHero(int x, int y) {

        if (list.get(x)[y] instanceof Crate) {

            Treasure t = ((Crate) list.get(x)[y]).getTreasure();
            System.out.println(hero.name + " crushed the crate into bits and found " + t + ".");
            if (t == Treasure.Coins) {
                hero.Inventory.add(Treasure.Coins);
            } else if (t == Treasure.Food) {
                hero.Inventory.add(Treasure.Food);
            } else if (t == Treasure.Rags) {
                hero.Inventory.add(Treasure.Rags);
            } else if (t == Treasure.Statue) {
                hero.Inventory.add(Treasure.Statue);
            } else if (t == Treasure.Wood) {
                hero.Inventory.add(Treasure.Wood);
            }
        list.get(x)[y] = null; // destroy the crate
        }
        else if (list.get(x)[y] instanceof Dragon) {
            String d = ((Dragon) list.get(x)[y]).getColor();
            if (d == "Golden"){
                hero.Inventory.add(Treasure.Coins);
                list.get(x)[y] = null; // kill the dragon
                System.out.println(hero.name + " bravely defeated the " + d + " dragon and came away with gold coins as a prize.");
            }
            else {
                list.get(x)[y] = null; // kill the dragon
                System.out.println(hero.name + " bravely defeated the " + d + " dragon.");
            }

        }
        hero.setPosition(x,y);
        System.out.println(hero.name + " moved to (" + x + ", " + y + ")");

    }

    public void reportHero() {
        System.out.println("--- Hero report for " + hero.name + " ---\n" +
                "Position: " + "("+ hero.pos.x + ", " + hero.pos.y + ")" +"\n" +
                "Treasures:\n");
        ArrayList t = hero.getTreasures();
        for (int i = 0; i <= (hero.getTreasures().size() + 1); i++) {
            System.out.println(t.remove(0));
        }
        System.out.println();
    }

    public ArrayList<Entity[]> getGrid() {
        return this.list;
    }

    public Hero getHero() {
        return hero;
    }
}
