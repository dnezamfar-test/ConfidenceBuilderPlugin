package ConfidenceBuilderPlugin;
import java.util.ArrayList;

public class Line {
    //Variables
    private ArrayList<Point> _pointsList;

    //Getters
    public ArrayList<Point> getPointsList() { return _pointsList; }
    public Point getPoint(int index){return _pointsList.get(index);}
    public int getVerticesCount(){return _pointsList.size();}

    //Constructor
    public Line(double[] x, double[] y) {
        _pointsList = new ArrayList<>();
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
                _pointsList.add(pt);
            }
        }
    //Method
    public void RemovePoint(int index){_pointsList.remove(index);}
}
