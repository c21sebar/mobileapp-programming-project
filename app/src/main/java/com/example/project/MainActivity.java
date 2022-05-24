package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=c21sebar";
    List<Lake> listLake = new ArrayList<>();


    ItemAdapter itemAdapter = new ItemAdapter(listLake, this);
    RecyclerView recyclerView;
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new JsonTask(this).execute(JSON_URL); //för url
        //new JsonFile(this, this).execute(JSON_FILE); //För lokalt

        myPreferenceRef = getSharedPreferences("SharedPreference", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();
    }
    void readSort(){

        String read = myPreferenceRef.getString("Sort","0");
        Log.d("sort","read: " + read);
        switch (read){
            case "ID": {
                sortByID();
                itemAdapter.notifyDataSetChanged();
                Log.d("sort","ID sortering laddad");
                break;
            }
            case "Areal":{
                sortByAreal();
                itemAdapter.notifyDataSetChanged();
                Log.d("sort","Areal sortering laddad");
                break;

            }
            case "Djup":{
                sortByDjup();
                itemAdapter.notifyDataSetChanged();
                Log.d("sort","Djup sortering laddad");
                break;
            }
            case "Skaraborg":{
                sortBySkaraborg();
                itemAdapter.notifyDataSetChanged();
                Log.d("sort","Skaraborg sortering laddad");
                break;

            }
            default:{
                Log.d("sort","Ingen sortering laddad");
                noFilter();
                itemAdapter.notifyDataSetChanged();
            }
        }

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
        Log.d("sort","Json laddad");
        readSort();


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
       Log.d("menu","Sortera efter Max ID");
       Collections.reverse(listLake);
       itemAdapter.notifyDataSetChanged();
        myPreferenceEditor.putString("Sort", "ID");
        myPreferenceEditor.apply();
    }
    public void sortByAreal(){
        Collections.sort(listLake, new Comparator<Lake>() {
            @Override
            public int compare(Lake lake1, Lake lake2) {
                return lake1.size - lake2.size;
            }
        });
        Log.d("menu","Sortera efter Areal");
        Collections.reverse(listLake);
        itemAdapter.notifyDataSetChanged();
        myPreferenceEditor.putString("Sort", "Areal");
        myPreferenceEditor.apply();
    }
    public void sortByDjup(){
        Collections.sort(listLake, new Comparator<Lake>() {
            @Override
            public int compare(Lake lake1, Lake lake2) {
                return lake1.cost - lake2.cost;

            }
        });
        Log.d("menu","Sortera efter max djup");
        Collections.reverse(listLake);
        itemAdapter.notifyDataSetChanged();
        myPreferenceEditor.putString("Sort", "Djup");
        myPreferenceEditor.apply();
    }
    public void sortBySkaraborg(){
        Collections.sort(listLake, new Comparator<Lake>() {
            @Override
            public int compare(Lake lake1, Lake lake2) {
                if (lake1.location.contains("Skaraborg") && !lake2.location.contains("Skaraborg")) {
                    return 1;
                }else if (!lake1.location.contains("Skaraborg") && lake2.location.contains("Skaraborg")) {
                    return -1;
                }
                return 0;

            }
        });
        Log.d("menu","Sortera efter Skaraborg");
        Collections.reverse(listLake);
        itemAdapter.notifyDataSetChanged();
        myPreferenceEditor.putString("Sort", "Skaraborg");
        myPreferenceEditor.apply();
    }


    public void showAboutPage(){

        Log.d("menu","Öppna about page");
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
        if (id == R.id.maxDjup){
            Log.d("menu","Sort by max djup");
            sortByDjup();
        }
        if (id == R.id.skaraborg){
            Log.d("menu","Sort by Skaraborg");
            sortBySkaraborg();
        }
        if(id==R.id.FilterSkaraborg){
            filterBySkaraborg();
        }
        if(id==R.id.FilterArea){
            filterByArea();
        }
        if(id==R.id.FilterDjup){
            filterByDjup();
        }
        if(id==R.id.NoFilter){
            noFilter();
        }

        return super.onOptionsItemSelected(item);
    }
    void noFilter(){
        itemAdapter.setLakeList(listLake);
        itemAdapter.notifyDataSetChanged();
    }
    void filterBySkaraborg(){
        List<Lake> filterListLake = new ArrayList<>();
        for (int i =0; i<listLake.size(); i++){
            if ("Skaraborg".equalsIgnoreCase(listLake.get(i).getLocation())){
                filterListLake.add(listLake.get(i));
            }
        }
        itemAdapter.setLakeList(filterListLake);
        itemAdapter.notifyDataSetChanged();

    }
    void filterByArea(){
        List<Lake> filterListLake = new ArrayList<>();
        for (int i =0; i<listLake.size(); i++){
            if (100<(listLake.get(i).getSize())){
                filterListLake.add(listLake.get(i));
            }
        }
        itemAdapter.setLakeList(filterListLake);
        itemAdapter.notifyDataSetChanged();

    }
    void filterByDjup(){
        List<Lake> filterListLake = new ArrayList<>();
        for (int i =0; i<listLake.size(); i++){
            if (20 >= (listLake.get(i).getCost())){
                filterListLake.add(listLake.get(i));
            }
        }
        itemAdapter.setLakeList(filterListLake);
        itemAdapter.notifyDataSetChanged();

    }

}
