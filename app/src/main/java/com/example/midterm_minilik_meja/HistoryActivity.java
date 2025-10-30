package com.example.midterm_minilik_meja;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.TreeSet;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History of Generated Numbers");
        setContentView(R.layout.activity_history);

        ListView listHistory = findViewById(R.id.listHistory);

        ArrayList<String> numbers = new ArrayList<>();
        for (Integer n : new TreeSet<>(DataStore.getHistoryNumbers())) {
            numbers.add(String.valueOf(n));
        }

        listHistory.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers)
        );
    }
}
