package org.example.csc311_md3_group_assignment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Car {
    private Pane carPane;
    private Polygon body;
    private Rectangle window1, window2;
    private Circle frontWheel, backWheel;
    private double x, y;
    private final double width = 60, height = 30;

    public Car(double startX, double startY) {
        this.x = startX;
        this.y = startY;

        carPane = new Pane();

        // Create car body (polygon to match the shape in the image)
        body = new Polygon();
        body.getPoints().addAll(
                0.0, 20.0,   // Front bottom-left
                10.0, 5.0,   // Roof front slope
                40.0, 5.0,   // Roof top-right
                50.0, 20.0,  // Back bottom-right
                60.0, 20.0,  // Back bumper
                60.0, 30.0,  // Bottom-back
                0.0, 30.0    // Bottom-front
        );
        body.setFill(Color.PURPLE);

        // Create windows
        window1 = new Rectangle(12, 7, 10, 8);
        window1.setFill(Color.LIGHTGREEN);

        window2 = new Rectangle(26, 7, 12, 8);
        window2.setFill(Color.LIGHTGREEN);

        // Create wheels
        frontWheel = new Circle(8);
        backWheel = new Circle(8);
        frontWheel.setFill(Color.BLACK);
        backWheel.setFill(Color.BLACK);

        // Position the wheels
        frontWheel.setCenterX(15);
        frontWheel.setCenterY(32);
        backWheel.setCenterX(45);
        backWheel.setCenterY(32);

        // Add all parts to the car pane
        carPane.getChildren().addAll(body, window1, window2, frontWheel, backWheel);
        carPane.setLayoutX(x);
        carPane.setLayoutY(y);
    }

    public Pane getCarPane() {
        return carPane;
    }

    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
        carPane.setLayoutX(x);
        carPane.setLayoutY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double angle) {
        carPane.setRotate(angle); // Rotates the entire car
    }

}
