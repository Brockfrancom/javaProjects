/**
 * @author Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allan
 * 4/8/2019
 * Program 6 - Percolation
 *
 * This program performs union find with path compression
 */
public class UnionFind {

    private static int[][] s;

    public static void main(String[] args) {

        disjSets(30); // create a 30 element set

        //print out starting
        System.out.println("ID ParentID  Size");
        for (var i = 0; i <30;i++){
            System.out.print(i+ "   ");
            System.out.print(((s[i]))[0]);
            System.out.print("          ");
            if(i == s[i][0]) {
                System.out.println(((s[i]))[1]);
            }
            else{
                System.out.println();
            }
        }

        //perform unions
        union(1,2);
        union(4,5);
        union(5,2);
        union(24,1);
        union(28,27);
        union(28,25);
        union(28,5);

        //print out summary
        System.out.println("ID ParentID  Size");
        for (var i = 0; i <30;i++){
            System.out.print(i+ "   ");
            System.out.print(((s[i]))[0]);
            System.out.print("          ");
            if(i == s[i][0]) {
                System.out.println(((s[i]))[1]);
            }
            else{
                System.out.println();
            }
        }
        System.out.println("Node 5 has parent: " + find(5));
    }

    private static void disjSets(int numElements) {
        s = new int[numElements][4];
        for (var i = 0; i < s.length;i++) {
            int[] element = new int[]{i,0, -1, -1};
            s[i] = element;
        }
    }

    private static void union(int root1, int root2) {
        if( s[root2][1] < s[root1][1] ) { // root2 is deeper as height is stored as negative
            s[root1][0] = find(root2);    // Make root2 new root
        }
        else {
            if( s[root1][1] == s[root2][1] )
                s[root1][1]--;          // Update height if same
            s[root2][0] = find(root1);  // Make root1 new root
        }
    }

    private static int find(int x){
        if(s[x][1]<0){
            return s[x][0];
        }
        else {
            return s[x][0] = find(s[x][0]);
        }
    }
}
