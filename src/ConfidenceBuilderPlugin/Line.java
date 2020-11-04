package ConfidenceBuilderPlugin;
import java.util.ArrayList;
import java.lang.Math;

public class Line {
    private ArrayList<Point> points;
    
    public Line(double[] x, double[] y) {
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
            points.add(pt); }
    }
    public void  VisvaligamWhyattSimplify(int numToKeep) {
        //Thins a line down to the specified amount of points based on the VW algorithm.
        //Implementation based on the description of the method here: http://bost.ocks.org/mike/simplify/
        int removeLimit = (points.size()) - numToKeep;
        for(int i = 0; i < removeLimit; i++) {
            int minIndex;
            float minArea = Area(points.get(0), points.get(1), points.get(2)); // This is the baseline we'll start our first comparison to.
            for(int j=2; j< points.size()-2;j++) // starting at 2, because we're gonna start calculating areas, and we already calculated area 1.
            { //ending at minus 2 because we don't want the last point, and the size property uses counting numbers, so we have to reduce by an extra -1 to account for 0 index
                float tmpArea = Area(points.get(j-1), points.get(j), points.get(j+1));
                if (tmpArea < minArea) {
                    minIndex = j;
                    minArea = tmpArea;
                    points.remove(minIndex);
                }
            }
        }
    }
    public void VisvaligamWhyattSimplifyWill(int numToKeep){
        //Stolen from Will's function in LifeSimGIS by the same name. Thins a line down to the specified amount of points based on the VW algorithm.
        int removeLimit = points.size()-numToKeep;
        int minIndex = 1;
        for(int i=0; i<removeLimit; i++){
            double minArea = Area(points.get(0), points.get(1), points.get(2) );
            for(int j=2; j< points.size() - 2; j++){
                double tmpArea = Area(points.get(j-1), points.get(j), points.get(j+1) );
                if(tmpArea < minArea){
                    minIndex = j;
                    minArea = tmpArea;
                }
            }
            points.remove(minIndex);
        }
    }
    private float Area(Point point0, Point point1, Point point2) {
          //taken from Will Lehman's area function in LifeSimGIS by the same name. Takes 3 points and returns the area of the triangle formed by them.
        return ((float) Math.abs(((point0.x*point1.y) + (point1.x*point2.y) + (point2.x*point0.y)
                - (point1.x*point0.y) - (point2.x*point1.y) - (point0.x*point2.y)) * 0.5));
    }
    public double GetInvCDF(double probability){
        // Stolen from Will's statistics Library
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
    private double PerpendicularDistance(Point a, Point b, Point c){
        double area = Math.abs(0.5*(a.x*(b.y-c.y) + b.x * (c.y-a.y) + c.x * (a.y - b.y)));
        double base = Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
        return (area/base * 2); //height
    }
}
