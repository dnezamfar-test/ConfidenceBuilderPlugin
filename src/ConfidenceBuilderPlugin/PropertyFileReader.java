package ConfidenceBuilderPlugin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PropertyFileReader {
    public static Properties Read(String _propertiesFile) {
        String _simulationName = "";
        ArrayList<Double> _xOrds = new ArrayList<>();
        ArrayList<Double> _CI_Values = new ArrayList<>();
        BufferedReader brp = null;
        try {
            brp = new BufferedReader(new FileReader(_propertiesFile));
            String[] tmp;
            String propertyLine;
            while ((propertyLine = brp.readLine()) != null) {
                tmp = propertyLine.split(",");
                if (tmp.length == 0) {
                    continue;
                }
                switch (tmp[0]) {
                    case "SimulationName" -> _simulationName = tmp[1];
                    case "XOrds" -> {
                        for (String s : tmp) {
                            if (s.equals("XOrds")) {
                                continue;
                            }
                            _xOrds.add(1 - Double.parseDouble(s));
                        }
                    }
                    case "CI_Vals" -> {
                        for (String s : tmp) {
                            if (s.equals("CI_Vals")) {
                                continue;
                            }
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
        Properties property = new Properties(_propertiesFile, _simulationName, _xOrds, _CI_Values);
        return property;
    }
}
