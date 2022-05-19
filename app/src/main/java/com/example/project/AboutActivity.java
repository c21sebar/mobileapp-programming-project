package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    TextView textView;
    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.about_activity);
        super.onCreate(savedInstanceState);

        textView = findViewById(R.id.aboutText);
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
