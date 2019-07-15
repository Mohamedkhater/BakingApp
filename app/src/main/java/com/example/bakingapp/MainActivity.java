package com.example.bakingapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    GridView mGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridview=findViewById(R.id.grid_view_main);
        RetroHelper helper= new RetroHelper(this,mGridview);
        helper.getResponce();


    }
}
