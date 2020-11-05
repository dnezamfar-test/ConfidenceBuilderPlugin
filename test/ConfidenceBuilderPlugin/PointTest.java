package ConfidenceBuilderPlugin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point pt0_0 = new Point(0,0);
    Point pt1_1 = new Point(1,1);
    Point pt2_4 = new Point(2,4);

    @Test
    void perpendicularDistanceShouldCalcCorrect() {
        assertEquals(Point.PerpendicularDistance(pt0_0,pt2_4, pt1_1), 0.4472135954999579);
    }
}