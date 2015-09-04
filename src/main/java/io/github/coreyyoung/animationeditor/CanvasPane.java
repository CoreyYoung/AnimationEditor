package io.github.coreyyoung.animationeditor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class CanvasPane extends Region {

    private final Canvas canvas = new Canvas();

    public CanvasPane() {
        getChildren().add(canvas);
        canvas.widthProperty().addListener(event -> draw());
        canvas.heightProperty().addListener(event -> draw());

    }

    /**
     * Makes the canvas fill the pane.
     */
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        canvas.relocate(getInsets().getLeft(), getInsets().getRight());
        canvas.setWidth(Math.max(0, getWidth() - (getInsets().getLeft() + getInsets().getRight())));
        canvas.setHeight(Math.max(0, getHeight() - (getInsets().getTop() + getInsets().getBottom())));
    }

    /**
     * Redraws the canvas. This is called whenever the canvas is resized.
     */
    private void draw() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        context.setFill(Color.GRAY);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
