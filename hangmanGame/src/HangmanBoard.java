/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 3/22/2019
 *
 * Programming Exercise 5 - JavaFX GUIs
 *
 * Program to play hangman. This is the logic and brains of the program.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.paint.Color.*;

public class HangmanBoard {

    private static Text label2 = new Text(10, 725, "Make a guess and press enter");
    private static Text label = new Text(50, 750, "Guess:");
    private static Text loserText = new Text(300, 250, "You Lost!!"); // Losing text
    private static Text winnerText = new Text(300, 250, "You Won!!"); // Winning text
    private static Text correctText = new Text(400, 515, "Correct!"); // Losing text
    private static Text incorrectText = new Text(400, 515, "Incorrect!"); // Winning text
    private static TextField guess = new TextField(""); // Box to input answer
    private static String[] alphabetArray = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // Alphabet
    private static List<Text> guesses; // Will be populated with opaque text for every letter, set to opacity 1 when guessed.
    private static Timeline t1 = new Timeline(new KeyFrame(Duration.millis(2000)));
    private static Timeline t2 = new Timeline(new KeyFrame(Duration.millis(300), (event -> {
        correctText.setOpacity(correctText.getOpacity()-.2);
        incorrectText.setOpacity(incorrectText.getOpacity()-.2);
    })));
    private static List<Text> formattedLetters; // This will contain text objects for each letter in the phrase.
    private static List<Character> unformattedLetters; // This will contain characters for each letter in phrase.
    // These control which part is drawn.
    private static boolean drawHead;
    private static boolean drawBody;
    private static boolean drawLeftArm;
    private static boolean drawRightArm;
    private static boolean drawLeftLeg;
    private static boolean drawRightLeg;
    private static boolean winner;

    // This initializes the board and arrays with the phrase.
    public static void drawBlanks(String phrase) {
        drawGallows(); // initialize board
        displayAlphabet(); // initialize alphabet for the guesses.

        // Splits the phrase into characters,
        char[] CharsArray = new char[phrase.length()];
        phrase.getChars(0, phrase.length(), CharsArray, 0);

        // Draws blanks, and adds transparent text as well.
        var x = 300;
        var y = 600;
        int numSpaces = 0;
        for(char letter:CharsArray) {
            if (letter == ' ' && x > 500) {
                x = 300;
                y += 40;
            }
            else if (letter == ' ') {
                x += 25;
            }
            else {
                Text text = new Text(x+3, y, Character.toString(letter));
                text.setFont(new Font("Verdana", 20));
                text.setFill(Color.TRANSPARENT);
                formattedLetters.add(text);
                unformattedLetters.add(letter);

                Text Blank = new Text(x, y, "___");
                Hangman.pane.getChildren().add(text);
                Hangman.pane.getChildren().add(Blank);
                x += 25;
            }
        }
    }

    // This shows the letters to keep track of what has been guessed.
    private static void displayAlphabet() {
        var x = 500;
        var y = 200;
        for(String letter: alphabetArray) {
            Text text = new Text(x+3, y, letter);
            text.setFont(new Font("Verdana", 20));
            text.setOpacity(.08); // you can see the letters, but they haven't been guessed yet.
            guesses.add(text);
            Text Blank = new Text(x, y, "___");
            Hangman.pane.getChildren().add(text);
            Hangman.pane.getChildren().add(Blank);
            x += 25;
            if (x>750) {
                x = 500;
                y += 40;
            }
        }
    }

    // The following functions draw the gallows of hangman, also initializes globals for the next round.
    private static void drawGallows() {
        Line gallows1 = new Line(100,600,100, 100);
        Hangman.pane.getChildren().add(gallows1);
        Line gallows2 = new Line(100, 100, 400, 100);
        Hangman.pane.getChildren().add(gallows2);
        Line gallows3 = new Line(400, 100, 400, 200);
        Hangman.pane.getChildren().add(gallows3);
        Arc base = new Arc(100, 700, 100, 100, 0, 180);
        base.setFill(TRANSPARENT);
        base.setStrokeWidth(2);
        base.setStroke(BLACK);
        Hangman.pane.getChildren().add(base);

        // Add the guess box
        guess.setLayoutX(100);
        guess.setLayoutY(733);
        guess.setPrefColumnCount(5);
        guess.setOnAction(event -> checkGuess());
        Hangman.pane.getChildren().add(label2);
        Hangman.pane.getChildren().add(label);
        Hangman.pane.getChildren().add(guess);

        // Initialize globals
        formattedLetters = new ArrayList<>();
        unformattedLetters = new ArrayList<>();
        guesses = new ArrayList<>();
        drawHead = true;
        drawBody = true;
        drawLeftArm = true;
        drawRightArm = true;
        drawLeftLeg = true;
        drawRightLeg = true;
        winner = false;
        int guessIndex = Hangman.pane.getChildren().indexOf(guess);
        Hangman.pane.getChildren().get(guessIndex).setDisable(false); // Enable the entry box

        // Set the format of the message displayed.
        correctText.setFont(new Font("Verdana", 20));
        correctText.setFill(GREEN);
        incorrectText.setFont(new Font("Verdana", 20));
        incorrectText.setFill(RED);
        winnerText.setFont(new Font("Verdana", 40));
        winnerText.setFill(GREEN);
        loserText.setFont(new Font("Verdana", 40));
        loserText.setFill(RED);

        // Set up the time lines.
        t1.setCycleCount(1);
        t1.setOnFinished(e -> playAgain());
        t2.setCycleCount(4);
        t2.setOnFinished(e -> {
            Hangman.pane.getChildren().remove(correctText);
            Hangman.pane.getChildren().remove(incorrectText);
            correctText.setOpacity(1);
            incorrectText.setOpacity(1);
        });
    }
    private static void drawHead() {
        Circle head = new Circle(400, 225,25);
        head.setFill(TRANSPARENT);
        head.setStrokeWidth(2);
        head.setStroke(BLACK);
        Hangman.pane.getChildren().add(head);
    }
    private static void drawLeftArm() {
        Line arm = new Line(400, 275, 350, 250);
        Hangman.pane.getChildren().add(arm);
    }
    private static void drawRightArm() {
        Line arm = new Line(400, 275, 450, 250);
        Hangman.pane.getChildren().add(arm);
    }
    private static void drawBody() {
        Line body = new Line(400, 250, 400, 350);
        Hangman.pane.getChildren().add(body);

        // Add an image view and add it to pane
        ImageView imageView = new ImageView("BYU.png");
        imageView.setX(375);
        imageView.setY(275);
        imageView.setFitHeight(40);
        imageView.setFitWidth(50);
        Hangman.pane.getChildren().add(imageView);

    }
    private static void drawLeftLeg() {
        Line leg = new Line(400, 350, 350, 450);
        Hangman.pane.getChildren().add(leg);
    }
    private static void drawRightLeg() {
        Line leg = new Line(400, 350, 450, 450);
        Hangman.pane.getChildren().add(leg);
    }

