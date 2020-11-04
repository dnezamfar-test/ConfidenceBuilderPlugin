package ConfidenceBuilderPlugin;
import java.util.ArrayList;


public class Polygon {
    //fields
    private ArrayList<Point> _vertices;
    //getters
    public ArrayList<Point> getVertices() {
        return _vertices;
    }
    //constructors
    public Polygon(double[] x, double[] y) {
        for (int i = 0; i < x.length; i++) {
            Point pt = new Point(x[i], y[i]);
            _vertices.add(pt);
        }
    }
    //methods
    public static float AreaOfTriangle(Point point0, Point point1, Point point2)
    {
        //taken from Will Lehman's area function in LifeSimGIS by the same name. Takes 3 points and returns the area of the triangle formed by them. *****FIX
        return ((float) Math.abs(((point0.getX()*point1.getY()) + (point1.getX()*point2.getY()) + (point2.getX()*point0.getY())
                - (point1.getX()*point0.getY()) - (point2.getX()*point1.getY()) - (point0.getX()*point2.getY())) * 0.5));
    }
}
