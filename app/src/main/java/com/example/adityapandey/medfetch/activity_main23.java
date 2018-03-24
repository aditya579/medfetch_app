package com.example.adityapandey.medfetch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ADITYA PANDEY on 15-12-2017.
 */

public class activity_main23 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        final Button bttn = findViewById(R.id.button3);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity_main23.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        final Button bttn1 = findViewById(R.id.button4);
        bttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity_main23.this, activity_main22.class);
                startActivity(intent1);
            }
        });
    }
}
