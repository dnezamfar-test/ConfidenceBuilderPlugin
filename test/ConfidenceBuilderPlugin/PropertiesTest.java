package ConfidenceBuilderPlugin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesTest {
    String currentDirectory = System.getProperty("user.dir");
    String propertiesPath = currentDirectory + "\\ConfidenceBuilder.props";
    Properties propertiesTest = new Properties(propertiesPath);
    ArrayList<Double> xOrds = new ArrayList<>(Arrays.asList(1-0.5, 1-0.8, 1-0.9, 1-0.95, 1-0.98, 1-0.99, 1-0.995, 1-0.998));
    ArrayList<Double> cI_Vals = new ArrayList<>(Arrays.asList(.975, .025));

    @Test
    void getSimulationNameReturnsCorrectName() {
        assertEquals(propertiesTest.getSimulationName(), "12-Stochastic");
    }

    @Test
    void getXOrdsReturnsCorrectXOrds() {
        assertArrayEquals(propertiesTest.getXOrds().toArray(), xOrds.toArray());
    }

    @Test
    void getCI_ValsReturnsCorrectCIVals() {
        assertArrayEquals(propertiesTest.getCI_Values().toArray(), cI_Vals.toArray());
    }
}