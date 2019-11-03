/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 4/16/2019
 *
 * Programming Exercise 7 - JavaFX GUIs
 *
 * Program to play Sudoku. This is ran by running the main function to start.
 *
 * NOTE: If the random file that is read in is Sudoku7, the program will freeze
 * for about a minute. If the file is Sudoku6, the board is already solved. If
 * the file is Sudoku4 or Sudoku5, There is no solution.
 *
 * This program also prints out the solution to the console. If this behavior is not
 * desired, comment out lines 108 and 113. Thanks!!
 *
 * Run this main to run the program!!
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;

public class Sudoku extends Application {
    private static Pane pane = new Pane();
    private static Scene scene = new Scene(pane, 244, 300);
    private static Button solve = new Button("Solve");
    private static Button clear = new Button("Clear");
    private static Text correct = new Text(100, 20, "Correct!");
    private static Text incorrect = new Text(75, 20, "Incorrect Solution");
    private static Text noSolution = new Text(37, 20, "Input board has no solution");
    private static Text hasSolution = new Text(39, 20, "Input board has a solution");
    private static Timeline t1 = new Timeline(new KeyFrame(Duration.millis(2000)));


    // These are the original grid, the solution, and the gui grid.
    private static TextField[][] inputgrid;
    private static int[][] solution;
    private static int[][] grid;

    // This is the starting point
    public static void main(String[] args) {
        launch(args);
    }

    // This randomly selects a .txt file and reads it in
    private static void readAPuzzleFromFile() {
        var randomInteger = (int)(Math.random() * 8); // random integer from 0 to 8.
        String file;
        switch(randomInteger) {
            case 0:
                file = "Sudoku.txt";
                break;
            case 1:
                file = "Sudoku1.txt";
                break;
            case 2:
                file = "Sudoku2.txt";
                break;
            case 3:
                file = "Sudoku3.txt";
                break;
            case 4:
                file = "Sudoku4.txt"; // No solution
                break;
            case 5:
                file = "Sudoku5.txt"; // No solution
                break;
            case 6:
                file = "Sudoku6.txt"; // Already solved
                break;
            case 7:
                file = "Sudoku7.txt"; // Takes a while (35ish seconds)
                break;
            default:
                file = "Sudoku.txt"; // Never called
        }
        // Reads in file.
        try  {
            java.util.Scanner input = new java.util.Scanner(new java.io.File(file));
            System.out.println("Reading in " + file + "...");
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    grid[i][j] = input.nextInt();
        }
        catch (Exception e){
            System.out.println("File not found.");
            System.exit(0);
        }
    }

