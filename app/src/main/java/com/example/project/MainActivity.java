package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
    private final String JSON_FILE = "lakes.json";
    //private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    List<Lake> listLake = new ArrayList<>();
    ItemAdapter itemAdapter = new ItemAdapter(listLake);
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //new JsonTask(this).execute(JSON_URL); //för url
        new JsonFile(this, this).execute(JSON_FILE); //För lokalt

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", "lake lista: " + listLake.size());
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Lake>>(){}.getType();
        listLake = gson.fromJson(json, type);
        Log.d("MainActivity", "lake lista2: " + listLake.size());
        itemAdapter.setLakeList(listLake);
        itemAdapter.notifyDataSetChanged();


    }

}
