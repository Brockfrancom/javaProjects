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
public class EdgeInfo {
    int from;        // source of edge
    int to;          // destination of edge

    public EdgeInfo(int from, int to){
        this.from = from;
        this.to = to;
    }

    public String toString(){
        return from + "->" + to + " " ;
    }
}
