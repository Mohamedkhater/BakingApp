package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class RecipeInstructionsActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
        Intent intent = getIntent();
        int position;

        position=intent.getIntExtra("position",0);
        if (savedInstanceState==null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            DescriptionFragment fragment= new DescriptionFragment();
            fragment.setDescription(RetroHelper.getFragmentCard().getRecipeSteps().get(position).getDescription());
            fragmentManager.beginTransaction().add(R.id.description_text_container,fragment).commit();
        }



    }
}
