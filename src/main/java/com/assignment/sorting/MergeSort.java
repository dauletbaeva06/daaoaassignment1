package com.assignment.sorting;

import com.assignment.metrics.Metrics;

public class MergeSort {

    private final Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, 0);
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right, int depth) {
        if (left >= right) return;

        metrics.updateDepth(depth);

        int mid = left + (right - left) / 2;

        mergeSort(arr, buffer, left, mid, depth + 1);
        mergeSort(arr, buffer, mid + 1, right, depth + 1);

        merge(arr, buffer, left, mid, right);
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.addComparison();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int p = left; p <= right; p++) {
            arr[p] = buffer[p];
        }
    }
}
