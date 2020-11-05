package ConfidenceBuilderPlugin;
import java.util.ArrayList;

public class Line {
    //Variables
    private ArrayList<Point> points;
    //Getter
    public ArrayList<Point> getPoints() { return points; }
    //Constructor
    public Line(double[] x, double[] y) {
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
            points.add(pt); }
    }
    //Methods
    public void  VisvaligamWhyattSimplify(int numToKeep, ArrayList<Point> pointList) {
        //Thins a line down to the specified amount of points based on the VW algorithm.
        //Implementation based on the description of the method here: http://bost.ocks.org/mike/simplify/

        int removeLimit = (pointList.size()) - numToKeep;
        int minIndex = 1;
        for(int i = 0; i < removeLimit; i++) {
            float minArea = Polygon.AreaOfTriangle(pointList.get(0), pointList.get(1), pointList.get(2)); // This is the baseline we'll start our first comparison to.
            for(int j=2; j< pointList.size()-2;j++) // starting at 2, because we're gonna start calculating areas, and we already calculated area 1.
            { //ending at minus 2 because we don't want the last point, and the size property uses counting numbers, so we have to reduce by an extra -1 to account for 0 index
                float tmpArea = Polygon.AreaOfTriangle(pointList.get(j-1), pointList.get(j), pointList.get(j+1));
                if (tmpArea < minArea) {
                    minIndex = j;
                    minArea = tmpArea;
                }
            }
            pointList.remove(minIndex);
        }
    }
}
