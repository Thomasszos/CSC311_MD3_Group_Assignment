package org.example.csc311_md3_group_assignment;

import javafx.scene.layout.Pane;

public class Car {
    private Pane carPane;
    private int x, y;

    public Car(int x, int y) {
        this.x = x;
        this.y = y;
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

}
