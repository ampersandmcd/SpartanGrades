package com.example.shayladd.spartan_grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static final String key = "com.example.shayladd.spartan_grades";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Spartan Grades");

        final Spinner dropdown = findViewById(R.id.search_by_spinner);
        final String[] search_by_items = {"Course Code", "Course Title", "Professor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, search_by_items);
        dropdown.setAdapter(adapter);

        //search button pressed
        Button search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchResults.class);
                EditText search_term = (EditText)findViewById(R.id.search_term);
                String search_term_str = search_term.getText().toString();
                String search_by = dropdown.getSelectedItem().toString();
                String[] search_bundle = new String[2];
                search_bundle[0] = search_by;
                search_bundle[1] = search_term_str;
                intent.putExtra(key, search_bundle);
                startActivity(intent);
            }
        });

    }
}
