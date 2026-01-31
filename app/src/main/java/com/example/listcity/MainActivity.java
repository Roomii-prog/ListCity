package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Step 9: Create references for ListView, ArrayAdapter, and ArrayList
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 10a: Find reference to ListView
        cityList = findViewById(R.id.city_list);

        // Step 10b: Create a string array of cities
        String[] cities = {"Edmonton", "Vancouver", "Toronto", "Calgary",
                "Montreal", "Ottawa", "Winnipeg", "Hamilton"};

        // Step 10c: Create ArrayList and assign to dataList
        dataList = new ArrayList<>();

        // Step 10d: Add cities to dataList
        dataList.addAll(Arrays.asList(cities));

        // Step 10e: Link content.xml to dataList using ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        // Step 10f: Connect ListView to ArrayAdapter
        cityList.setAdapter(cityAdapter);
    }
}