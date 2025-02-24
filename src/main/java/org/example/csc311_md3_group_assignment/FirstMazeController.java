package org.example.csc311_md3_group_assignment;

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

        //Makes sure the image is loaded and preps reading pixels.
        mazeImage = ivMaze.getImage();
        pixelReader = mazeImage.getPixelReader();


        //Defines Characters initial starting point and size.
        robotCharacter.setFitHeight(15);
        robotCharacter.setFitWidth(15);
        robotCharacter.setLayoutX(15);
        robotCharacter.setLayoutY(254);

        //Adds robot to the Anchor Pane. Do not put him in the stack pane as that auto-centers.
        apMovement.getChildren().add(robotCharacter);


        //If you don't set this then your arrow keys won't be registered, unless you apply them to a different part of the scene
        spHolder.setFocusTraversable(true); // Allow focus on the container
        spHolder.requestFocus();

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
    }


}
