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

        Utils.setStage(stage);



        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("firstmaze-view.fxml"));
        //Parent root = (Parent)fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load(), 603, 423);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //Utils.changeScene("secondmaze-view.fxml");








    }

    public static void main(String[] args) {
        launch();
    }
}