package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.bakingapp.RetroHelper.RECIPE_TEXT;

public class RecipeDetails extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        RecipeCard recipeCard=intent.getParcelableExtra(RECIPE_TEXT);

       // Log.i(RecipeDetails.class.getSimpleName(),recipeCard.getRecipeSteps().toString());
            setTitle(recipeCard.getRecipeName());

    }
}
