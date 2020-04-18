package Logic;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class Calculator {

    private ArrayList<Polygon3D> polygon3DList;
    private ArrayList<Polygon2D> polygon2DList;

    double distance = -200f;

    public Calculator(ArrayList<Polygon3D> polygon3DList) {
        polygon3DList.sort(Collections.reverseOrder());
        this.polygon3DList = polygon3DList;
    }

    public ArrayList<Polygon2D> projection() {
        polygon2DList = new ArrayList<>();
        for (Polygon3D polygon3D : polygon3DList) {
            Polygon2D polygon2D = new Polygon2D(polygon3D.getFill());
            ArrayList<Point2D> point2DList = new ArrayList<>();
            for (Point3D point3D : polygon3D.getPoint3DList()) {
                double x = point3D.getX();
                double y = point3D.getY();
                double z = point3D.getZ();

                double xp = ((x * distance) / (z > 1 ? z : 1)) + 325;
                double yp = ((y * distance) / (z > 1 ? z : 1)) + 325;

                Point2D point2D = new Point2D(xp, yp);
                point2DList.add(point2D);
            }
            polygon2D.setPoint2DList(point2DList);
            polygon2DList.add(polygon2D);
        }
        return polygon2DList;
    }

    public ArrayList<Polygon3D> getPolygon3DList() {
        return polygon3DList;
    }

    public void setPolygon3DList(ArrayList<Polygon3D> polygon3DList) {
        this.polygon3DList = polygon3DList;
    }

    public ArrayList<Polygon2D> getPolygon2DList() {
        return polygon2DList;
    }

    public void setPolygon2DList(ArrayList<Polygon2D> polygon2DList) {
        this.polygon2DList = polygon2DList;
    }

    public void changeDistance(double change) {
        this.distance += change;
        if (this.distance < -1000) {
            this.distance = -1000;
        }
        if (this.distance > -20) {
            this.distance = -20;
        }
    }

    public void changeTranslation(double change, String axis) {
        for (Polygon3D polygon3D : this.polygon3DList) {
            for (Point3D point3D : polygon3D.getPoint3DList()) {
                switch (axis) {
                    case "x":
                        point3D.setX(point3D.getX() + change);
                        break;
                    case "y":
                        point3D.setY(point3D.getY() + change);
                        break;
                    case "z":
                        point3D.setZ(point3D.getZ() + change);
                        break;
                }
            }
        }
    }

    public void changeRotation(double change, String axis) {
        change = Math.toRadians(change);
        for (Polygon3D polygon3D : this.polygon3DList) {
            for (Point3D point3D : polygon3D.getPoint3DList()) {
                double x = point3D.getX();
                double y = point3D.getY();
                double z = point3D.getZ();
                switch (axis) {
                    case "x":
                        point3D.setY(Math.cos(change) * y - Math.sin(change) * z);
                        point3D.setZ(Math.sin(change) * y + Math.cos(change) * z);
                        break;
                    case "y":
                        point3D.setX(Math.cos(change) * x + Math.sin(change) * z);
                        point3D.setZ(-Math.sin(change) * x + Math.cos(change) * z);
                        break;
                    case "z":
                        point3D.setX(Math.cos(change) * x - Math.sin(change) * y);
                        point3D.setY(Math.sin(change) * x + Math.cos(change) * y);
                        break;
                }
            }
        }
    }
}