    // This solves the board, and it is stored as solution
    private static void solve() {
        // Copy the board that was read in
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = grid[i][j];
            }
        }
        // Solve the board
        if (search(solution)) {
            System.out.println("The solution is found");
            pane.getChildren().add(hasSolution);
            printGrid(solution); // prints out the solution to the console.
        }
        else {
            System.out.println("No solution");
            pane.getChildren().add(noSolution);
            printGrid(grid); // prints out the file read in to the console.
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku");
        primaryStage.setResizable(false); // Prevents the user from resizing the stage and hiding data.
        primaryStage.setScene(scene);
        primaryStage.show();
        startGUI(); // Set up the GUI
    }

    // Set up the GUI
    private static void startGUI() {
        solution = new int[9][9];
        inputgrid = new TextField[9][9];
        grid = new int[9][9];
        t1.setCycleCount(1);
        t1.setOnFinished(e -> {
            solve.setDisable(false);
            pane.getChildren().remove(0, pane.getChildren().size()); // Clear the board
            startGUI(); // Reset the GUI
        });


        readAPuzzleFromFile(); // Read in file
        solve(); // Solve the board

        // Display the boxes
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                var text = new TextField(Integer.toString((grid[i][j])));
                inputgrid[i][j] = text;
                if (text.getText().equals("0")){
                    text.setText("");
                }
                else{
                    text.setDisable(true);
                }
                text.setPrefColumnCount(1);
                text.setLayoutY((i*26) + 30);
                text.setLayoutX(j*27);
                pane.getChildren().add(text);
            }
        }
        // Set up the buttons
        solve.setLayoutY(270);
        solve.setLayoutX(65);
        solve.setOnAction(event -> {
            var boardCorrect = true;
            if(pane.getChildren().contains(noSolution)) {
                pane.getChildren().remove(noSolution);
            }
            if(pane.getChildren().contains(hasSolution)){
                pane.getChildren().remove(hasSolution);
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (Integer.toString(grid[i][j]).equals(inputgrid[i][j].getText())){
                        // Read in from file, do nothing
                    }
                    else if(Integer.toString(solution[i][j]).equals(inputgrid[i][j].getText())){
                        // Correct input, do nothing
                    }
                    else if(!Integer.toString(solution[i][j]).equals(inputgrid[i][j].getText()) && !inputgrid[i][j].getText().equals("")){
                        inputgrid[i][j].setStyle("-fx-text-fill:red;");
                        // Entry is wrong, make it red.
                    }
                    else { // No entry, turn it blue and insert numbers.
                        inputgrid[i][j].setStyle("-fx-text-fill:blue;");
                        inputgrid[i][j].setText(Integer.toString(solution[i][j]));
                        if(boardCorrect){
                            pane.getChildren().add(incorrect);
                            boardCorrect = false;
                        }
                    }
                }
            }
            if (boardCorrect){
                pane.getChildren().add(correct);
            }
            solve.setDisable(true);
            t1.play();
        });
        pane.getChildren().add(solve);
        clear.setLayoutY(270);
        clear.setLayoutX(125);
        clear.setOnAction(event -> {
            solve.setDisable(false);
            pane.getChildren().remove(0, pane.getChildren().size()); // Clear the board
            startGUI(); // Reset the GUI
        });
        pane.getChildren().add(clear);
    }

    /** The code below here is all starter code, with only small modifications. */

    /** Obtain a list of free cells from the puzzle */
    private static int[][] getFreeCellList(int[][] grid) {
        // Determine the number of free cells
        int numberOfFreeCells = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0)
                    numberOfFreeCells++;

        // Store free cell positions into freeCellList
        int[][] freeCellList = new int[numberOfFreeCells][2];
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0) {
                    freeCellList[count][0] = i;
                    freeCellList[count++][1] = j;
                }

        return freeCellList;
    }

    /** Print the values in the grid */
    private static void printGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    /** Search for a solution */
    private static boolean search(int[][] grid) {
        int[][] freeCellList = getFreeCellList(grid); // Free cells
        if (freeCellList.length == 0)
            return true; // "No free cells");

        int k = 0; // Start from the first free cell
        while (true) {
            int i = freeCellList[k][0];
            int j = freeCellList[k][1];
            if (grid[i][j] == 0)
                grid[i][j] = 1; // Fill the free cell with number 1

            if (isValid(i, j, grid)) {
                if (k + 1 == freeCellList.length) { // No more free cells
                    return true; // A solution is found
                }
                else { // Move to the next free cell
                    k++;
                }
            }
            else if (grid[i][j] < 9) {
                // Fill the free cell with the next possible value
                grid[i][j] = grid[i][j] + 1;
            }
            else { // free cell grid[i][j] is 9, backtrack
                while (grid[i][j] == 9) {
                    if (k == 0) {
                        return false; // No possible value
                    }
                    grid[i][j] = 0; // Reset to free cell
                    k--; // Backtrack to the preceding free cell
                    i = freeCellList[k][0];
                    j = freeCellList[k][1];
                }

                // Fill the free cell with the next possible value,
                // search continues from this free cell at k
                grid[i][j] = grid[i][j] + 1;
            }
        }
    }

    /** Check whether grid[i][j] is valid in the grid */
    private static boolean isValid(int i, int j, int[][] grid) {
        // Check whether grid[i][j] is valid at the i's row
        for (int column = 0; column < 9; column++)
            if (column != j && grid[i][column] == grid[i][j])
                return false;

        // Check whether grid[i][j] is valid at the j's column
        for (int row = 0; row < 9; row++)
            if (row != i && grid[row][j] == grid[i][j])
                return false;

        // Check whether grid[i][j] is valid in the 3 by 3 box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if (row != i && col != j && grid[row][col] == grid[i][j])
                    return false;

        return true; // The current value at grid[i][j] is valid
    }

    /** Check whether the fixed cells are valid in the grid */
    private static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] < 0 || grid[i][j] > 9 ||
                        (grid[i][j] != 0 && !isValid(i, j, grid)))
                    return false;

        return true; // The fixed cells are valid
    }

    /** Read a Sudoku.txt puzzle from the keyboard */
    private static void readAPuzzle() {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a Sudoku.txt puzzle:");
        grid = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = input.nextInt();
    }
}