package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SecondActivity  extends AppCompatActivity {

    TextView text;
    Button button;
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.second_activity);
        super.onCreate(savedInstanceState);

        final String name = getIntent().getExtras().getString("namn");
        final String imgURL = getIntent().getExtras().getString("imgURL");
        final String URLimg = "https://upload.wikimedia.org/wikipedia/commons/3/37/Hjortens_Udde%2C_lake_V%C3%A4nern_Sweden%2C_2003-04.jpg";
        text =findViewById(R.id.secondActivityTextView);
        text.setText(name);

        imageView = findViewById(R.id.secondActivityImageView);

        Log.d("img", imgURL);
        Log.d("img", URLimg);
        Picasso.get().load(imgURL).into(imageView);

        button = findViewById(R.id.finishButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
