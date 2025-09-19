package com.assignment.sorting;

import com.assignment.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void testSmallArray() {
        Metrics metrics = new Metrics();
        QuickSort quickSort = new QuickSort(metrics);

        int[] arr = {4, 1, 3, 9, 7};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        quickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Metrics metrics = new Metrics();
        QuickSort quickSort = new QuickSort(metrics);

        int[] arr = new Random().ints(1000, 0, 10000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        quickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}
