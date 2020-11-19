package ConfidenceBuilderPlugin;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Properties { 
    //fields
    private String _propertiesFile;
    private String _simulationName;
    private ArrayList<Double> _xOrds;
    private ArrayList<Double> _CI_Values;
    private double _binStartWeight;
    private List<Double> _binWeights;
    private double _binEndWeights;

    //constructor
    public Properties(String _propertiesFile, String _simulationName, ArrayList<Double> _xOrds, ArrayList<Double> CIvalues, double binStartWeight, List<Double> binWeights, double binEndWeights) {
        this. _propertiesFile = _propertiesFile;
        this. _simulationName = _simulationName;
        this. _xOrds = _xOrds;
        this. _CI_Values = CIvalues;
        this._binStartWeight = binStartWeight;
        this._binEndWeights = binEndWeights;
        this._binWeights = binWeights;
    }
    //getters
    public String getPropertiesFile() { return _propertiesFile; }
    public String getSimulationName() {
        return _simulationName;
    }
    public ArrayList<Double> getXOrds() {
        return _xOrds;
    }
    public ArrayList<Double> getCI_Values() {
        return _CI_Values;
    }
    public double getBinStartWeight() { return _binStartWeight; }
    public List<Double> getBinWeights() { return _binWeights; }
    public double getBinEndWeights() { return _binEndWeights; }
}
