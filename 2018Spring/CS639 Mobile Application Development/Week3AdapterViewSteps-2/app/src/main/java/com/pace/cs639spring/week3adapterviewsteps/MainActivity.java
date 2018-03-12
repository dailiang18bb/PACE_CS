package com.pace.cs639spring.week3adapterviewsteps;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        configureListView();
    }

    private void configureListView() {
        ListView listView = findViewById(android.R.id.list);

        List<ExampleObject> exampleObjects = new ArrayList<>();
        exampleObjects.add(new ExampleObject(Color.RED, "Name 1"));
        exampleObjects.add(new ExampleObject(Color.BLUE, "Name 2"));
        exampleObjects.add(new ExampleObject(Color.GRAY, "Name 3"));
        exampleObjects.add(new ExampleObject(Color.GREEN, "Name 4"));
        exampleObjects.add(new ExampleObject(Color.YELLOW, "Name 5"));

        TestAdapter adapter = new TestAdapter(this, exampleObjects);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Clicked on list row", Toast.LENGTH_LONG).show();
            }
        });
    }
}
