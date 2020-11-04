package ConfidenceBuilderPlugin;
import java.util.ArrayList;
import java.lang.Math;

public class Line {
    //Variables
    private ArrayList<Point> points;
    //Constructor
    public Line(double[] x, double[] y) {
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
            points.add(pt); }
    }
    //Methods
    public void  VisvaligamWhyattSimplify(int numToKeep) {
        //Thins a line down to the specified amount of points based on the VW algorithm.
        //Implementation based on the description of the method here: http://bost.ocks.org/mike/simplify/
        int removeLimit = (points.size()) - numToKeep;
        int minIndex = 1;
        for(int i = 0; i < removeLimit; i++) {
            float minArea = Polygon.AreaOfTriangle(points.get(0), points.get(1), points.get(2)); // This is the baseline we'll start our first comparison to.
            for(int j=2; j< points.size()-2;j++) // starting at 2, because we're gonna start calculating areas, and we already calculated area 1.
            { //ending at minus 2 because we don't want the last point, and the size property uses counting numbers, so we have to reduce by an extra -1 to account for 0 index
                float tmpArea = Polygon.AreaOfTriangle(points.get(j-1), points.get(j), points.get(j+1));
                if (tmpArea < minArea) {
                    minIndex = j;
                    minArea = tmpArea;
                }
            }
            points.remove(minIndex);
        }
    }
    private double PerpendicularDistance(Point lineStart, Point testPoint, Point lineEnd){
        double area = Math.abs(0.5*(lineStart.getX()*(testPoint.getY()-lineEnd.getY()) + testPoint.getX() * (lineEnd.getY()-lineStart.getY()) + lineEnd.getX() * (lineStart.getY() - testPoint.getY())));
        double base = Math.sqrt(Math.pow(lineStart.getX()-testPoint.getX(),2)+Math.pow(lineStart.getY()-testPoint.getY(),2));
        return (area/base * 2); //height
    }
}
