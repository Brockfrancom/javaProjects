/**
 * Assignment 3 for CS 2420
 * This is the class that uses both an AVL tree and a queue to solve
 * soduku, and shows the differences between the two data structures.
 * @author Brock Francom, A02052161
 *
 * NOTE: The show me method is built into each of the respective methods,
 * and to see the different boards the showMe lines need to be un-commented.
 * I just didn't like how long it made the output so I commented it out.
 *
 * Thanks!
 */
public class SliderGame {

    // This is the main method that calls both methods to solve a board.
    public static void main(String[] args) {
        int [] game0 = { 1, 2, 3, 7, 4, 0, 6, 5, 8 };
        Board b = new Board();
        b.makeBoard(game0);
        bruteForceSolve(b, "game0");
        b.makeBoard(game0);
        aStarSolve(b, "game0");

        int []game1 = { 1, 3, 2, 4, 5, 6, 8, 7, 0 };
        b.makeBoard(game1);
        bruteForceSolve(b, "game1");
        b.makeBoard(game1);
        aStarSolve(b, "game1");

        int []game2 = { 1, 3, 8, 6, 2, 0, 5, 4, 7 };
        b.makeBoard(game2);
        bruteForceSolve(b, "game2");
        b.makeBoard(game2);
        aStarSolve(b, "game2");

        int []game3 = { 4, 0, 1, 3, 5, 2, 6, 8, 7 };
        b.makeBoard(game3);
        bruteForceSolve(b, "game3");
        b.makeBoard(game3);
        aStarSolve(b, "game3");

        int []game4 = { 7, 6, 4, 0, 8, 1, 2, 3, 5 };  // Warning slow to solve
        b.makeBoard(game4);
        bruteForceSolve(b, "game4");
        b.makeBoard(game4);
        aStarSolve(b, "game4");

        int []game5 = { 1, 2, 3, 4, 5, 6, 8, 7, 0 };   // Warning unsolvable
        b.makeBoard(game5);
        bruteForceSolve(b, "game5");
        b.makeBoard(game5);
        aStarSolve(b, "game5");
    }

    // solve the board b using the queue, brute force method.
    public static void bruteForceSolve(Board b, String name) {
        System.out.println("Brute Force:");
        GameWithQueue g = new GameWithQueue();
        var moves = g.playGiven(name, b);
        System.out.println();
        //b.showMe(moves);
    }

    // solve the board b using the AVL tree
    public static void aStarSolve(Board b, String name) {
        System.out.println("A Star solve: ");
        GameWithAVL g = new GameWithAVL();
        GameState gameState = new GameState(b);
        var moves = g.playGiven(name, gameState);
        System.out.println();
        //b.showMe(moves);
    }
}
