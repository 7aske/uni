package com.example.cs330_dz01_nikola_tasic_3698;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.widget.Button;

public class Student1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener((view)->{
            OpenUrlDialog dialog = new OpenUrlDialog();
            dialog.show(getSupportFragmentManager(), "OpenUrlDialog");
        });
    }
}