package org.example.csc311_md3_group_assignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Utils.setStage(stage);

        // Create a TabPane
        TabPane tabPane = new TabPane();

        // Create tabs for different FXML views
        Tab tab1 = new Tab("First Maze");
        Tab tab2 = new Tab("Second Maze");

        // Prevent tabs from being closed
        tab1.setClosable(false);
        tab2.setClosable(false);

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(tab1, tab2);

        // Create a content area where scenes will be dynamically loaded
        Pane contentArea = new Pane();
        Utils.setContentArea(contentArea); // Let Utils know where to load FXML content

        // Create a BorderPane
        BorderPane root = new BorderPane();
        root.setTop(tabPane);       // Tabs stay at the top
        root.setCenter(contentArea); // Content swaps below the tabs

        // Tab change logic: Load different FXML files when switching tabs
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            try {
                if (newTab == tab1) {
                    Utils.changeScene("firstmaze-view.fxml");
                } else if (newTab == tab2) {
                    Utils.changeScene("secondmaze-view.fxml");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Create the scene with the BorderPane
        Scene scene = new Scene(root, 603, 423);
        stage.setTitle("Maze Application");
        stage.setScene(scene);
        stage.show();

        // Load the default FXML (First tab)
        Utils.changeScene("firstmaze-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
