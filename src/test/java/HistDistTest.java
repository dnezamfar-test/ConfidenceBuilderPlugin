import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HistDistTest {
    HistDist myHD = new HistDist(10, 0, 10);
    HistDist _dist = new HistDist(10, 0, 10);

    //Testing addObservation --
    @Test
    void addObservationIncreasesBinCountWhenItsLowerThanMin() {
        myHD.addObservation(-1.00);
        assertEquals(11, myHD.getBinCount());
    }

    @Test
    void addObservationDecreasesMinWhenLowerThanMin() {
        myHD.addObservation(-1.00);
        assertEquals(-1.00, myHD.getMin());
    }

    @Test
    void addObservationIncreasesBinCountWhenItsHigherThanMax() {
        myHD.addObservation(11.00);
        assertEquals(11, myHD.getBinCount());
    }

    @Test
    void addObservationIncreasesMaxWhenHigherThanMax() {
        myHD.addObservation(11.00);
        assertEquals(11.00, myHD.getMax());
    }

    @Test
    void addObservationsAddsObservationToExpectedBin() {
        myHD.addObservation(5.00);
        double expected = 1.00;
        double actual = myHD.getBins()[5];
        assertEquals(expected, actual);
    }

    @Test
    void addObservationDoesntImpactOtherBins() {
        myHD.addObservation(5.00);
        double expected = 0.00;
        double actual = myHD.getBins()[4];
        assertEquals(expected, actual);
    }

    //Testing recomputeMean
    @Test
    void recomputeMeanComputesAccurately() {
        myHD.addObservation(5.00);
        double actual = myHD.getMean();
        assertEquals(5.00, actual);
    }

    @Test
    void invCDF() {
    }

/*    @Test
    void getCDF() {
        double actual = _dist.getCDF(1);
        assertEquals(.1, actual);
    }*/

/*    @Test
    void getPDF() {
        double actual = _dist.getPDF(0);
        assertEquals(.1, actual);
    }
 */

    @Test
    void testForConvergence() {
    }

    @Test
    void estimateRemainingIterationsForConvergence() {
    }

/*    @Test
    void getNumObs() {
        assertEquals(10, _dist.getNumObs());
    }*/

    @Test
    void getMax() {
        assertEquals(10, _dist.getMax());
    }

    @Test
    void getMin() {
        assertEquals(0, _dist.getMin());
    }

    @Test
    void getConverged() {
        // need test data?
    }

    @Test
    void getConvergedIteration() {
        // need test data?
    }

/*    @Test
    void addObservationOverMax() {
        HistDist dist = new HistDist(5, 0, 5);
        for (int i = 0; i < 7; i++) {
            dist.addObservation(i);
        }
        int[] expected = {1, 1, 1, 1, 1, 1, 1};
        int[] actual = dist.getBins();
        assertArrayEquals(expected, actual);
    }

    @Test
    void addObservationUnderMin() {
        HistDist dist = new HistDist(5, 0, 5);
        for (int i = -1; i < 6; i++) {
            dist.addObservation(i);
        }
        int[] expected = {1, 1, 1, 1, 1, 1, 1};
        int[] actual = dist.getBins();
        assertArrayEquals(expected, actual);
    }*/

  /*  @Test
    void addObservationUnderMin_Cruel() {
        HistDist dist = new HistDist(5, 0, 5);
        for (int i = -1; i < 6; i++) {
            if (i == 5) {
                dist.addObservation(6);
            } else {
                dist.addObservation(i);
            }

        }
        int[] expected = {1, 1, 1, 1, 1, 1, 0, 1};
        int[] actual = dist.getBins();
        assertArrayEquals(expected, actual);
        //  Arrays.equals(actual, expected);
    }*/

    @Test
    void addObservations() {
    }
}

