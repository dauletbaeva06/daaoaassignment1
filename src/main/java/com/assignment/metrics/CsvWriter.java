package com.assignment.metrics;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements AutoCloseable {
    private final CSVWriter writer;

    public CsvWriter(String fileName) throws IOException {
        this.writer = new CSVWriter(new FileWriter(fileName, true));
        // Добавляем заголовок, если файл пустой
        writer.writeNext(new String[]{"algorithm", "n", "trial", "time_ms", "comparisons", "allocations", "depth"});
        writer.flush();
    }

    public void writeRow(String algorithm, int n, int trial, long timeMs, Metrics metrics) {
        String[] row = {
                algorithm,
                String.valueOf(n),
                String.valueOf(trial),
                String.valueOf(timeMs),
                String.valueOf(metrics.getComparisons()),
                String.valueOf(metrics.getAllocations()),
                String.valueOf(metrics.getMaxDepth())
        };
        writer.writeNext(row);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to flush CSVWriter", e);
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
