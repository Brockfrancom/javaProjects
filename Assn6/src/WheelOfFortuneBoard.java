/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 3/26/2019
 *
 * Programming Exercise 6 - JavaFX GUIs
 *
 * Program to play Wheel of Fortune. This is the logic and brains of the program.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static javafx.scene.paint.Color.*;

public class WheelOfFortuneBoard {

    // Global nodes, texts, and input fields.
    private static ImageView image = new ImageView(new Image("wheel.png"));
    private static Button spin = new Button("Spin");
    private static Text label2 = new Text(10, 725, "Make a guess and press enter");
    private static Text score = new Text(90, 600, "0"); // Score
    private static Text label = new Text(50, 750, "Guess:");
    private static Text winnerText = new Text(300, 250, "You Won!!"); // Winning text
    private static Text correctText = new Text(400, 515, "Correct!"); // Losing text
    private static Text incorrectText = new Text(400, 515, "Incorrect!"); // Winning text
    private static TextField guess = new TextField(""); // Box to input answer
    private static String[] alphabetArray = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // Alphabet
    private static List<Text> guesses; // Will be populated with opaque text for every letter, set to opacity 1 when guessed.
    private static Timeline t1 = new Timeline(new KeyFrame(Duration.millis(2000)));
    private static Timeline t2 = new Timeline(new KeyFrame(Duration.millis(300), (event -> {
        correctText.setOpacity(correctText.getOpacity() - .2);
        incorrectText.setOpacity(incorrectText.getOpacity() - .2);
    })));
    private static Timeline t3 = new Timeline(new KeyFrame(Duration.millis(2), (event -> {
        image.setRotate(image.getRotate()+1);
    })));

    private static List<Text> formattedLetters; // This will contain text objects for each letter in the phrase.
    private static List<Character> unformattedLetters; // This will contain characters for each letter in phrase.

    // This initializes the board and arrays with the phrase.
    public static void drawBlanks(String phrase) {
        initializeBoard(); // initialize board
        displayAlphabet(); // initialize alphabet for the guesses.

        // Splits the phrase into characters,
        char[] CharsArray = new char[phrase.length()];
        phrase.getChars(0, phrase.length(), CharsArray, 0);

        // Draws blanks, and adds transparent text as well.
        var x = 300;
        var y = 600;
        for (char letter : CharsArray) {
            if (letter == ' ' && x > 500) {
                x = 300;
                y += 40;
            } else if (letter == ' ') {
                x += 25;
            } else {
                Text text = new Text(x + 3, y, Character.toString(letter));
                text.setFont(new Font("Verdana", 20));
                text.setFill(Color.TRANSPARENT);
                formattedLetters.add(text);
                unformattedLetters.add(letter);

                Text Blank = new Text(x, y, "___");
                WheelOfFortune.pane.getChildren().add(text);
                WheelOfFortune.pane.getChildren().add(Blank);
                x += 25;
            }
        }
    }

    // This shows the letters to keep track of what has been guessed.
    private static void displayAlphabet() {
        var x = 500;
        var y = 200;
        for (String letter : alphabetArray) {
            Text text = new Text(x + 3, y, letter);
            text.setFont(new Font("Verdana", 20));
            text.setOpacity(.08); // you can see the letters, but they haven't been guessed yet.
            guesses.add(text);
            Text Blank = new Text(x, y, "___");
            WheelOfFortune.pane.getChildren().add(text);
            WheelOfFortune.pane.getChildren().add(Blank);
            x += 25;
            if (x > 750) {
                x = 500;
                y += 40;
            }
        }
    }

    // The following function initializes globals for the round.
    private static void initializeBoard() {
        // Make the spinner
        image.setFitWidth(400);
        image.setFitHeight(400);
        image.setY(20);
        image.setX(20);
        image.setRotate(0);
        WheelOfFortune.pane.getChildren().add(image);

        // Draw the pointer
        Polygon pointer = new Polygon();
        pointer.getPoints().addAll(375.0, 75.0, 400.0, 25.0, 425.0, 50.0);
        WheelOfFortune.pane.getChildren().add(pointer);

        // Add the guess box
        guess.setLayoutX(100);
        guess.setLayoutY(733);
        guess.setPrefColumnCount(5);
        guess.setOnAction(event -> checkGuess());
        WheelOfFortune.pane.getChildren().add(label2);
        WheelOfFortune.pane.getChildren().add(label);
        WheelOfFortune.pane.getChildren().add(guess);

        // Add the score label and spin button
        Text text = new Text(50, 600, "Score:");
        spin.setOnAction(e -> {
            t3.play();
            spin.setDisable(true);
            guess.setDisable(false); // Enable the entry box
        });
        spin.setLayoutX(100);
        spin.setLayoutY(650);
        spin.setDisable(false);
        WheelOfFortune.pane.getChildren().add(spin);
        WheelOfFortune.pane.getChildren().add(text);
        WheelOfFortune.pane.getChildren().add(score);

        // Initialize globals
        formattedLetters = new ArrayList<>();
        unformattedLetters = new ArrayList<>();
        guesses = new ArrayList<>();
        score.setText("0");
        guess.setDisable(true); // Disable the entry box

        // Set the format of the message displayed.
        correctText.setFont(new Font("Verdana", 20));
        correctText.setFill(GREEN);
        incorrectText.setFont(new Font("Verdana", 20));
        incorrectText.setFill(RED);
        winnerText.setFont(new Font("Verdana", 40));
        winnerText.setFill(GREEN);

        // Set up the time lines.
        t1.setCycleCount(1);
        t1.setOnFinished(e -> playAgain());
        t2.setCycleCount(4);
        t2.setOnFinished(e -> {
            WheelOfFortune.pane.getChildren().remove(correctText);
            WheelOfFortune.pane.getChildren().remove(incorrectText);
            correctText.setOpacity(1);
            incorrectText.setOpacity(1);
        });
        t3.setCycleCount(500);
    }