    // This is ran after each entry, and it shows what has been guessed, checks to see if the guess is correct,
    // and either displays a correct guess or else draws a body part.
    private static void checkGuess(){
        boolean correct = false;
        String input = guess.getText();
        guess.clear(); // clears the guess box

        // Displays the letters guessed.
        for (var i = 0; i< alphabetArray.length; i++) {
            if(alphabetArray[i].compareToIgnoreCase(input) == 0){
                guesses.get(i).setOpacity(1);
            }
        }
        // If a guess is correct, display the letter.
        for (var i=0; i < unformattedLetters.size(); i++) {
            var letter = unformattedLetters.get(i);
            if (Character.toString(letter).compareToIgnoreCase(input) == 0) {
                formattedLetters.get(i).setFill(BLACK);
                if (Hangman.pane.getChildren().contains(correctText)) { // prevents an error
                    Hangman.pane.getChildren().remove(correctText);
                }
                if (Hangman.pane.getChildren().contains(incorrectText)) { // prevents an error
                    Hangman.pane.getChildren().remove(incorrectText);
                }
                Hangman.pane.getChildren().add(correctText); // Display correct
                t2.play();
                correct = true;
            }
        }
        // Check for a win, if all letters are shown you win.
        var count = 0;
        for (var i=0;i<formattedLetters.size(); i++) {
            if(formattedLetters.get(i).getFill() == Color.BLACK){
                count += 1;
            }
        }
        if (count == formattedLetters.size()) {
            winner = true;
            for (var i=0; i < formattedLetters.size(); i++) {
                formattedLetters.get(i).setFill(GREEN); // change colors of letters to show win.
            }
            int guessIndex = Hangman.pane.getChildren().indexOf(guess);
            Hangman.pane.getChildren().get(guessIndex).setDisable(true); // Disable the entry box
            t1.play(); // start animation
        }

        // Logic to handle incorrect guesses
        if (!correct) {
            if (Hangman.pane.getChildren().contains(incorrectText)) { // prevents an error
                Hangman.pane.getChildren().remove(incorrectText);
            }
            if (Hangman.pane.getChildren().contains(correctText)) { // prevents an error
                Hangman.pane.getChildren().remove(correctText);
            }
            Hangman.pane.getChildren().add(incorrectText); // Display incorrect
            t2.play();
            if (drawHead) {
                drawHead();
                drawHead = false;
            }
            else if (drawBody) {
                drawBody();
                drawBody = false;
            }
            else if (drawLeftArm) {
                drawLeftArm();
                drawLeftArm = false;
            }
            else if (drawRightArm) {
                drawRightArm();
                drawRightArm = false;
            }
            else if (drawLeftLeg) {
                drawLeftLeg();
                drawLeftLeg = false;
            }
            else if (drawRightLeg) {
                drawRightLeg();
                drawRightLeg = false;
                for (var i=0; i < formattedLetters.size(); i++) { // You've lost, change text to red
                    formattedLetters.get(i).setFill(RED);
                }
                int guessIndex = Hangman.pane.getChildren().indexOf(guess);
                Hangman.pane.getChildren().get(guessIndex).setDisable(true); // Disable the entry box
                t1.play(); // start animation
            }
        }
    }

    // After animation, this is called, and asks to play again.
    private static void playAgain() {
        Text text = new Text(350, 350, "Play Again?");
        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            Hangman.pane.getChildren().remove(0,Hangman.pane.getChildren().size()); // removes buttons and text
            drawBlanks(Hangman.pickPhrase()); // starts a new game.
        });
        yes.setLayoutX(320);
        yes.setLayoutY(400);

        Button no = new Button("No");
        no.setOnAction(e -> System.exit(0)); // end program
        no.setLayoutX(420);
        no.setLayoutY(400);

        // Clear pane, add buttons and messages.
        Hangman.pane.getChildren().remove(0,Hangman.pane.getChildren().size());
        // Displays correct message.
        if (winner){
            Hangman.pane.getChildren().add(winnerText);
        }
        else {
            Hangman.pane.getChildren().add(loserText);
        }
        Hangman.pane.getChildren().add(text);
        Hangman.pane.getChildren().add(yes);
        Hangman.pane.getChildren().add(no);
    }
}
