/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 2/16/2019
 *
 * Programming Exercise 3 - JavaFX GUIs
 *
 * Program to display a drawing for Hangman.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import static javafx.scene.paint.Color.*;

public class Hangman extends Application {
    public Pane pane = new Pane();
    public Scene scene = new Scene(pane, 800, 800);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hangman");
        drawGallows();
        drawHead();
        drawBody();
        drawLeftArm();
        drawRightArm();
        drawLeftLeg();
        drawRightLeg();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // The following functions draw the different parts of hangman.
    public void drawGallows() {
        Line gallows1 = new Line(100,600,100, 100);
        pane.getChildren().add(gallows1);
        Line gallows2 = new Line(100, 100, 400, 100);
        pane.getChildren().add(gallows2);
        Line gallows3 = new Line(400, 100, 400, 200);
        pane.getChildren().add(gallows3);
        Arc base = new Arc(100, 700, 100, 100, 0, 180);
        base.setFill(WHITE);
        base.setStrokeWidth(2);
        base.setStroke(BLACK);
        pane.getChildren().add(base);
    }
    public void drawHead() {
        Circle head = new Circle(400, 225,25);
        head.setFill(WHITE);
        head.setStrokeWidth(2);
        head.setStroke(BLACK);
        pane.getChildren().add(head);
    }
    public void drawLeftArm() {
        Line arm = new Line(400, 275, 350, 250);
        pane.getChildren().add(arm);
    }
    public void drawRightArm() {
        Line arm = new Line(400, 275, 450, 250);
        pane.getChildren().add(arm);
    }
    public void drawBody() {
        Line body = new Line(400, 250, 400, 350);
        pane.getChildren().add(body);
    }
    public void drawLeftLeg() {
        Line leg = new Line(400, 350, 350, 450);
        pane.getChildren().add(leg);
    }
    public void drawRightLeg() {
        Line leg = new Line(400, 350, 450, 450);
        pane.getChildren().add(leg);
    }
}
