package org.example.csc311_md3_group_assignment;


import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstMazeController implements Initializable {

    @FXML
    private AnchorPane apMovement;

    @FXML
    private Canvas cvMazeHolder;

    @FXML
    private ImageView ivMaze;

    @FXML
    private StackPane spHolder;

    @FXML
    private VBox vbRoot;

    Image robotImage = new Image(getClass().getResource("robot.png").toExternalForm());
    ImageView robotCharacter = new ImageView(robotImage);



    Image mazeImage;
    PixelReader pixelReader;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load the maze image and prepare for pixel reading
        mazeImage = ivMaze.getImage();
        pixelReader = mazeImage.getPixelReader();

        // Define the robot's initial position and size
        robotCharacter.setFitHeight(15);
        robotCharacter.setFitWidth(15);
        robotCharacter.setLayoutX(15);
        robotCharacter.setLayoutY(254);

        // Add the robot to the AnchorPane
        apMovement.getChildren().add(robotCharacter);

        // Ensure the pane captures key events
        apMovement.setFocusTraversable(true);
        apMovement.requestFocus();

        //Attach key event listener directly to apMovement
        apMovement.setOnKeyPressed(this::moveCharacter);

        // If focus is lost, automatically bring it back
        apMovement.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                apMovement.requestFocus();
            }
        });

        // Prevent TabPane from stealing arrow key events
        vbRoot.setOnKeyPressed(this::moveCharacter);
    }



    @FXML
    void moveCharacter(KeyEvent event) {
        Color color;
        switch (event.getCode()) {
            //Tries to move up when up arrow pressed. Works only if the top left corner of the robot will NOT appear in a blue spot.
            case UP:
                color = pixelReader.getColor((int)robotCharacter.getLayoutX(), (int)robotCharacter.getLayoutY()-5);
                if (color.equals(Color.WHITE)) {
                    robotCharacter.setLayoutY(robotCharacter.getLayoutY() - 5);
                }
                System.out.println(color);
                break;
            case DOWN:
                //Same as up but in the if it calculates the size of the robot.
                color = pixelReader.getColor((int)robotCharacter.getLayoutX(), (int)robotCharacter.getLayoutY()+(int)robotCharacter.getFitHeight()+5);
                if (color.equals(Color.WHITE)) {
                    robotCharacter.setLayoutY(robotCharacter.getLayoutY() + 5);
                }
                System.out.println(color);
                break;
            case LEFT:
                color = pixelReader.getColor((int)robotCharacter.getLayoutX()-5, (int)robotCharacter.getLayoutY());
                if (color.equals(Color.WHITE)) {
                    robotCharacter.setLayoutX(robotCharacter.getLayoutX() - 5);
                }
                System.out.println(color);
                //robotCharacter.setLayoutX(robotCharacter.getLayoutX() - 5);
                break;
            case RIGHT:
                color = pixelReader.getColor((int)robotCharacter.getLayoutX()+(int)robotCharacter.getFitWidth()+5, (int)robotCharacter.getLayoutY());
                if (color.equals(Color.WHITE)) {
                    robotCharacter.setLayoutX(robotCharacter.getLayoutX() + 5);
                }
                System.out.println(color);
                //robotCharacter.setLayoutX(robotCharacter.getLayoutX() + 5);
                break;
            default:
                break;
        }

        checkForExit();

    }

    private void checkForExit() {
        // Define the exit region based on the maze image dimensions (example: bottom-right 50x50 area)
        double exitThresholdX = ivMaze.getFitWidth() - 50; // Near the right edge
        double exitThresholdY = ivMaze.getFitHeight() / 2 - 25;
        if (robotCharacter.getLayoutX() >= exitThresholdX && robotCharacter.getLayoutY() >= exitThresholdY) {
            System.out.println("Exit reached!");
            // ADDED: Trigger the transition to the second maze.
            triggerExitTransition();
        }
    }

    // ADDED: New method to animate (fade out) and switch to the second maze scene.
    private void triggerExitTransition() {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), robotCharacter);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> {
            try {
                // Use the Utils class to change the scene to the second maze FXML.
                Utils.changeScene("secondmaze-view.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fade.play();
    }
}

