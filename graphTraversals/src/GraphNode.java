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
import java.util.LinkedList;

public class GraphNode {
    public int nodeID;
    PathInfo p1;
    PathInfo p2;
    public LinkedList<EdgeInfo> succ;

    public GraphNode( ){
        this.nodeID = 0;
        this.p2 = new PathInfo();
        this.p1 = new PathInfo();
        this.succ = new LinkedList<EdgeInfo>();
    }

    public GraphNode(int nodeID){
        this.nodeID = nodeID;
        this.p2 = new PathInfo();
        this.p1 = new PathInfo();
        this.succ = new LinkedList<EdgeInfo>();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(nodeID);
        sb.append(p1.toString());
        sb.append(p2.toString());
        for (EdgeInfo e:succ){
            sb.append(e.toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    public void addEdge(int v1, int v2){
        succ.addFirst(new EdgeInfo(v1,v2));
    }
}
