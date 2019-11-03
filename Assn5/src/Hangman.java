/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 3/22/2019
 *
 * Programming Exercise 5 - JavaFX GUIs
 *
 * Program to play hangman. This is where the program starts.
 * A random phrase is picked and sent to the Hangman board.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Hangman extends Application {
    public static Pane pane = new Pane();
    public static Scene scene = new Scene(pane, 800, 800);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hangman");

        HangmanBoard.drawBlanks(pickPhrase()); // Picks the phrase, and sends it to the hangman board.

        primaryStage.setResizable(false); // Prevents the user from resizing the stage and hiding data.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // This picks a random phrase from the file, and returns it.
    public static String pickPhrase() {
        List<String> phrases = new ArrayList<>();

        // Reads in file to list.
        try  {
            java.util.Scanner input = new java.util.Scanner(new java.io.File("phrases.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                phrases.add(line);
            }
        }
        catch (Exception e){
            System.out.println("File not found.");
            System.exit(0);
        }
        var randomInteger = (int)(Math.random() * ((phrases.size() - 1))); // random integer from 0 to the size of the array.
        return phrases.get(randomInteger);
    }
}
