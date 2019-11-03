/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 3/2/2019
 *
 * Programming Exercise 4 - JavaFX GUIs
 *
 * Program to play connect 4
 *
 * Note Although my program works correctly, my logic is kinda complicated,
 * if I was to redo this, I would probably use a 2d
 * array and have simpler logic.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour extends Application {

    private Color player1 = Color.RED;
    private Color player2 = Color.PURPLE;
    private Boolean player1Turn = true;
    private Pane pane = new Pane();
    private Scene scene = new Scene(pane, 900, 800);
    private final List<Circle> circles = new ArrayList<>();
    private boolean flashVariable = true; //Used to control what color is flashed
    private List<Circle> winning = new ArrayList<>(); // used in the recursive functions.

    public static void main(String[]args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Connect Four Game");
        makeBoard();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // This creates a board and a list of circles and displays them.
    private void makeBoard() {
        Rectangle board = new Rectangle(700, 600, Color.BLUE);
        board.setX(100);
        board.setY(100);
        pane.getChildren().add(board);

        for (var i=0;i<6;i++) {
            for (var j=0; j<7; j++) {
                Circle c = new Circle(50, Color.WHITE);
                c.setCenterY(((i *100)+150));
                c.setCenterX(((j *100)+150));
                c.setOnMouseClicked(e -> makeMove(c));
                circles.add(c);
                pane.getChildren().add(c);
            }
        }
    }

    // This code makes sure the clicked circle is valid, then sets it's color, then checks if it is a win.
    private void makeMove(Circle c) {
        if(isValid(c) && player1Turn) {
            c.setFill(player1);
            isWinner(c, player1);
            player1Turn = false;
        }
        else if (isValid(c)) {
            c.setFill(player2);
            isWinner(c, player2);
            player1Turn = true;
        }
    }

    // Logic for a valid move
    private boolean isValid(Circle c) {
        if (c.getFill() == Color.WHITE){
            int index = circles.indexOf(c);
            if (index >= 35){
                return true;
            }
            else return circles.get(index + 7).getFill() != Color.WHITE;
        }
        return false;
    }

    // Checks if winner. Program terminates in the function that returns true if a winner is found
    private void isWinner(Circle c, Color playerColor) {
        if (checkVertical(c, playerColor) ||
                checkRight(c, playerColor) ||
                checkLeft(c, playerColor) ||
                checkLeftAndRight(c, playerColor, 0) ||
                checkDiagonalRight(c, playerColor, 0) ||
                checkDiagonalLeft(c, playerColor, 0)) {
            // Do nothing, program terminates in the function that won. This was used to print out messages during development,
            // if you want the program to print winner, uncomment the line below.
//            System.out.println("Winner!!!");
        }
    }

    // This checks left and right, and will return true if the last piece in a horizontial 4 in a row is in the middle.
    private boolean checkLeftAndRight(Circle c, Color playerColor, int depth) {
        if (depth == 0) { // Resets the winning list on the first call of the function
            winning = new ArrayList<>();
        }
        if (depth >4) { // Makes sure there is no infinite recursion
            return false;
        }
        int index = circles.indexOf(c);
        int row = circles.indexOf(c)/7;
        winning.add(c);
        if (depth > 1) {
            if (index+3 < circles.size() && ((index+3)/7) == row && circles.get(index+3).getFill() == playerColor && !winning.contains((circles.get(index + 3)))) {
                winning.add((circles.get(index+3)));
            }
            if (index-3 >0 && ((index-3)/7) == row && circles.get(index-3).getFill() == playerColor && !winning.contains((circles.get(index - 3)))) {
                winning.add((circles.get(index-3)));
            }
            if (index+1 < circles.size() && ((index+1)/7) == row && circles.get(index+1).getFill() == playerColor && !winning.contains((circles.get(index + 1)))) {
                winning.add((circles.get(index+1)));
            }
            if (index-1 >0 && ((index-1)/7) == row && circles.get(index-1).getFill() == playerColor && !winning.contains((circles.get(index - 1)))) {
                winning.add((circles.get(index-1)));
            }
            if (winning.size() == 4) {
                makeFlash(winning, playerColor);
                return true;
            }
        }
        if (index-1 > 0 && circles.get(index-1).getFill() != playerColor && ((index-1)/7) == row) { //all the way to the left,
            winning.remove(c);
            return checkRight(c, playerColor);
        }
        if (index+1 < circles.size() && circles.get(index+1).getFill() != playerColor && ((index+1)/7) == row) { // all the way to the right
            winning.remove(c);
            return checkLeft(c, playerColor);
        }
        if (index-1 >0 && index + 1 < circles.size() && ((index+1)/7) == row && ((index-1)/7) == row) {
            return checkLeftAndRight(circles.get(index-1), playerColor, depth +1) || checkLeftAndRight(circles.get(index+1), playerColor, depth+1);
        }
        else {
            return false;
        }
    }

    // Checks diagonals to the right
    private boolean checkDiagonalRight(Circle c, Color playerColor, int depth) {
        if (depth == 0) { // resets the winning list
            winning = new ArrayList<>();
        }
        if (depth >4) { // prevents infinite recursion
            return false;
        }
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        winning.add(c);
        if (depth > 2) {
            if (index+8 < circles.size() && circles.get(index+8).getFill() == playerColor && (index+8)%7 != (column+1)) {
                winning.add((circles.get(index+8)));
            }
            if (index-8 > 0 && circles.get(index-8).getFill() == playerColor && (index-8)%7 != (column-1)) {
                winning.add((circles.get(index-8)));
            }
            if (winning.size() == 4) {
                makeFlash(winning, playerColor);
                return true;
            }
        }
        if ((index-8 > 0 && circles.get(index-8).getFill() != playerColor)) { //top left
            winning.remove(c);
            return checkRightDownDiagonal(c, playerColor);
        }
        if ((index+8 < circles.size() && circles.get(index+8).getFill() != playerColor)) { //bottom left
            winning.remove(c);
            return checkRightUpDiagonal(c, playerColor);
        }
        if (index-8 >0 && index+8 < circles.size()) {
            return checkDiagonalRight(circles.get(index-8), playerColor, depth +1) || checkDiagonalRight(circles.get(index+8), playerColor, depth+1);
        }
        else {
            return false;
        }
    }

    // Checks diagonals to the right
    private boolean checkDiagonalLeft(Circle c, Color playerColor, int depth) {
        if (depth == 0) { // resets the winning list
            winning = new ArrayList<>();
        }
        if (depth >4) { // prevents infinite recursion
            return false;
        }
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        winning.add(c);
        if (depth > 2) {
            if (index+6 < circles.size() && circles.get(index+6).getFill() == playerColor && (index+6)%7 != (column-1)) {
                winning.add((circles.get(index+6)));
            }
            if (index-6 > 0 && circles.get(index-6).getFill() == playerColor && (index-6)%7 != (column+1)) {
                winning.add((circles.get(index-6)));
            }
            if (winning.size() == 4) {
                makeFlash(winning, playerColor);
                return true;
            }
        }
        if ((index-6 > 0 && circles.get(index-6).getFill() != playerColor)) { //bottom right
            winning.remove(c);
            return checkLeftUpDiagonal(c, playerColor) || checkLeftDownDiagonal(c, playerColor);
        }
        if ((index+6 < circles.size() && circles.get(index+6).getFill() != playerColor)) { //top right
            winning.remove(c);
            return checkLeftDownDiagonal(c, playerColor) || checkLeftUpDiagonal(c, playerColor);
        }
        if (index-6 >0 && index+6 < circles.size()) {
            return checkDiagonalLeft(circles.get(index-6), playerColor, depth+1) || checkDiagonalLeft(circles.get(index+6), playerColor, depth+1);
        }
        else {
            return false;
        }
    }

    // Checks left down diagonal
    private boolean checkLeftDownDiagonal(Circle c, Color playerColor) {
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        int diagonal = 1;
        try {
            for (var i=0; i<3;i++) {
                index += 6;
                column -= 1;
                if (circles.get(index).getFill() == playerColor && (index%7) == column) {
                    winning.add(circles.get(index));
                    diagonal +=1;
                }
            }
            if (diagonal ==4) {
                makeFlash(winning, playerColor);
                return true;
            }

        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // checks right down diagonal
    private boolean checkRightDownDiagonal(Circle c, Color playerColor) {
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        int diagonal = 1;
        try {
            for (var i=0; i<3;i++) {
                index += 8;
                column += 1;
                if (circles.get(index).getFill() == playerColor && (index%7) == column) {
                    winning.add(circles.get(index));
                    diagonal +=1;
                }
            }
            if (diagonal ==4) {
                makeFlash(winning, playerColor);
                return true;
            }

        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // checks left up diagonal
    private boolean checkLeftUpDiagonal(Circle c, Color playerColor) {
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        int diagonal = 1;
        try {
            for (var i=0; i<3;i++) {
                index -= 8;
                column -= 1;
                if (circles.get(index).getFill() == playerColor && (index%7) == column) {
                    winning.add(circles.get(index));
                    diagonal +=1;
                }
            }
            if (diagonal ==4) {
                makeFlash(winning, playerColor);
                return true;
            }

        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // checks right up diagonal
    private boolean checkRightUpDiagonal(Circle c, Color playerColor) {
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int index = circles.indexOf(c);
        int column = circles.indexOf(c)%7;
        int diagonal = 1;
        try {
            for (var i=0; i<3;i++) {
                index -= 6;
                column += 1;
                if (circles.get(index).getFill() == playerColor && (index%7) == column) {
                    winning.add(circles.get(index));
                    diagonal +=1;
                }
            }
            if (diagonal ==4) {
                makeFlash(winning, playerColor);
                return true;
            }

        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // checks the vertical
    private boolean checkVertical(Circle c, Color playerColor) {
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int index = circles.indexOf(c);
        int vertical = 1;
        try {
            for (var i=0; i<3;i++) {
                index += 7;
                if (circles.get(index).getFill() == playerColor) {
                    winning.add(circles.get(index));
                    vertical +=1;
                }
            }
            if (vertical ==4) {
                makeFlash(winning, playerColor);
                return true;
            }

        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    //checks the left horizontial
    private boolean checkLeft(Circle c, Color playerColor) {
        int index = circles.indexOf(c);
        int row = circles.indexOf(c)/7;
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int horizontial = 1;
        try {
            for (var i = 0; i < 3; i++) {
                index += 1;
                if (circles.get(index).getFill() == playerColor && (index/7) == row) {
                    winning.add(circles.get(index));
                    horizontial += 1;
                }
            }
            if (horizontial == 4) {
                makeFlash(winning, playerColor);
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // checks the right horizontial
    private boolean checkRight(Circle c, Color playerColor) {
        int index = circles.indexOf(c);
        int row = circles.indexOf(c)/7;
        List<Circle> winning = new ArrayList<>();
        winning.add(c);
        int horizontial = 1;
        try {
            for (var i = 0; i < 3; i++) {
                index -= 1;
                if (circles.get(index).getFill() == playerColor && (index/7) == row) {
                    winning.add(circles.get(index));
                    horizontial += 1;
                }
            }
            if (horizontial == 4) {
                makeFlash(winning, playerColor);
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        return false;
    }

    // This sets up the timeline and flashes winning circles. On finishing, the program terminates.
    private void makeFlash(List winningCircles, Color playerColor) {
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(500), e -> makeFlash2(winningCircles, playerColor)));
        t1.setCycleCount(5);
        t1.play();
        t1.setOnFinished(e -> System.exit(0));
    }

    // This gets called 5 times in the make flash function.
    private void makeFlash2(List winningCircles, Color playerColor) {
        if (flashVariable) {
            for (var circle : winningCircles) {
                ((Circle) circle).setFill(Color.CYAN);
            }
            flashVariable = false;
        }
        else {
            for (var circle : winningCircles) {
                ((Circle) circle).setFill(playerColor);
            }
            flashVariable = true;
        }
    }
}
