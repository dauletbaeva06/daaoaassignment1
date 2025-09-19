package com.assignment.sorting;

import com.assignment.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    void testSortedArray() {
        Metrics metrics = new Metrics();
        MergeSort mergeSort = new MergeSort(metrics);

        int[] arr = {5, 2, 8, 1, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Metrics metrics = new Metrics();
        MergeSort mergeSort = new MergeSort(metrics);

        int[] arr = new Random().ints(1000, 0, 10000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}
