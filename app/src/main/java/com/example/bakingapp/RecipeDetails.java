package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
public class RecipeDetails extends AppCompatActivity implements MasterListFragment.getrecipeIdFromBundle{
    ArrayList<RecipeCard.RecipeIngredients> ingredients;
    ArrayList<RecipeCard.RecipeStep> stps;
    public boolean twoPaneMode=false;
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        if (findViewById(R.id.tablet_linear_layout)!=null){
            twoPaneMode=true;
            if (savedInstanceState==null){
                FragmentManager ft= getSupportFragmentManager();
                DescriptionFragment fragment= new DescriptionFragment();
                fragment.setDescription(RetroHelper.getFragmentCard().getRecipeSteps().get(0).getDescription());

                ft.beginTransaction().add(R.id.description_text_container,fragment).commit();
            }

        }

    }



    @Override
    public void onIdAssigned(int id) {
        if (twoPaneMode)
        {
            DescriptionFragment fragment= new DescriptionFragment();
            FragmentManager mg= getSupportFragmentManager();
            fragment.setDescription(RetroHelper.getFragmentCard().getRecipeSteps().get(id).getDescription());

            mg.beginTransaction().replace(R.id.description_text_container,fragment).commit();

        }
        else{
            Intent intent= new Intent(this,RecipeInstructionsActivity.class);
            intent.putExtra("position",id);
            startActivity(intent);
        }

    }
}
