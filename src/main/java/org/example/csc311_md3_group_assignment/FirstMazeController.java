package org.example.csc311_md3_group_assignment;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;

public class FirstMazeController {

    @FXML private Pane rootPane;
    @FXML private ImageView mazeView;
    @FXML private ImageView robotView;  // Back to using FXML-injected ImageView

    private PixelReader mazeReader;
    private static final double MOVE_DELTA = 5;
    private static final Color ALLOWED_COLOR = Color.WHITE;

    @FXML
    public void initialize() {
        // Load maze image
        Image mazeImage = new Image(getClass().getResource("maze.png").toExternalForm());
        mazeView.setImage(mazeImage);
        mazeReader = mazeImage.getPixelReader();

        // Load robot image
        Image robotImage = new Image(getClass().getResource("robot.png").toExternalForm());
        robotView.setImage(robotImage);
        robotView.setLayoutX(50);  // Initial position
        robotView.setLayoutY(50);
        robotView.setFitWidth(30);  // Set size
        robotView.setFitHeight(30);

        // Set up key handling
        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
                newScene.setOnKeyPressed(this::handleKeyPress);
                newScene.getRoot().requestFocus();
            }
        });

        // Ensure the pane can receive focus
        rootPane.setFocusTraversable(true);
    }

    private void handleKeyPress(KeyEvent event) {
        double dx = 0;
        double dy = 0;

        switch (event.getCode()) {
            case LEFT:
                dx = -MOVE_DELTA;
                break;
            case RIGHT:
                dx = MOVE_DELTA;
                break;
            case UP:
                dy = -MOVE_DELTA;
                break;
            case DOWN:
                dy = MOVE_DELTA;
                break;
            default:
                return;
        }

        double newX = robotView.getLayoutX() + dx;
        double newY = robotView.getLayoutY() + dy;

        if (canMove(newX, newY)) {
            robotView.setLayoutX(newX);
            robotView.setLayoutY(newY);
        }

        event.consume();
    }

    private boolean canMove(double newX, double newY) {
        // Calculate the center position of the robot at the new location
        double centerX = newX + robotView.getFitWidth() / 2;
        double centerY = newY + robotView.getFitHeight() / 2;

        // Check bounds
        if (centerX < 0 || centerY < 0 ||
                centerX >= mazeView.getFitWidth() ||
                centerY >= mazeView.getFitHeight()) {
            return false;
        }

        try {

            double radius = Math.min(robotView.getFitWidth(), robotView.getFitHeight()) / 4;


            return isAllowedColor((int)centerX, (int)centerY) &&
                    isAllowedColor((int)(centerX - radius), (int)centerY) &&
                    isAllowedColor((int)(centerX + radius), (int)centerY) &&
                    isAllowedColor((int)centerX, (int)(centerY - radius)) &&
                    isAllowedColor((int)centerX, (int)(centerY + radius));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isAllowedColor(int x, int y) {
        try {
            Color color = mazeReader.getColor(x, y);
            return color.equals(ALLOWED_COLOR);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}