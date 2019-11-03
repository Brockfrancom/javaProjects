import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
/*
Brock Francom
A02052161
CS-2410
Andrew Brim
1/29/2019

Programming Exercise 2 - JavaFX

This program displays hello world in a GUI application.
*/
public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 300, 300, Color.BLUEVIOLET);

        Text text1 = new Text("Hello");
        Text text2 = new Text("World");

        //changes the colors of the text.
        text1.setStroke(Color.RED);
        text2.setStroke(Color.DARKGOLDENROD);

        //binds the text to the size of the frame, so that it is visible.
        text1.yProperty().bind(pane.heightProperty().divide(2));
        text1.xProperty().bind(pane.widthProperty().divide(2).subtract(35));
        text2.xProperty().bind(text1.xProperty().add(35));
        text2.yProperty().bind(text1.yProperty());

        pane.getChildren().add(text1); //adds the text
        pane.getChildren().add(text2);
        primaryStage.setTitle("Hello World Program"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
