/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 2/16/2019
 *
 * Programming Exercise 3 - JavaFX GUIs
 *
 * Program to display Welcome to Java around a circle. (Display each character in right location
 * with appropriate rotations using a loop)
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CharactersInCircle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to Java Circle");
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 700, 700);
        // create a list of all the characters
        List message = new ArrayList();
        message.add("W");
        message.add("e");
        message.add("l");
        message.add("c");
        message.add("o");
        message.add("m");
        message.add("e");
        message.add(" ");
        message.add("t");
        message.add("o");
        message.add(" ");
        message.add("J");
        message.add("a");
        message.add("v");
        message.add("a");
        message.add(" ");

        // This block handles all the math to rotate and add the characters in a circle.
        int rotationIncrement = (360/message.size());
        int radius = 100;
        int rotation = 90;
        for (var letter : message) {
            Text text = new Text((String) letter);
            text.setRotate(-rotation + 180);
            int x = (int)((Math.sin(Math.toRadians(rotation)) * radius)) + 400;
            int y = (int)((Math.cos(Math.toRadians(rotation)) * radius)) + 400;
            text.setX(x);
            text.setY(y);
            rotation -= rotationIncrement;
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            pane.getChildren().add(text);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
