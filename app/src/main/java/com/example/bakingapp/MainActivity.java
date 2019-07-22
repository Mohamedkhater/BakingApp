package com.example.bakingapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;



public class MainActivity extends AppCompatActivity {
     RecyclerView mGridview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridview=findViewById(R.id.recipe_rv);
        RetroHelper helper= new RetroHelper(this,mGridview);
        helper.getResponce();


    }
}
