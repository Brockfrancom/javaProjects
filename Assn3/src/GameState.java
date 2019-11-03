/**
 * Assignment 3 for CS 2420
 * This is the game state that is put on the AVL tree.
 * A game state with a lower priority is better.
 * @author Brock Francom, A02052161
 */
public class GameState implements Comparable<GameState> {
    Board board;
    Board perfect = new Board();
    int costSoFar;
    int estimatedCost;
    int priority;
    String moves;

    // constructor
    public GameState(Board b) {
        this.board = b;
        this.costSoFar = 0;
        this.estimatedCost = estimateCost();
        this.priority = costSoFar + estimatedCost;
        this.moves = "";
        this.perfect.makeBoard(0);
    }

    // copy constructor
    public GameState(GameState current) {
        this.board = new Board(current.board);
        this.costSoFar = current.costSoFar;
        this.estimatedCost = current.estimatedCost;
        this.priority = current.priority;
        this.moves = current.moves;
        this.perfect = current.perfect;
    }

    @Override
    public int compareTo(GameState o) {
        return this.priority - o.priority;
    }

    //calls the move functions from board, and then updates the game state.
    public char makeMove(char u, char lastMove) {
        char move = board.makeMove(u, lastMove);
        moves = moves + move;
        board.lastMove = lastMove;
        update();
        return move;
    }

    // This estimates the cost of solving the board
    private int estimateCost() {
        var numberOff = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.board[i][j] != perfect.board[i][j]) {
                    numberOff += 1;
                }
            }
        }
        return numberOff;
    }

    // This updates the game state
    private void update() {
        this.costSoFar += 1;
        this.estimatedCost = estimateCost();
        this.priority = costSoFar + estimatedCost;
    }
}
