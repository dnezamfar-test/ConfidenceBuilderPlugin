import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistDistTest {
    HistDist myHD = new HistDist(10,0,10);

    @Test
    void addObservation() {
        myHD.addObservation( 9.9999);

    }

    @Test
    void addObservations() {
    }

    @Test
    void invCDF() {
    }

    @Test
    void getCDF() {
    }

    @Test
    void getPDF() {
    }

    @Test
    void testForConvergence() {
    }

    @Test
    void estimateRemainingIterationsForConvergence() {
    }
}