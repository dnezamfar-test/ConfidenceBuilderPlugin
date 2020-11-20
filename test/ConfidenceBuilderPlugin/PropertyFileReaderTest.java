package ConfidenceBuilderPlugin;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFileReaderTest {
    String currentDirectory = System.getProperty("user.dir");
    String propertiesPath = currentDirectory + "\\ConfidenceBuilder.props";
    Properties propertiesTest = PropertyFileReader.Read(propertiesPath);
    ArrayList<Double> xOrds = new ArrayList<>(Arrays.asList(1 - 0.5, 1 - 0.8, 1 - 0.9, 1 - 0.95, 1 - 0.98, 1 - 0.99, 1 - 0.995, 1 - 0.998));
    ArrayList<Double> cI_Vals = new ArrayList<>(Arrays.asList(.975, .025));
    private double _binStartWeight = 0.000000000000000;
    private ArrayList<Double> _binWeights = new ArrayList<>(Arrays.asList(0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1));
    private double _binEndWeights = 0.000000000000000;


    @Test
    void PropertyFileReaderReturnsCorrectSimulationName() { assertEquals("12-Stochastic", propertiesTest.getSimulationName()); }

    @Test
    void PropertyFileReaderReturnsCorrectName() {
        assertEquals(propertiesTest.getSimulationName(), "12-Stochastic");
    }
    @Test
    void PropertyFileReaderReturnsCorrectXOrds() {
        assertArrayEquals(propertiesTest.getXOrds().toArray(), xOrds.toArray());
    }

    @Test
    void PropertyFileReaderReturnsCorrectCIVals() {
        assertArrayEquals(propertiesTest.getCI_Values().toArray(), cI_Vals.toArray());
    }

    @Test
    void PropertyFileReaderReturnsCorrectBinStaringWeight(){
        assertEquals(_binStartWeight,propertiesTest.getBinStartWeight());
    }
    @Test
    void PropertyFileReaderReturnsCorrectWeights(){
        assertArrayEquals(_binWeights.toArray(), propertiesTest.getBinWeights().toArray());
    }
    @Test
    void PropertyFileReaderReturnsCorrectBinEndWeight(){
        assertEquals(_binEndWeights, propertiesTest.getBinEndWeights());
    }
}

