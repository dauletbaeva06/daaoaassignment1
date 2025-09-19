package com.assignment.geometry;

import com.assignment.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    @Test
    void testSimplePoints() {
        Metrics metrics = new Metrics();
        ClosestPair cp = new ClosestPair(metrics);

        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 7),
                new ClosestPair.Point(1, 1)
        };

        double result = cp.closest(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testTwoPoints() {
        Metrics metrics = new Metrics();
        ClosestPair cp = new ClosestPair(metrics);

        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(5, 0)
        };

        double result = cp.closest(points);
        assertEquals(5.0, result, 1e-6);
    }
}
