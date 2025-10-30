package com.example.midterm_minilik_meja;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editNumber;
    Button btnGenerate, btnHistory;
    ListView listView;
    ArrayList<String> rows = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("timesTable");

        editNumber = findViewById(R.id.editNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        listView = findViewById(R.id.listView);

        rows.addAll(DataStore.getCurrentRows());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rows);
        listView.setAdapter(adapter);

        btnGenerate.setOnClickListener(v -> {
            String s = editNumber.getText().toString().trim();
            if (TextUtils.isEmpty(s)) {
                editNumber.setError("Enter a number");
                return;
            }

            int n = Integer.parseInt(s);

            DataStore.generateTable(n);
            rows.clear();
            rows.addAll(DataStore.getCurrentRows());
            adapter.notifyDataSetChanged();
        });

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String row = rows.get(position);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete row?")
                    .setMessage("Remove: " + row + " ?")
                    .setPositiveButton("Delete", (d, w) -> {
                        rows.remove(position);
                        DataStore.getCurrentRows().remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Deleted: " + row, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_clear_all) {
            new AlertDialog.Builder(this)
                    .setTitle("Clear all data?")
                    .setPositiveButton("Yes", (d, w) -> {
                        DataStore.clearAll();
                        rows.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "All rows cleared", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
