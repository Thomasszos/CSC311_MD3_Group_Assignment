package org.example.csc311_md3_group_assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utils {

    //Handles changing of FXMLs.
    private static Stage primaryStage;


    //Sets the uniform vars.
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }


    //Loads whatever FXMl we want.
    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource(fxml)));
        primaryStage.getScene().setRoot(pane);
    }
}
