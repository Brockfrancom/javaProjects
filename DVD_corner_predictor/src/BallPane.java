// 6. add more balls to pane
// -

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallPane extends Pane {
    public final double radius = 20;
    private double x = 20, y = 20;
    private double dx = 2, dy = 2;

    // This is important that these match the ones above.
    private double xinitial = 20, yinitial = 20;
    private double dxinitial = 2, dyinitial = 2;

    private Rectangle square = new Rectangle(x, y, 40,40);
    private Timeline animation;
    public int bounces = 0;
    Text text = new Text(x+10, y+25, ((Integer)bounces).toString());


    private void updateText() {
        text.setText(((Integer)bounces).toString());
        text.setX(x+10);
        text.setY(y+25);
    }

    public BallPane() {
        square.setFill(Color.GREEN); // Set ball color
        getChildren().add(square); // Place a ball into this pane

        square.xProperty().addListener(ov -> updateText());
        getChildren().add(text);

        square.setX(x);
        square.setY(y);

        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.3);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {
        // Check boundaries
        if (x <= 0 || x >= getWidth() - 40) {
            dx *= (-1); // Change ball move direction
            bounces += 1;
        }
        if (y <= 0 || y >= getHeight() - 40) {
            dy *= -1; // Change ball move direction
            bounces += 1;
        }
        if ((x <= 0 || x >= getWidth() - 40) && (y <= 0 || y >= getHeight() - 40)) {
            bounces -= 1;
            square.setFill(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }
        // Adjust ball position
        x += dx;
        y += dy;
        square.setX(x);
        square.setY(y);

        // These 2 lines make a cool design when uncommented. :)

//        Text text2 = new Text(x+10, y+10, "0");
//        getChildren().add(text2);
    }

    public void predictCornerShots() {
        animation.pause();
        List cornerShots = new ArrayList();
        int predictedBounces = 0;
        for (var i=0;i<100000;i++) {
            if (x <= 0 || x >= getWidth() - 40) {
                dx *= (-1); // Change ball move direction
                predictedBounces += 1;
            }
            if (y <= 0 || y >= getHeight() - 40) {
                dy *= (-1); // Change ball move direction
                predictedBounces += 1;
            }
            if ((x <= 0 || x >= getWidth() - 40) && (y <= 0 || y >= getHeight() - 40)) {
                predictedBounces -= 1;
                cornerShots.add(predictedBounces);
            }
            x += dx;
            y += dy;
        }
        square.setX(xinitial);
        square.setY(yinitial);
        x = xinitial;
        y = yinitial;
        dx = dxinitial;
        dy = dyinitial;
        updateText();

        System.out.println("The square and scene has the following properties:\n" +
                "Start point (x,y): (" + x + "," + y + ")\n" +
                "(dx,dy): (" + dx + "," + dy + ")\n" +
                "Scene width, Scene height: " + getWidth() + ", " + getHeight() + "\n" +
                "and will hit the corner on the following bounces:");
        System.out.println(cornerShots);
        System.out.println("Press P to start animation...");
        System.out.println();
    }
}