package Logic;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Polygon2D {
    private ArrayList<Point2D> point2DList = new ArrayList<>();
    private Color fill;

    public Polygon2D(Color color){
        this.fill = color;
    }

    public void addPoint(double x, double y){
        Point2D point2D = new Point2D(x, y);
        this.point2DList.add(point2D);
    }

    public ArrayList<Point2D> getPoint2DList() {
        return point2DList;
    }

    public void setPoint2DList(ArrayList<Point2D> point2DList) {
        this.point2DList = point2DList;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }
}
