package com.assignment.select;

import com.assignment.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeterministicSelectTest {

    @Test
    void testSelectCorrectness() {
        Metrics metrics = new Metrics();
        DeterministicSelect select = new DeterministicSelect(metrics);

        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int kth = select.select(arr.clone(), k);
            assertEquals(sorted[k], kth);
        }
    }

    @Test
    void testRandomArray() {
        Metrics metrics = new Metrics();
        DeterministicSelect select = new DeterministicSelect(metrics);

        int[] arr = new Random().ints(200, 0, 10000).toArray();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int k = arr.length / 2;
        int kth = select.select(arr.clone(), k);
        assertEquals(sorted[k], kth);
    }
}
