package com.example.midterm_minilik_meja;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DataStore {
    private static final List<String> currentRows = new ArrayList<>();
    private static final Set<Integer> historyNumbers = new LinkedHashSet<>();

    public static List<String> getCurrentRows() { return currentRows; }
    public static Set<Integer> getHistoryNumbers() { return historyNumbers; }

    public static void generateTable(int n) {
        currentRows.clear();
        for (int i = 1; i <= 10; i++) currentRows.add(n + " × " + i + " = " + (n * i));
        historyNumbers.add(n);
    }

    public static void clearAll() {
        currentRows.clear();
        historyNumbers.clear();
    }
}
