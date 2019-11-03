/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Andrew Brim
 * 2/16/2019
 *
 * Programming Exercise 3 - JavaFX GUIs
 *
 * Program that prompts user to enter the center coordinates, width, height of 2 rectangles,
 * from the command line. The program displays the rectangles and a text indicating whether
 * the two are overlapping, one is contained in the other, or whether they don't overlap.
 *
 * NOTE: This program requires command line arguments, as follows:
 * rec1x rec1y rec1width rec1height rec2x rec2y rec2width rec2height
 * EXAMPLE: 10 20 100 100 30 40 10 10
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafx.scene.paint.Color.*;

public class Rectangles extends Application {

    public static int rec1x;
    public static int rec1y;
    public static int rec1width;
    public static int rec1height;

    public static int rec2x;
    public static int rec2y;
    public static int rec2width;
    public static int rec2height;

    public static void main(String[] args) {
        if (args.length < 8) {
            System.out.println("Please run this program by entering 8 integers without commas: \nrec1x rec1y rec1width rec1height rec2x rec2y rec2width rec2height");
            System.out.println("EXAMPLE: 10 20 100 100 30 40 10 10");
            System.exit(0);
        }
        rec1x = Integer.valueOf(args[0]);
        rec1y = Integer.valueOf(args[1]);
        rec1width = Integer.valueOf(args[2]);
        rec1height = Integer.valueOf(args[3]);

        rec2x = Integer.valueOf(args[4]);
        rec2y = Integer.valueOf(args[5]);
        rec2width = Integer.valueOf(args[6]);
        rec2height = Integer.valueOf(args[7]);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Two rectangles");
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 700, 700);

        Rectangle rectangle1 = new Rectangle(rec1x,rec1y,rec1width,rec1height);
        rectangle1.setFill(TRANSPARENT);
        rectangle1.setStrokeWidth(2);
        rectangle1.setStroke(BLACK);

        Rectangle rectangle2 = new Rectangle(rec2x,rec2y,rec2width,rec2height);
        rectangle2.setFill(TRANSPARENT);
        rectangle2.setStrokeWidth(2);
        rectangle2.setStroke(BLACK);

        pane.getChildren().add(rectangle1);
        pane.getChildren().add(rectangle2);

        // have to call this method twice in order to switch the roles of the rectangles.
        var textvalue1 = isInside(rec1x,rec1y,rec1width,rec1height,rec2x,rec2y,rec2width,rec2height);
        var textvalue2 = isInside(rec2x,rec2y,rec2width,rec2height,rec1x,rec1y,rec1width,rec1height);

        // Logic to interpret the results of the function called previously
        if (textvalue1 == "2" || textvalue2 == "2" ) {
            Text text = new Text("One rectangle is contained in another");
            text.xProperty().bind(pane.widthProperty().divide(2).subtract(35));
            text.yProperty().bind(pane.heightProperty().subtract(10));
            pane.getChildren().add(text);
        }
        else if (textvalue1 == "0" && textvalue2 == "0") {
            Text text = new Text("The rectangles do not overlap");
            text.xProperty().bind(pane.widthProperty().divide(2).subtract(35));
            text.yProperty().bind(pane.heightProperty().subtract(10));
            pane.getChildren().add(text);
        }
        else {
            Text text = new Text("The rectangles overlap");
            text.xProperty().bind(pane.widthProperty().divide(2).subtract(35));
            text.yProperty().bind(pane.heightProperty().subtract(10));
            pane.getChildren().add(text);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // returns a value based on whether a point is in a rectangle of not.
    public String isInside(int x1a, int y1a, int wa, int ha, int x1b, int y1b, int wb, int hb) {
        int x2a = wa + x1a;
        int y2a = ha + y1a;
        if(((x1a < x1b) && (x2a > x1b)) && ((y1a < y1b) && (y2a > y1b))) {
            int x2b = wb + x1b;
            int y2b = hb + y1b;
            if (((x1a < x2b) && (x2a > x2b)) && ((y1a < y2b) && (y2a > y2b))) {
                return "2"; //contains
            }
            else {
                return "1"; // overlap
            }
        }
        int x2b = wb + x1b;
        int y2b = hb + y1b;
        if (((x1a < x2b) && (x2a > x2b)) && ((y1a < y2b) && (y2a > y2b))) {
            return "1"; // overlap
        }
        else {
            return "0"; // none
        }
    }
}
