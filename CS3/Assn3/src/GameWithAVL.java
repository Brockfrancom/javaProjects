/**
 * Assignment 3 for CS 2420
 * Game with an AVL tree this is used in SliderGame.
 * @author Brock Francom, A02052161
 */
import java.util.Scanner;

public class GameWithAVL {
    int boardsPlacedOnTree = 0;
    int boardsRemovedFromTree = 0;

    // plays the game and returns the most efficient solution
    public String playGiven(String label, GameState b){
        System.out.println(label + "\n"+  b.board);
        boardsPlacedOnTree = 0; //reset counters
        boardsRemovedFromTree = 0;
        Board perfect = new Board(); // perfect board to check against
        perfect.makeBoard(0);
        AVLTree<GameState> t = new AVLTree();
        t.insert(b);
        char moveList[] = {'U', 'D', 'R', 'L'};
        boardsPlacedOnTree += 1;
        for (int i = 0; i<10000000; i++) { // makes all possible moves and append legal boards to the tree
            var current = t.findMin();
            t.deleteMin(); // makes sure the same board doesn't get played again.
            boardsRemovedFromTree += 1;
            for (char m:moveList) {
                GameState copy = new GameState(current);
                var move = copy.makeMove(m, current.board.lastMove);
                if (move != ' ') {
                    copy.board.lastMove = move;
                    t.insert(copy);
                    boardsPlacedOnTree += 1;
                }
                if (perfect.equals(copy.board)) {
                    System.out.println(copy.moves);
                    System.out.println(boardsPlacedOnTree + " boards were placed on the tree, and " +
                            boardsRemovedFromTree + " were removed from the tree.");
                    return copy.moves;
                }
            }
        }
        System.out.println("Unsolvable");
        System.out.println(boardsPlacedOnTree + " boards were placed on the tree, and " +
                boardsRemovedFromTree + " were removed from the tree." );
        return " ";
    }

    public String playRandom(String label, int jumbleCount){
        Board b = new Board();
        b.makeBoard(jumbleCount);
        GameState gameState = new GameState(b);
        System.out.println(label + "\n" + b);
        boardsPlacedOnTree = 0; //reset counters
        boardsRemovedFromTree = 0;
        Board perfect = new Board(); // perfect board to check against
        perfect.makeBoard(0);
        AVLTree<GameState> t = new AVLTree();
        t.insert(gameState);
        char moveList[] = {'L', 'D', 'R', 'U'};
        boardsPlacedOnTree += 1;
        for (int i = 0; i<1000000; i++) { // makes all possible moves and append legal boards to the tree
            var current = t.findMin();
            t.deleteMin();
            boardsRemovedFromTree += 1;
            for (char m:moveList) {
                GameState copy = new GameState(current);
                var move = copy.makeMove(m, current.board.lastMove);
                if (move != ' ') {
                    t.insert(copy);
                    boardsPlacedOnTree += 1;
                }
                if (perfect.equals(copy.board)) {
                    System.out.println(copy.moves);
                    System.out.println(boardsPlacedOnTree + " boards were placed on the tree, and " +
                            boardsRemovedFromTree + " were removed from the tree.");
                    return copy.moves;
                }
            }
        }
        System.out.println("Unsolvable");
        System.out.println(boardsPlacedOnTree + " boards were placed on the tree, and " +
                boardsRemovedFromTree + " were removed from the tree." );
        return " ";
    }

    // this is where the program enters, and the user can continue for as long as they would like.
    public static void gameWithAVL(String[] args) {
        GameWithAVL g = new GameWithAVL();
        Scanner in = new Scanner(System.in);

        int [] game0 = { 1, 2, 3, 7, 4, 0, 6, 5, 8 };
        Board b = new Board();
        b.makeBoard(game0);
        GameState gameState = new GameState(b);
        g.playGiven("game 0", gameState);
        System.out.println("Click any key to continue\n");
        String resp;
        resp= in.nextLine();

        int []game1 = { 1, 3, 2, 4, 5, 6, 8, 7, 0 };
        b.makeBoard(game1);
        gameState = new GameState(b);
        g.playGiven("game 1", gameState);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game2 = { 1, 3, 8, 6, 2, 0, 5, 4, 7 };
        b.makeBoard(game2);
        gameState = new GameState(b);
        g.playGiven("game 2", gameState);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game3 = { 4, 0, 1, 3, 5, 2, 6, 8, 7 };
        b.makeBoard(game3);
        gameState = new GameState(b);
        g.playGiven("game 3", gameState);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game4 = { 7, 6, 4, 0, 8, 1, 2, 3, 5 };  // Warning slow to solve
        b.makeBoard(game4);
        gameState = new GameState(b);
        g.playGiven("game 4", gameState);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game5 = { 1, 2, 3, 4, 5, 6, 8, 7, 0 };   // Warning unsolvable
        b.makeBoard(game5);
        gameState = new GameState(b);
        g.playGiven("game 5", gameState);

        boolean playAgain = true;
	    int JUMBLECT = 4;  // how much jumbling to to in random board
        while (playAgain) {
            g.playRandom("Random Board", JUMBLECT);
            System.out.println("Play Again?  Answer Y for yes\n");

            // to prevent an error on a blank enter
            try {
                resp = in.nextLine().toUpperCase();
                playAgain = (resp.charAt(0) == 'Y');
            }
            catch (Exception ex) {
                playAgain = false;
            }
        }
    }
}
