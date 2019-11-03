import java.util.ArrayList;

/**
 * @author Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allan
 * 4/22/2019
 * Program 7 - WordNet
 *
 * This program creates a wordnet
 */
public class PathInfo {
    int dist;
    int pred;
    ArrayList path = new ArrayList();

    PathInfo() {
        clear();
    }

    public void set(int node, int dist) {
        this.pred = node;
        this.dist = dist;
    }

    public void clear() {
        this.pred = -1;
        this.dist = 99;
    }

    public String toString() {
        return "[" + dist + " Pred:" + pred + "] ";
    }
}
