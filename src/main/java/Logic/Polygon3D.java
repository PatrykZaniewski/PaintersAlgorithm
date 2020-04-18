package Logic;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Polygon3D implements Comparable<Polygon3D> {
    ArrayList<Point3D> point3DList = new ArrayList<>();
    double minZ = 0;
    Color fill;

    public Polygon3D(){
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        this.fill = Color.rgb(red, green, blue);
    }

    public ArrayList<Point3D> getPoint3DList() {
        return point3DList;
    }

    public void setPoint3DList(ArrayList<Point3D> point3DList) {
        this.point3DList = point3DList;
    }

    public void addPoint(double x, double y, double z){
        Point3D point3D = new Point3D(x, y, z);
        this.point3DList.add(point3D);
        this.minZ = (this.minZ * point3DList.size() + z) / (this.point3DList.size() + 1);
    }

    public Double getMinZ() {
        return minZ;
    }

    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    @Override
    public int compareTo(Polygon3D o) {
        return this.getMinZ().compareTo(o.getMinZ());
    }

}
