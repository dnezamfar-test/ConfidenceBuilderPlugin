package ConfidenceBuilderPlugin;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Properties { 
    //fields
    String _propertiesFile;
    String _simulationName;
    ArrayList<Double> _xOrds;
    ArrayList<Double> _CI_Values;

    //constructor
    public Properties(String _propertiesFile) {
        this._propertiesFile = _propertiesFile;
        BufferedReader brp = null;
        String propertyLine;
        
        try {
            brp = new BufferedReader(new FileReader(_propertiesFile));
            String[] tmp;
            while ((propertyLine = brp.readLine()) != null) {
                tmp = propertyLine.split(",");
                if(tmp.length==0){continue;}
                switch (tmp[0]) {
                    case "SimulationName" -> _simulationName = tmp[1];


                    case "XOrds" -> {
                        _xOrds = new ArrayList<>();
                        for (String s : tmp) {
                            if (s.equals("XOrds")) {
                                continue; }
                            _xOrds.add(1 - Double.parseDouble(s)); } }


                    case "CI_Vals" -> {
                        _CI_Values = new ArrayList<>();
                        for (String s : tmp) {
                            if (s.equals("CI_Vals")) {
                                continue; }
                            _CI_Values.add(Double.parseDouble(s));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brp != null) {
                try {
                    brp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //getters
    public String getPropertiesFile() {
        return _propertiesFile;
    }

    public String getSimulationName() {
        return _simulationName;
    }

    public ArrayList<Double> getXOrds() {
        return _xOrds;
    }

    public ArrayList<Double> getCI_Values() {
        return _CI_Values;
    }
}
