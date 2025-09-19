package com.assignment.sorting;

import com.assignment.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, 0);
    }

    private void quickSort(int[] arr, int left, int right, int depth) {
        if (left >= right) return;

        metrics.updateDepth(depth);

        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];

        // Partition
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) {
                metrics.addComparison();
                i++;
            }
            while (arr[j] > pivot) {
                metrics.addComparison();
                j--;
            }
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        // recurse smaller side first
        if (j - left < right - i) {
            if (left < j) quickSort(arr, left, j, depth + 1);
            if (i < right) quickSort(arr, i, right, depth + 1);
        } else {
            if (i < right) quickSort(arr, i, right, depth + 1);
            if (left < j) quickSort(arr, left, j, depth + 1);
        }
    }
}
