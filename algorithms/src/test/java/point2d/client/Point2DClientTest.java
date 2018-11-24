package point2d.client;

import edu.princeton.cs.algs4.Point2D;
import misc.Point2DClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DClientTest {
    @Test
    void testWithOverlappingPoints() {
        assertEquals(0, Point2DClient.getDistance(new Point2D[]{
                new Point2D(0, 0),
                new Point2D(0, 0)
        }));
    }

    @Test
    void testWithDistance() {
        Point2D[] points = new Point2D[] {
                new Point2D(0, 0),
                new Point2D(0, 2),
                new Point2D(0, 3)
        };
        assertEquals(1, Point2DClient.getDistance(points));
    }
}