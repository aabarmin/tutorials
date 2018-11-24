package misc;

import edu.princeton.cs.algs4.Point2D;

import java.util.function.BiFunction;

public class Point2DClient {
    private static final BiFunction<Point2D, Point2D, Double> GET_DISTANCE = (p1, p2) ->
            Math.sqrt(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2));

    public static double getDistance(Point2D[] points) {
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                distance = Math.min(distance, GET_DISTANCE.apply(
                        points[i], points[j]
                ));
            }
        }
        return distance;
    }
}
