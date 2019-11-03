/**
 * @author Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allan
 * 4/8/2019
 * Program 6 - percolation
 *
 * This program is a demonstration percolation.
 */
public class Percolation {

    private static char[][] percolation;
    private static boolean[] isOpen;

    private static int SIZE;
    private static char [] sym = {'\u25A0','\u25A2','\u25A4'};  //white square, black square, white with lines

    private static int[][] set;
    private static boolean percolated = false;
    private static int sitesOpened = 0;

    public static void main(String[] args) {
        SIZE = 20;
        initialize(SIZE);
        disjSets(SIZE*SIZE);

        System.out.println("After 0 sites opened");
        status();
        while (!percolated){
            percolate();
            if (sitesOpened%50 == 0) {
                System.out.println("After " + sitesOpened + " sites opened");
                status();
            }
        }
        System.out.println("Percolated after " + sitesOpened + " sites opened.");
        status();
    }

    private static void percolate() {

        int x1 = (int)(Math.random()*20);
        int y1 = (int)(Math.random()*20);

        int x = getINT(x1,y1);

        percolation[x1][y1] = sym[0];
        if(isOpen[x]){
            return;
        }
        else {
            isOpen[x] = true;
            sitesOpened +=1;
        }
        try {
            //if (isOpen[x - 1] && (x/10 == ((x-1)/10))) {
            if (isOpen[x - 1] && isOpen[x-1]) {
                union(x, x - 1);
                isPercolated(x);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
        try {
            //if(isOpen[x+1] && (x/10 == ((x+1)/10))) {
            if(isOpen[x+1] && isOpen[x+1]) {
                union(x, x + 1);
                isPercolated(x);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
        try {
            //if (isOpen[x - 20] && (x/10 == ((x-20)/10)-1)) {
            if (isOpen[x - 20] && isOpen[x-20]) {
                union(x, x - 20);
                isPercolated(x);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
        try {
            //if (isOpen[x + 20] && (x/10 == ((x+20)/10)-1)) {
            if (isOpen[x + 20] && isOpen[x+20]) {
                union(x, x + 20);
                isPercolated(x);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
    }

    private static void isPercolated(int x) {
        int lowest = set[x][3];
        int highest = set[x][4];

        if((lowest == 0 && highest == 19) || (lowest == 19 && highest == 0)) {
            percolated = true;
        }
    }

    private static int getINT(int x, int y) {
        return SIZE*x+y;
    }

    private static void initialize(int size) {
        percolation = new char[size][size];
        SIZE = size;
        for(int i=0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                percolation[i][j] = sym[1];
            }
        }
        isOpen = new boolean[size*size];
    }

    private static void status() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(percolation[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    private static void disjSets(int numElements) {
        set = new int[numElements][4];
        for (var i = 0; i < set.length; i++) {
            int[] element = new int[]{i,i,-1,i%SIZE,i%SIZE};
            set[i] = element;
        }
    }

    private static void union(int root1, int root2) {
        int highest;
        int lowest;

        var one = find(root1);
        var two = find(root2);

        if (set[one][3] > set[two][3]) {
            lowest = set[one][3];
        }
        else {
            lowest = set[two][3];
        }
        if (set[one][4] < set[two][4]) {
            highest = set[one][4];
        }
        else {
            highest = set[two][4];
        }

        if( set[root2][2] < set[root1][2] ) { // root2 is deeper as height is stored as negative
            set[root1][1] = find(root2);      // Make root2 new root

            set[root1][3] = lowest;
            set[root1][4] = highest;
        }
        else {
            if( set[root1][2] == set[root2][2]) {
                set[root1][2]--;          // Update height if same
            }
            set[root2][1] = find(root1);  // Make root1 new root

            set[root2][3] = lowest;
            set[root2][4] = highest;
        }
    }

    private static int find(int x){
        if(set[x][2]<0){
            return set[x][1];
        }
        else {
            return set[x][1] = find(set[x][1]);
        }
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static long getUsedMemory() {
        return getMaxMemory() - getFreeMemory();
    }

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
}
