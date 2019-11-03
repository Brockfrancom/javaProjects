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
import java.io.File;
import java.util.*;

import static java.lang.Math.min;

public class Graph {
    int numVertex;  // Number of vertices in the graph.
    GraphNode[] G;  // Adjacency list for graph.
    String graphName;  //The file from which the graph was created.

    public Graph() {
        this.numVertex = 0;
        this.graphName = "";
    }

    public Graph(int numVertex) {
        this.numVertex = numVertex;
        G = new GraphNode[numVertex];
        for (int i = 0; i < numVertex; i++) {
            G[i] = new GraphNode( i );
        }
    }

    public boolean addEdge(int source, int destination) {
        if (source < 0 || source >= numVertex) return false;
        if (destination < 0 || destination >= numVertex) return false;
        //add edge
        G[source].addEdge( source, destination );
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "The Graph " + graphName + " \n" );

        for (int i = 0; i < numVertex; i++) {
            sb.append( G[i].toString() );
        }
        return sb.toString();
    }

    public void makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner( new File( filename ) );
            System.out.println( "\n" + filename );
            numVertex = reader.nextInt();
            G = new GraphNode[numVertex];
            for (int i = 0; i < numVertex; i++) {
                G[i] = new GraphNode( i );
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                G[v1].addEdge( v1, v2 );
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllPred() {
        for (int i = 0; i < numVertex; i++) {
            G[i].p1.clear();
            G[i].p2.clear();
        }
    }

    /**
     * Find the path from v1 to v2 going through anc.
     *
     * @param v1:  first vertex
     * @param v2:  second vertex
     * @param anc: ancestor of v1 and v2
     * @return the path
     */
    public String reportPath(int v1, int v2, int anc, ArrayList one, ArrayList two) {
        StringBuilder sb = new StringBuilder();
        if (v1 == anc){

        }
        else{
            sb.append(" " + v1);
        }
        var space = false;
        var space2 = false;
        var index = one.indexOf(anc);
        for(var i=1; i<index; i++){
            sb.append(" " + one.get(i) + " ");
            space = true;
        }
        if (space) {
            sb.append(anc);
        }
        else {
            sb.append(" " + anc);
        }

        var index2 = two.indexOf(anc);
        for(var i=1; i<index2; i++){
            sb.append(" " + two.get(i) + " ");
            space2 = true;
        }
        if (v2 == anc){

        }
        else{
            if (space2){
                sb.append( v2 );
            }
            else{
                sb.append(" " + v2);
            }
        }
        return sb.toString();
    }

    /**
     * Computes the least common ancestor of v1 and v2, prints the length of the path, the ancestor, and the path itself.
     * @param v1: first vertex
     * @param v2: second vertex
     * @return returns the length of the shortest ancestral path.
     */
    public int lca(int v1, int v2) {
        // Compute lca
        PathInfo best = new PathInfo();

        var one = depthFirstSearch1(v1);
        var two = depthFirstSearch2(v2);

        for(var i= 0; i<numVertex; i++){
            if(G[i].p1.dist != 99 && G[i].p2.dist != 99) {
                if (best.dist > (G[i].p1.dist + G[i].p2.dist)) {
                    best.dist = (G[i].p1.dist + G[i].p2.dist);
                    best.pred = i;
                }
            }
        }
        System.out.println( graphName + " Best lca " + v1 + " " + v2 + " Distance: " + best.dist + " Ancestor " + best.pred + " Path:" + reportPath( v1, v2, best.pred, one, two ) );
        clearAllPred();
        return best.dist;
    }

    public int lca2(int v1, int v2) {
        // Compute lca
        PathInfo best = new PathInfo();

        depthFirstSearch1(v1);
        depthFirstSearch2(v2);

        for(var i= 0; i<numVertex; i++){
            if(G[i].p1.dist != 99 && G[i].p2.dist != 99) {
                if (best.dist > (G[i].p1.dist + G[i].p2.dist)) {
                    best.dist = (G[i].p1.dist + G[i].p2.dist);
                    best.pred = i;
                }
            }
        }
        clearAllPred();
        return best.dist;
    }

    // This computes the outcast of a group of vertices.
    public int outcast(int[] v) {
        int outcast = -1;
        int distances[] = new int[numVertex];
        for (var i=0; i<v.length;i++) {
            int d1 = 0;
            for(var j=0;j<v.length;j++) {
                d1 += lca2(v[i], v[j]);
            }
            distances[i] = d1;
        }
        var max = 0;
        for (var i=0;i<distances.length;i++){
            if(distances[i]> max){
                max = distances[i];
                outcast = i;
            }
        }
        System.out.println( "The outcast of " + Arrays.toString( v ) + " is " + v[outcast] + " with distance sum of " + max);
        return outcast;
    }

    // A function used by depthFirstSearch
    private ArrayList depthFirstSearch1(int v, boolean visited[], int depth, ArrayList path) {
        // Mark the current node as visited
        visited[v] = true;
        LinkedList<EdgeInfo> successors = G[v].succ;
        G[v].p1.set(v, depth);
        path.add(v);

        for (var i=0; i<successors.size(); i++) {
            if (!visited[successors.get(i).to]) {
                depthFirstSearch1(successors.get(i).to, visited, depth+1, path);
            }
        }
        return path;
    }

    // The function to do depthFirstSearch traversal.
    private ArrayList depthFirstSearch1(int v) {
        // Mark all the vertices as not visited(set to false by default in java)
        boolean visited[] = new boolean[numVertex];
        ArrayList path = new ArrayList();
        depthFirstSearch1(v, visited, 0, path);
        return path;
    }

    // A function used by depthFirstSearch
    private ArrayList depthFirstSearch2(int v, boolean visited[], int depth, ArrayList path) {
        // Mark the current node as visited
        visited[v] = true;
        LinkedList<EdgeInfo> successors = G[v].succ;
        G[v].p2.set(v, depth);
        path.add(v);

        for (var i=0; i<successors.size(); i++) {
            if (!visited[successors.get(i).to]) {
                depthFirstSearch2(successors.get(i).to, visited, depth+1, path);
                if(G[v].p1.dist != 99){
                    return path;
                }
            }
        }
        return path;
    }

    // The function to do depthFirstSearch traversal.
    private ArrayList depthFirstSearch2(int v) {
        // Mark all the vertices as not visited(set to false by default in java)
        boolean visited[] = new boolean[numVertex];
        ArrayList path = new ArrayList();
        depthFirstSearch2(v, visited, 0, path);
        return path;
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph();
        graph1.makeGraph( "digraph1.txt" );
        //System.out.println(graph1.toString());
        int[] set1 = {7, 10, 2};
        int[] set2 = {7, 17, 5, 11, 4, 23};
        int[] set3 = {10, 17, 13};

        graph1.lca( 3, 7 );
        graph1.lca( 5, 6 );
        graph1.lca( 9, 1 );

        graph1.outcast( set1 );

        Graph graph2 = new Graph();
        graph2.makeGraph( "digraph2.txt" );
        //System.out.println(graph2.toString());
        graph2.lca( 3, 24 );

        graph2.outcast( set3 );
        graph2.outcast( set2 );
        graph2.outcast( set1 );
    }
}