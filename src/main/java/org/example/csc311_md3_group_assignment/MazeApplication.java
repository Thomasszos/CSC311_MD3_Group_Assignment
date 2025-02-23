package org.example.csc311_md3_group_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();

        Utils.setStage(stage, root);
        Utils.changeRoot("firstmaze-view.fxml");

//        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("firstmaze-view.fxml"));
//        fxmlLoader.setRoot(root);
//        Scene scene = new Scene(fxmlLoader.load(), 603, 423);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}