package com.assignment.cli;

import com.assignment.metrics.Metrics;
import com.assignment.metrics.CsvWriter;
import com.assignment.sorting.MergeSort;
import com.assignment.sorting.QuickSort;
import com.assignment.select.DeterministicSelect;
import com.assignment.geometry.ClosestPair;

import java.util.Random;

public class Runner {
    public static void main(String[] args) throws Exception {
        Metrics metrics = new Metrics();
        Random rnd = new Random();

        try (CsvWriter csv = new CsvWriter("results.csv")) {

            // MergeSort
            for (int trial = 1; trial <= 5; trial++) {
                int n = 1000;
                int[] arr = rnd.ints(n, 0, 10000).toArray();

                MergeSort mergeSort = new MergeSort(metrics);
                metrics.reset();
                long start = System.nanoTime();
                mergeSort.sort(arr);
                long end = System.nanoTime();

                csv.writeRow("MergeSort", n, trial, (end - start) / 1_000_000, metrics);
            }

            // QuickSort
            for (int trial = 1; trial <= 5; trial++) {
                int n = 1000;
                int[] arr = rnd.ints(n, 0, 10000).toArray();

                QuickSort quickSort = new QuickSort(metrics);
                metrics.reset();
                long start = System.nanoTime();
                quickSort.sort(arr);
                long end = System.nanoTime();

                csv.writeRow("QuickSort", n, trial, (end - start) / 1_000_000, metrics);
            }

            // Deterministic Select
            for (int trial = 1; trial <= 5; trial++) {
                int n = 1000;
                int[] arr = rnd.ints(n, 0, 10000).toArray();

                DeterministicSelect select = new DeterministicSelect(metrics);
                metrics.reset();
                long start = System.nanoTime();
                int kth = select.select(arr.clone(), n / 2);
                long end = System.nanoTime();

                csv.writeRow("DeterministicSelect", n, trial, (end - start) / 1_000_000, metrics);
            }

            // Closest Pair
            for (int trial = 1; trial <= 5; trial++) {
                int n = 1000;
                ClosestPair.Point[] points = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    points[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
                }

                ClosestPair cp = new ClosestPair(metrics);
                metrics.reset();
                long start = System.nanoTime();
                double dist = cp.closest(points);
                long end = System.nanoTime();

                csv.writeRow("ClosestPair", n, trial, (end - start) / 1_000_000, metrics);
            }
        }

        System.out.println("Results written to results.csv ✅");
    }
}
