package com.example.bakingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent=getIntent();
        ArrayList<RecipeCard.RecipeIngredients>ingredients=intent.getParcelableArrayListExtra("ingredients");
        RecyclerView ingredientsRecyclerView= findViewById(R.id.ingredients_rv);
        ingredientsRecyclerView.setAdapter(new IngredientsAdapter(this,ingredients));
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
