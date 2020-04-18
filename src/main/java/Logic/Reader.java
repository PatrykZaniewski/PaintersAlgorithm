package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public ArrayList<Polygon3D> readData(){
        File A = new File("src/main/resource/data.txt");
        Scanner scannerA;
        ArrayList<Polygon3D> polygon3DList = new ArrayList<>();
        try {
            scannerA = new Scanner(A);
            while (scannerA.hasNext()) {
                Polygon3D polygon3D = new Polygon3D();
                String line = scannerA.nextLine();
                line = line.replace("\\s+", "");
                String[] array = line.split(";");
                for(String string: array){
                    String[] coord = string.split(",");
                    double x = Double.parseDouble(coord[0]);
                    double y = Double.parseDouble(coord[1]);
                    double z = Double.parseDouble(coord[2]);
                    polygon3D.addPoint(x, y, z);
                }
                polygon3DList.add(polygon3D);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return polygon3DList;
    }
}
