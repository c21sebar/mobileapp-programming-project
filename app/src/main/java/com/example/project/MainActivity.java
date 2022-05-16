package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    List<Lake> listLake = new ArrayList<>();
    ItemAdapter itemAdapter = new ItemAdapter(listLake);
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new JsonTask(this).execute(JSON_URL); //för url
        //new JsonFile(this, this).execute(JSON_FILE); //För lokalt

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", "berg lista: " + listLake.size());
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Lake>>(){}.getType();
        itemAdapter = gson.fromJson(json, type);
        Log.d("MainActivity", "berg lista1: " + listLake.size());
        itemAdapter.setLakeList(listLake);
        itemAdapter.notifyDataSetChanged();


    }

}
