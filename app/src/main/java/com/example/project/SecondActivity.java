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


    TextView typeView;
    TextView companyView;
    TextView locationView;
    TextView categoryView;
    TextView sizeView;
    TextView costView;
    TextView wikiView;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.second_activity);
        super.onCreate(savedInstanceState);
        final String id = getIntent().getExtras().getString("ID");
        final String name = getIntent().getExtras().getString("namn");
        final String imgURL = getIntent().getExtras().getString("imgURL");
        final String type = getIntent().getExtras().getString("type");
        final String company = getIntent().getExtras().getString("company");
        final String location = getIntent().getExtras().getString("location");
        final String category = getIntent().getExtras().getString("category");
        final String size = getIntent().getExtras().getString("size");
        final String cost = getIntent().getExtras().getString("cost");
        final String wikiLink = getIntent().getExtras().getString("wikiLink");

        text =findViewById(R.id.namn);
        text.setText(name);

        typeView = findViewById(R.id.type);
        typeView.setText(type);

        companyView = findViewById(R.id.company);
        companyView.setText(company);

        locationView = findViewById(R.id.location);
        locationView.setText(location);

        categoryView = findViewById(R.id.category);
        categoryView.setText(category);

        sizeView = findViewById(R.id.size);
        sizeView.setText(size);

        costView = findViewById(R.id.cost);
        costView.setText(cost);

        wikiView = findViewById(R.id.wikiLink);
        wikiView.setText(wikiLink);

        imageView = findViewById(R.id.secondActivityImageView);
        Log.d("img", imgURL);
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
