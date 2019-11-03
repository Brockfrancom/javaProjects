import java.util.Scanner;
public class Game {
    int boardsPlacedOnQueue = 0;
    int boardsRemovedFromQueue = 0;

    // plays the game and returns the most efficient solution
    public void playGiven(String label, Board b){
        System.out.println(label + "\n"+  b);
        boardsPlacedOnQueue = 0; //reset counters
        boardsRemovedFromQueue = 0;
        Board perfect = new Board(); // perfect board to check against
        perfect.makeBoard(0);
        Queue<Board> q = new Queue();
        q.enqueue(b);
        boardsPlacedOnQueue += 1;
        for (int i = 0; i<10000000; i++) { // makes all possible moves and append legal boards to the queue
            var current = q.dequeue();
            boardsRemovedFromQueue += 1;
            Board copy = new Board(current);
            var move = copy.makeMove('U', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('D', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('L', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('R', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }
        }
        System.out.println("Unsolvable");
        System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                boardsRemovedFromQueue + " were removed from the queue." );
        return; // prevents overflow, and prevents program from running too long.
    }

    public void playRandom(String label, int jumbleCount){
        Board b = new Board();
        b.makeBoard(jumbleCount);
        System.out.println(label + "\n" + b);
        boardsPlacedOnQueue = 0; //reset counters
        boardsRemovedFromQueue = 0;
        Board perfect = new Board(); // perfect board to check against
        perfect.makeBoard(0);
        Queue<Board> q = new Queue();
        q.enqueue(b);
        boardsPlacedOnQueue += 1;
        for (int i = 0; i<10000000; i++) { // makes all possible moves and append legal boards to the queue
            var current = q.dequeue();
            boardsRemovedFromQueue += 1;
            Board copy = new Board(current);
            var move = copy.makeMove('U', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('D', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('L', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }

            copy = new Board(current);
            move = copy.makeMove('R', current.lastMove);
            if (move != ' ') {
                copy.lastMove = move;
                copy.moves = copy.moves + move;
                q.enqueue(copy);
                boardsPlacedOnQueue += 1;
            }
            if (perfect.equals(copy)) {
                System.out.println(copy.moves);
                System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                        boardsRemovedFromQueue + " were removed from the queue." );
                return;
            }
        }
        System.out.println("Unsolvable");
        System.out.println(boardsPlacedOnQueue + " boards were placed on the queue, and " +
                boardsRemovedFromQueue + " were removed from the queue." );
        return; // prevents overflow, and prevents program from running too long.
    }

    // this is where the program enters, and the user can continue for as long as they would like.
    public static void main(String[] args) {
       Game g = new Game();
        Scanner in = new Scanner(System.in);

        int [] game0 = { 1, 2, 3, 7, 4, 0, 6, 5, 8 };
        Board b = new Board();
        b.makeBoard(game0);
        g.playGiven("game 0", b);
        System.out.println("Click any key to continue\n");
        String resp;
        resp= in.nextLine();

        int []game1 = { 1, 3, 2, 4, 5, 6, 8, 7, 0 };
        b.makeBoard(game1);
        g.playGiven("game 1", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game2 = { 1, 3, 8, 6, 2, 0, 5, 4, 7 };
        b.makeBoard(game2);
        g.playGiven("game 2", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game3 = { 4, 0, 1, 3, 5, 2, 6, 8, 7 };
        b.makeBoard(game3);
        g.playGiven("game 3", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game4 = { 7, 6, 4, 0, 8, 1, 2, 3, 5 };  // Warning slow to solve
        b.makeBoard(game4);
        g.playGiven("game 4", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game5 = { 1, 2, 3, 4, 5, 6, 8, 7, 0 };   // Warning unsolvable
        b.makeBoard(game5);
        g.playGiven("game 5", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

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
