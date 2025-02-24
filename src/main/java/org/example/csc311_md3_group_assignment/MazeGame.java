package org.example.csc311_md3_group_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MazeGame extends Application {
    private ImageView droid;
    private static final int MOVE_DISTANCE = 5;
    private boolean isCollision = false;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML maze
            Parent root = FXMLLoader.load(getClass().getResource("firstmaze-view.fxml"));

            // Create the droid
            droid = new ImageView(new Image(getClass().getResourceAsStream("robot.png"))); // Replace with your droid image
            droid.setFitHeight(30);
            droid.setFitWidth(30);
            droid.setX(50); // Starting X position
            droid.setY(50); // Starting Y position

            // Add the droid to the scene
            if (root instanceof Pane) {
                ((Pane) root).getChildren().add(droid);
            }

            Scene scene = new Scene(root);

            // Add key event handling
            scene.setOnKeyPressed(event -> {
                double newX = droid.getX();
                double newY = droid.getY();

                switch (event.getCode()) {
                    case UP:
                        newY -= MOVE_DISTANCE;
                        break;
                    case DOWN:
                        newY += MOVE_DISTANCE;
                        break;
                    case LEFT:
                        newX -= MOVE_DISTANCE;
                        break;
                    case RIGHT:
                        newX += MOVE_DISTANCE;
                        break;
                }

                // Check if the new position would be valid (not colliding with walls)
                if (!checkCollision(newX, newY)) {
                    droid.setX(newX);
                    droid.setY(newY);
                }
            });

            primaryStage.setScene(scene);
            primaryStage.setTitle("Maze Game");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkCollision(double x, double y) {


        // For now, just prevent going outside the scene bounds
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}