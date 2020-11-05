package ConfidenceBuilderPlugin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point pt0_0 = new Point(0,0);
    Point pt1_1 = new Point(1,1);
    Point pt2_4 = new Point(2,4);
    Point pt100_0 = new Point(100,0);
    Point pt50_neg100 = new Point(50,-100);

    @Test
    void perpendicularDistanceShouldCalcCorrectOnSlopeLine() {
        assertTrue(Math.abs(Point.PerpendicularDistance(pt0_0,pt2_4, pt1_1)- 0.4472135954999579) < 0.0001);
    }
    @Test
    void perpendicularDistanceShouldCalcCorrectOnHorizontalLine() {
        assertEquals(Point.PerpendicularDistance(pt0_0, pt100_0, pt1_1), 1);
    }
    @Test
    void perpendicularDistanceShouldCalcCorrectInNegativeSpace() {
        assertEquals(Point.PerpendicularDistance(pt0_0, pt100_0, pt50_neg100), 100);
    }
}