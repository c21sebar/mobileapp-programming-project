package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
    private final String JSON_FILE = "lakes.json";
    //private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    List<Lake> listLake = new ArrayList<>();
    ItemAdapter itemAdapter = new ItemAdapter(listLake, this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void sortByID(){
        Collections.sort(listLake, new Comparator<Lake>() {
            @Override
            public int compare(Lake lake1, Lake lake2) {
                return lake1.ID.compareToIgnoreCase(lake2.ID);
            }
        });
       Log.d("menu","Option 1");
       Collections.reverse(listLake);
       itemAdapter.notifyDataSetChanged();
    }
    public void sortByAreal(){
        Collections.sort(listLake, new Comparator<Lake>() {
            @Override
            public int compare(Lake lake1, Lake lake2) {
                return lake1.size - lake2.size;
            }
        });
        Log.d("menu","Option 1");
        Collections.reverse(listLake);
        itemAdapter.notifyDataSetChanged();
    }

    public void showAboutPage(){

        Log.d("menu","Option 2");
        Intent intent = new Intent(this, AboutActivity.class);

        this.startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sortByHigestID) {
            Log.d("menu","Sorting by highest ID");
            sortByID();
            return true;
        }

        if (id == R.id.showAboutPage) {
            Log.d("menu","Opens about page");
            showAboutPage();
            return true;
        }
        if (id == R.id.biggestAreal){
            sortByAreal();
            Log.d("menu","Sort by highest Areal");

        }

        return super.onOptionsItemSelected(item);
    }

}