    // This updates the score
    private static void updateScore(int numberCorrect) {
        int spinnerScore = 0;
        double rotate = image.getRotate();
        rotate = rotate%360;

        // if incorrect, score = 0
        if(numberCorrect == 0) {
            score.setText("0");
            return;
        }
        // If correct, update score
        if (0<rotate && rotate<=45) {
            spinnerScore += (numberCorrect*500);
        }
        else if(45<rotate && rotate<=90) {
            spinnerScore += (numberCorrect*100);
        }
        else if(90<rotate && rotate<=135) {
            spinnerScore += (numberCorrect*700);
        }
        else if(135<rotate && rotate<=180) {
            spinnerScore += (numberCorrect*300);
        }
        else if(180<rotate && rotate<=225) {
            spinnerScore += (numberCorrect*800);
        }
        else if(225<rotate && rotate<=270) {
            spinnerScore += (numberCorrect*200);
        }
        else if(270<rotate && rotate<=315) {
            spinnerScore += (numberCorrect*600);
        }
        else {
            spinnerScore += (numberCorrect*400);
        }

        int previousScore = Integer.parseInt(score.getText());
        int currentScore = previousScore + spinnerScore;
        String scoreString = Integer.toString(currentScore);
        score.setText(scoreString);
    }

    // This is ran after each entry, and it shows what has been guessed, checks to see if the guess is correct,
    // and either displays a correct guess or incorrect guess.
    private static void checkGuess() {
        boolean correct = false;
        int numberCorrect = 0;
        String input = guess.getText();
        guess.clear(); // clears the guess box
        guess.setDisable(true);
        spin.setDisable(false);


        // Displays the letters guessed.
        for (var i = 0; i < alphabetArray.length; i++) {
            if (alphabetArray[i].compareToIgnoreCase(input) == 0) {
                if (guesses.get(i).getOpacity() == .08) {
                    guesses.get(i).setOpacity(1);
                }
                else {
                    guess.setDisable(false);
                    return;
                }
            }
        }

        // If a guess is correct, display the letter.
        for (var i = 0; i < unformattedLetters.size(); i++) {
            var letter = unformattedLetters.get(i);
            if (Character.toString(letter).compareToIgnoreCase(input) == 0) {
                if (formattedLetters.get(i).getFill() == TRANSPARENT) {
                    formattedLetters.get(i).setFill(BLACK);
                    if (WheelOfFortune.pane.getChildren().contains(correctText)) { // prevents an error
                        WheelOfFortune.pane.getChildren().remove(correctText);
                    }
                    if (WheelOfFortune.pane.getChildren().contains(incorrectText)) { // prevents an error
                        WheelOfFortune.pane.getChildren().remove(incorrectText);
                    }
                    WheelOfFortune.pane.getChildren().add(correctText); // Display correct
                    correct = true;
                    numberCorrect += 1;
                    t2.play();
                }
                else {
                    correct = true;
                    guess.setDisable(false); // Let them guess again
                }
            }
        }
        updateScore(numberCorrect); //Calculates and updates the score

        // Check for a win, if all letters are shown you win.
        var count = 0;
        for (var i = 0; i < formattedLetters.size(); i++) {
            if (formattedLetters.get(i).getFill() == Color.BLACK) {
                count += 1;
            }
        }
        // change colors of letters to show win.
        if (count == formattedLetters.size()) {
            for (var i = 0; i < formattedLetters.size(); i++) {
                formattedLetters.get(i).setFill(GREEN);
            }
            spin.setDisable(true);
            guess.setDisable(true); // Disable the entry box
            t1.play(); // start animation
        }

        // Logic to handle incorrect guesses
        if (!correct) {
            if (WheelOfFortune.pane.getChildren().contains(incorrectText)) { // prevents an error
                WheelOfFortune.pane.getChildren().remove(incorrectText);
            }
            if (WheelOfFortune.pane.getChildren().contains(correctText)) { // prevents an error
                WheelOfFortune.pane.getChildren().remove(correctText);
            }
            WheelOfFortune.pane.getChildren().add(incorrectText); // Display incorrect
            t2.play();
        }
    }

    // After animation, this is called, and asks to play again.
    private static void playAgain() {
        Text text1 = new Text(340, 300, "Score:");
        Text text2 = new Text(390, 300, score.getText());

        // Create a drop down comboBox
        ObservableList options = FXCollections.observableArrayList("Reset board");
        ComboBox comboBox = new ComboBox(options);
        comboBox.setLayoutX(325);
        comboBox.setLayoutY(345);
        comboBox.setOnAction(e -> {
            WheelOfFortune.pane.getChildren().remove(0, WheelOfFortune.pane.getChildren().size()); // removes buttons and text
            drawBlanks(WheelOfFortune.pickPhrase()); // starts a new game.
        });

        // Clear pane, add drop down and messages.
        WheelOfFortune.pane.getChildren().remove(0, WheelOfFortune.pane.getChildren().size());
        WheelOfFortune.pane.getChildren().add(winnerText);
        WheelOfFortune.pane.getChildren().add(comboBox);
        WheelOfFortune.pane.getChildren().add(text1);
        WheelOfFortune.pane.getChildren().add(text2);
    }
}