package ConfidenceBuilderPlugin;

public class Point {
    //fields
    private final double _x;
    private final double _y;
    //getters
    public double getX() {
        return _x;
    }
    public double getY() {
        return _y;
    }
    //constructor
    public Point(double x, double y){
        _x = x;
        _y = y;
    }
    private double PerpendicularDistance(Point lineStart, Point testPoint, Point lineEnd){
        double area = Math.abs(0.5*(lineStart.getX()*(testPoint.getY()-lineEnd.getY()) + testPoint.getX() * (lineEnd.getY()-lineStart.getY()) + lineEnd.getX() * (lineStart.getY() - testPoint.getY())));
        double base = Math.sqrt(Math.pow(lineStart.getX()-testPoint.getX(),2)+Math.pow(lineStart.getY()-testPoint.getY(),2));
        return (area/base * 2); //height
    }
}

