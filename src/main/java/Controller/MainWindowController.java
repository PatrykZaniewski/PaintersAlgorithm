package Controller;

import Logic.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class MainWindowController {

    public Canvas canvas;
    private long keyCooldown = 5 * 10000000;
    private long lastTimePressed = 0;
    private Calculator calculator;


    @FXML
    public void initialize() {
        Reader reader = new Reader();
        ArrayList<Polygon3D> polygon3DList = reader.readData();
        calculator = new Calculator(polygon3DList);
        calculator.changeTranslation(105, "z");
        calculator.changeTranslation(-105, "y");
        calculator.projection();
        draw(calculator.getPolygon2DList());

        canvas.setOnMouseClicked(e -> {
            double x = e.getX() - 325.0;
            double y = e.getY() - 325.0;
            System.out.println("[" + x +", " + y + "]");
        });

        canvas.setFocusTraversable(true);
        canvas.setOnKeyTyped(this::keyPressed);
    }

    public void draw(ArrayList<Polygon2D> polygon2DList){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 650, 650);
        for(Polygon2D polygon2D : polygon2DList){
            gc = canvas.getGraphicsContext2D();
            gc.beginPath();
            int i = 0;
            double xstart = 0;
            double ystart = 0;
            for(Point2D point2D: polygon2D.getPoint2DList()){
                if(i == 0){
                    xstart = point2D.getX();
                    ystart = point2D.getY();
                    gc.moveTo(point2D.getX(), point2D.getY());
                }
                else
                {
                    gc.lineTo(point2D.getX(), point2D.getY());
                }
                i++;
            }
            gc.lineTo(xstart, ystart);
            gc.setFill(polygon2D.getFill());
            gc.fill();
            gc.stroke();
        }
    }

    public void keyPressed(KeyEvent keyEvent){
        if (System.nanoTime() - lastTimePressed > keyCooldown) {
            switch(keyEvent.getCharacter()){
                case "w":
                    calculator.changeTranslation(-10, "z");
                    break;
                case "s":
                    calculator.changeTranslation(10, "z");
                    break;
                case "a":
                    calculator.changeTranslation(-10, "x");
                    break;
                case "d":
                    calculator.changeTranslation(10, "x");
                    break;
                case "c":
                    calculator.changeTranslation(-10, "y");
                    break;
                case "v":
                    calculator.changeTranslation(10, "y");
                    break;
                case "q":
                    calculator.changeRotation(-30, "y");
                    break;
                case "e":
                    calculator.changeRotation(30, "y");
                    break;
                case "r":
                    calculator.changeRotation(-30, "z");
                    break;
                case "f":
                    calculator.changeRotation(30, "z");
                    break;
                case "t":
                    calculator.changeRotation(30, "x");
                    break;
                case "g":
                    calculator.changeRotation(-30, "x");
                    break;
                case "z":
                    calculator.changeDistance(-15);
                    break;
                case "x":
                    calculator.changeDistance(15);
                    break;
            }
            lastTimePressed = System.nanoTime();
            draw(calculator.projection());
        }
    }

}
