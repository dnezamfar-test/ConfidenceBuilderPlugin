import java.util.ArrayList;
import java.util.List;

public class Properties { 
    //fields
    private final String _simulationName;
    private final ArrayList<Double> _xOrds;
    private final ArrayList<Double> _confidenceLimitsValues;
    private final double _binStartWeight;
    private final ArrayList<Double> _binWeights;
    private final double _binEndWeights;

    //constructor
    public Properties( String _simulationName, ArrayList<Double> _xOrds, ArrayList<Double> confidenceLimitsValues, double binStartWeight, ArrayList<Double> binWeights, double binEndWeights) {
        this. _simulationName = _simulationName;
        this. _xOrds = _xOrds;
        this. _confidenceLimitsValues = confidenceLimitsValues;
        this._binStartWeight = binStartWeight;
        this._binEndWeights = binEndWeights;
        this._binWeights = binWeights;
    }
    //getters
    public String getSimulationName() {
        return _simulationName;
    }
    public ArrayList<Double> getXOrds() {
        return _xOrds;
    }
    public ArrayList<Double> getCI_Values() {
        return _confidenceLimitsValues;
    }
    public double getBinStartWeight() { return _binStartWeight; }
    public List<Double> getBinWeights() { return _binWeights; }
    public double getBinEndWeights() { return _binEndWeights; }
    //methods
}

