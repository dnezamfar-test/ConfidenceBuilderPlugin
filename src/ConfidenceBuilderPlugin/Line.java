package ConfidenceBuilderPlugin;
import java.util.ArrayList;

public class Line {
    //Variables
    private ArrayList<Point> _pointsList;
    private double[] _xOrds;
    private double[] _yOrds;

    //Getters
    public ArrayList<Point> getPointsList() { return _pointsList; }
    public Point getPoint(int index){return _pointsList.get(index);}
    public int getVerticesCount(){return _pointsList.size();}
    public double getIntegratedArea(){return MidpointIntegral();}
    public double[] getXords(){ return _xOrds;}
    public double[] getYords(){ return _yOrds;}

    //Constructor
    public Line() {
        _pointsList = new ArrayList<>();
        _xOrds = null;
        _yOrds = null;

    }
    public Line(double[] x, double[] y) {
        _pointsList = new ArrayList<>();
        for(int i=0; i < x.length ; i++){
            Point pt = new Point(x[i],y[i]);
                _pointsList.add(pt);
            }
        _xOrds = x;
        _yOrds = y;
        }

    //Methods
    public void RemovePoint(int index){_pointsList.remove(index);}
    public void AddPoint(Point pnt){_pointsList.add(pnt);}
    private double MidpointIntegral(){
        //Returns integral of a line using midpoint rectangular integration
        double area = 0;
      for(int i=0; i<_pointsList.size()-1;i++){
          double tmpHeight = (_pointsList.get(i).getY()+_pointsList.get(i+1).getY())/2;
          double tmpWidth = _pointsList.get(i+1).getX()-_pointsList.get(i).getX();
          area += tmpHeight*tmpWidth;
      }
      return area;
    }

}
