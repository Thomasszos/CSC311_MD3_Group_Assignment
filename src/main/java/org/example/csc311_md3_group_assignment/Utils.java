package org.example.csc311_md3_group_assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {

    //Handles changing of FXMLs.
    private static Stage primaryStage;
    private static VBox myRoot;


    //Sets the uniform vars.
    public static void setStage(Stage stage,VBox root) {
        primaryStage = stage;
        myRoot = root;
    }


    //Loads whatever FXMl we want to work with.
    public static void changeRoot(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource(fxmlPath));
            fxmlLoader.setRoot(myRoot);
            Scene scene = new Scene(fxmlLoader.load(), 603, 423);
            primaryStage.setTitle("Hello!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
