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

    private Car car;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mazeImage = ivMaze.getImage();
        pixelReader = mazeImage.getPixelReader();


        // Create and add the car
        car = new Car(20, 250); // Start pos
        apMovement.getChildren().add(car.getCarPane());

        apMovement.setOnKeyPressed(this::moveCar);

        apMovement.setFocusTraversable(true);
        apMovement.requestFocus();

        // If focus is lost, bring it back
        apMovement.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                apMovement.requestFocus();
            }
        });

        //Attach key event listener directly to `apMovement`
        //apMovement.setOnKeyPressed(this::moveCharacter);
    }

    /**
     * Handles movement of the robot when arrow keys are pressed.
     */
//    private void moveCharacter(KeyEvent event) {
//        double stepSize = 5;  // Adjust this value if movement is too slow or too fast
//        double newX = robotCharacter.getLayoutX();
//        double newY = robotCharacter.getLayoutY();
//
//        switch (event.getCode()) {
//            case UP:
//                if (canMove(newX, newY - stepSize)) {
//                    robotCharacter.setLayoutY(newY - stepSize);
//                }
//                break;
//            case DOWN:
//                if (canMove(newX, newY + stepSize)) {
//                    robotCharacter.setLayoutY(newY + stepSize);
//                }
//                break;
//            case LEFT:
//                if (canMove(newX - stepSize, newY)) {
//                    robotCharacter.setLayoutX(newX - stepSize);
//                }
//                break;
//            case RIGHT:
//                if (canMove(newX + stepSize, newY)) {
//                    robotCharacter.setLayoutX(newX + stepSize);
//                }
//                break;
//        }
//    }

    /**
     * Handles movement of the car when arrow keys are pressed.
     */
    private void moveCar(KeyEvent event) {
        double stepSize = 5;
        double newX = car.getX();
        double newY = car.getY();

        switch (event.getCode()) {
            case UP:
                car.setRotation(270); // Face Up
                car.getCarPane().setScaleY(1); // Reset flip
                if (canMove(newX, newY - stepSize)) {
                    car.move(0, -stepSize);
                }
                break;
            case DOWN:
                car.setRotation(90); // Face Down
                car.getCarPane().setScaleY(1); // Reset flip
                if (canMove(newX, newY + stepSize)) {
                    car.move(0, stepSize);
                }
                break;
            case LEFT:
                car.setRotation(180); // Face Left
                car.getCarPane().setScaleY(-1); // Flip vertically to fix upside-down issue
                if (canMove(newX - stepSize, newY)) {
                    car.move(-stepSize, 0);
                }
                break;
            case RIGHT:
                car.setRotation(0); // Face Right
                car.getCarPane().setScaleY(1); // Reset flip
                if (canMove(newX + stepSize, newY)) {
                    car.move(stepSize, 0);
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

