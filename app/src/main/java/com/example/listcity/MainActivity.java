package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityButton;
    Button deleteCityButton;
    int selectedPosition = -1; // Track selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find references
        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        // Create initial data
        String[] cities = {"Edmonton", "Vancouver"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Set up adapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Handle item selection (for delete)
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Toast.makeText(MainActivity.this,
                        "Selected: " + dataList.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Add City Button
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCityDialog();
            }
        });

        // Delete City Button
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
                    String cityToDelete = dataList.get(selectedPosition);
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                    Toast.makeText(MainActivity.this,
                            cityToDelete + " deleted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Please select a city first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showAddCityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add City");

        // Set up the input
        final EditText input = new EditText(this);
        input.setHint("Enter city name");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            String cityName = input.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        cityName + " added",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "City name cannot be empty",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}