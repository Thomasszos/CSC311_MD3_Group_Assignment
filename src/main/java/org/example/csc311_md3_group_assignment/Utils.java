package org.example.csc311_md3_group_assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utils {

    private static Stage primaryStage;  // Store the stage reference
    private static Pane contentArea;    // Store the content area reference

    // this method to set the stage
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    // method to set the content area for dynamic scene loading
    public static void setContentArea(Pane pane) {
        contentArea = pane;
    }

    // Change scene inside the content area
    public static void changeScene(String fxml) throws IOException {
        if (contentArea != null) {
            Parent pane = FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource(fxml)));
            contentArea.getChildren().setAll(pane);

            // Force focus on the movement pane
            pane.requestFocus();

            //If the new scene has an AnchorPane named "apMovement", set focus on it
            AnchorPane movementPane = (AnchorPane) pane.lookup("#apMovement");
            if (movementPane != null) {
                movementPane.requestFocus();
            }
        } else {
            System.err.println("Error: Content area is not set!");
        }
    }

}
