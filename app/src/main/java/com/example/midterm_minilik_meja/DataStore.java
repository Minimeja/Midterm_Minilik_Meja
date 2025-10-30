package com.example.midterm_minilik_meja;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DataStore {

    private static final List<String> currentRows = new ArrayList<>();
    private static final Set<Integer> historyNumbers = new LinkedHashSet<>();
    private static int lastNumber = -1;

    public static List<String> getCurrentRows() { return currentRows; }

    public static Set<Integer> getHistoryNumbers() { return historyNumbers; }

    public static void generateTable(int n) {
        currentRows.clear();
        for (int i = 1; i <= 10; i++) {
            currentRows.add(n + " × " + i + " = " + (n * i));
        }
        lastNumber = n;
        historyNumbers.add(n);
    }

    public static int getLastNumber() { return lastNumber; }

    public static void clearAll() {
        currentRows.clear();
        historyNumbers.clear();
        lastNumber = -1;
    }

    public static boolean removeHistory(int n) {
        return historyNumbers.remove(n);
    }

    public static String removeCurrentRowAt(int index) {
        if (index >= 0 && index < currentRows.size()) {
            return currentRows.remove(index);
        }
        return null;
    }
}
