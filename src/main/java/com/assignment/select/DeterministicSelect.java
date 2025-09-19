package com.assignment.select;

import com.assignment.metrics.Metrics;

import java.util.Arrays;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k, 0);
    }

    private int select(int[] arr, int left, int right, int k, int depth) {
        if (left == right) return arr[left];

        metrics.updateDepth(depth);

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int rank = pivotIndex - left;
        if (k == rank) return arr[pivotIndex];
        else if (k < rank) return select(arr, left, pivotIndex - 1, k, depth + 1);
        else return select(arr, pivotIndex + 1, right, k - rank - 1, depth + 1);
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + (right - left) / 2];
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int median = arr[i + (subRight - i) / 2];
            arr[left + numMedians] = median;
            numMedians++;
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private int partition(int[] arr, int left, int right, int pivotValue) {
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivotValue) i++;
            while (arr[j] > pivotValue) j--;
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i - 1;
    }
}
