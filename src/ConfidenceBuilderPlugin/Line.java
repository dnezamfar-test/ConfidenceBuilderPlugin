package ConfidenceBuilderPlugin;
import java.util.ArrayList;

public class Line {
    //Variables
    private ArrayList<Point> _pointsList;

    //Getters
    public ArrayList<Point> getPointsList() { return _pointsList; }
    public Point getPoint(int index){return _pointsList.get(index);}
    public int getVerticesCount(){return _pointsList.size();}
    public double getIntegratedArea(){return MidpointIntegral();}

    //Constructor
    public Line() {
        _pointsList = new ArrayList<>();
    }
    public Line(double[] x, double[] y) {
        _pointsList = new ArrayList<>();
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
                _pointsList.add(pt);
            }
        }
    //Method
    public void RemovePoint(int index){_pointsList.remove(index);}
    public void AddPoint(Point pnt){_pointsList.add(pnt);}

    //Returns integral of a line using midpoint rectangular integration
    private double MidpointIntegral(){
        double area = 0;
      for(int i=0; i<_pointsList.size()-1;i++){
          double tmpHeight = (_pointsList.get(i).getY()+_pointsList.get(i+1).getY())/2;
          double tmpWidth = _pointsList.get(i+1).getX()-_pointsList.get(i).getX();
          area += tmpHeight*tmpWidth;
      }
      return area;
    }
}
