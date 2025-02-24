package org.example.csc311_md3_group_assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondMazeController implements Initializable {

    @FXML
    private AnchorPane apMovement;  // Pane where the robot moves

    @FXML
    private Canvas cvMazeHolder;

    @FXML
    private ImageView ivMaze;  // Holds the maze image

    @FXML
    private StackPane spHolder;

    @FXML
    private VBox vbRoot;

    private Image robotImage;
    private ImageView robotCharacter;
    private Image mazeImage;
    private PixelReader pixelReader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the maze image and prepare for pixel reading
        mazeImage = ivMaze.getImage();
        pixelReader = mazeImage.getPixelReader();

        // Load the robot character
        robotImage = new Image(getClass().getResource("robot.png").toExternalForm());
        robotCharacter = new ImageView(robotImage);

        // Set the robot’s initial size and position
        robotCharacter.setFitHeight(15);
        robotCharacter.setFitWidth(15);
        robotCharacter.setLayoutX(15);  // Starting X position
        robotCharacter.setLayoutY(254); // Starting Y position

        // Add the robot to the movement pane
        apMovement.getChildren().add(robotCharacter);

        // Enable keyboard controls for movement
        apMovement.setOnKeyPressed(this::moveCharacter);

        // Ensure focus is set so key events work
        apMovement.setFocusTraversable(true);
        apMovement.requestFocus();
    }

    /**
     * Handles movement of the robot when arrow keys are pressed.
     */
    public void moveCharacter(KeyEvent event) {
        double stepSize = 5;  // Adjust this value if movement is too slow or too fast
        double newX = robotCharacter.getLayoutX();
        double newY = robotCharacter.getLayoutY();

        switch (event.getCode()) {
            case UP:
                if (canMove(newX, newY - stepSize)) {
                    robotCharacter.setLayoutY(newY - stepSize);
                }
                break;
            case DOWN:
                if (canMove(newX, newY + stepSize)) {
                    robotCharacter.setLayoutY(newY + stepSize);
                }
                break;
            case LEFT:
                if (canMove(newX - stepSize, newY)) {
                    robotCharacter.setLayoutX(newX - stepSize);
                }
                break;
            case RIGHT:
                if (canMove(newX + stepSize, newY)) {
                    robotCharacter.setLayoutX(newX + stepSize);
                }
                break;
        }
    }

    /**
     * Checks if the robot can move to a new position based on pixel color.
     */
    private boolean canMove(double x, double y) {
        // Prevent moving outside the maze boundaries
        if (x < 0 || y < 0 || x >= mazeImage.getWidth() || y >= mazeImage.getHeight()) {
            return false;
        }

        // Get pixel color at the new position
        Color color = pixelReader.getColor((int) x, (int) y);

        // Allow movement if the pixel is **not black** (walls are usually black)
        return color.getBrightness() > 0.3;  // Adjusted from 0.9 to 0.3 to allow more movement
    }
}

