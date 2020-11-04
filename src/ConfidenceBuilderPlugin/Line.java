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

    private double GetInvCDF(double probability){
        // Stolen from Will's statistics Library //Create normal distribution class. Does not belong in line.
        double zScore;
        //Taylor Series Coefficients
        double c0 = 2.515517;
        double c1 = 0.802853;
        double c2 = 0.010328;
        double d1 = 1.432788;
        double d2 = 0.189269;
        double d3 = 0.001308;
        //QC the input
        if(probability == 0.5) { zScore = 0; return zScore;}
        if(probability <= 0) {probability = 0.0000000000000001;}
        if(probability >= 1) {probability = 0.9999999999999999;}
        //Conversion happens here
        double t = Math.sqrt(Math.log(1/Math.pow(probability,2)));
        zScore = t - (c0+c1*t+c2*Math.pow(t,2)) / (1+d1*t+d2*Math.pow(t,2)+d3*Math.pow(t,3));
        return zScore;
    }
    private double PerpendicularDistance(Point lineStart, Point testPoint, Point lineEnd){
        double area = Math.abs(0.5*(lineStart.getX()*(testPoint.getY()-lineEnd.getY()) + testPoint.getX() * (lineEnd.getY()-lineStart.getY()) + lineEnd.getX() * (lineStart.getY() - testPoint.getY())));
        double base = Math.sqrt(Math.pow(lineStart.getX()-testPoint.getX(),2)+Math.pow(lineStart.getY()-testPoint.getY(),2));
        return (area/base * 2); //height
    }
}
