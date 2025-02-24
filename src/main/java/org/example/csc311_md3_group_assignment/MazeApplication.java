package org.example.csc311_md3_group_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MazeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Utils.setStage(stage);

        // Create a TabPane to hold multiple tabs
        TabPane tabPane = new TabPane();

        // Create the first tab and load its FXML content
        Tab tab1 = new Tab("Maze 1");
        FXMLLoader loader1 = new FXMLLoader(MazeApplication.class.getResource("firstmaze-view.fxml"));
        tab1.setContent(loader1.load());
        FirstMazeController controller1 = loader1.getController();
        tabPane.getTabs().add(tab1);

        // Create a second tab and load its FXML content
        Tab tab2 = new Tab("Maze 2");
        FXMLLoader loader2 = new FXMLLoader(MazeApplication.class.getResource("secondmaze-view.fxml"));
        tab2.setContent(loader2.load());
        SecondMazeController controller2 = loader2.getController();
        tabPane.getTabs().add(tab2);


        tab1.setClosable(false);
        tab2.setClosable(false);

        // Create a scene with the TabPane
        Scene scene = new Scene(tabPane, 603, 423);

        // Add a key event filter to the scene to handle arrow keys
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                case LEFT:
                case RIGHT:
                    // Get the currently selected tab and forward the key event
                    int selectedIndex = tabPane.getSelectionModel().getSelectedIndex();
                    if (selectedIndex == 0) {
                        controller1.moveCharacter(event);
                    } else if (selectedIndex == 1) {
                        controller2.moveCharacter(event);
                    }
                    // Consume the event to prevent TabPane from processing it
                    event.consume();
                    break;
                default:
                    // Let other key events pass through
                    break;
            }
        });

        stage.setTitle("Maze Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}