import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class BounceBallControl extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BallPane ballPane = new BallPane(); // Create a ball pane

        // Pause and resume animation
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());

        // Increase and decrease animation
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
            }
            else if (e.getCode() == KeyCode.DOWN) {
                ballPane.decreaseSpeed();
            }
            else if (e.getCode() == KeyCode.P) {
                ballPane.play();
            }
            else if (e.getCode() == KeyCode.S) {
                ballPane.pause();
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(ballPane, 100, 112);
        primaryStage.setTitle("BounceBallControl"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        ballPane.predictCornerShots();
        primaryStage.show(); // Display the stage

        // Resets the animation and recalculates the number of bounces on resizing the pane.
        scene.widthProperty().addListener(ov -> {
                    ballPane.predictCornerShots();
                    ballPane.bounces = 0;
                }
        );
        scene.heightProperty().addListener(ov -> {
                    ballPane.predictCornerShots();
                    ballPane.bounces = 0;
                }
        );

        // Must request focus after the primary stage is displayed
        ballPane.requestFocus();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
