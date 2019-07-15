package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
public class RecipeDetails extends AppCompatActivity implements MasterListFragment.getrecipeIdFromBundle{
    ArrayList<RecipeCard.RecipeIngredients> ingredients;
    ArrayList<RecipeCard.RecipeStep> stps;
    public boolean twoPaneMode=false;
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        setTitle(RetroHelper.getFragmentCard().getRecipeName());

        if (findViewById(R.id.tablet_linear_layout)!=null){
            twoPaneMode=true;
            if (savedInstanceState==null){
                FragmentManager ft= getSupportFragmentManager();
                DescriptionFragment fragment= new DescriptionFragment();
                VideoFragment videoFragment=new VideoFragment();
                fragment.setDescription(RetroHelper.getFragmentCard().getRecipeSteps().get(0).getDescription());
                videoFragment.setVideoUrl(RetroHelper.getFragmentCard().getRecipeSteps().get(0).getVideoUrl());


                ft.beginTransaction().add(R.id.description_text_container,fragment).commit();
                ft.beginTransaction().add(R.id.video_container,videoFragment).commit();
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onIdAssigned(int id) {
        if (twoPaneMode)
        {
            DescriptionFragment fragment= new DescriptionFragment();
            FragmentManager mg= getSupportFragmentManager();
            fragment.setDescription(RetroHelper.getFragmentCard().getRecipeSteps().get(id).getDescription());
            VideoFragment videoFragment=new VideoFragment();
            videoFragment.setVideoUrl(RetroHelper.getFragmentCard().getRecipeSteps().get(id).getVideoUrl());

            mg.beginTransaction().replace(R.id.description_text_container,fragment).commit();
            mg.beginTransaction().replace(R.id.video_container,videoFragment).commit();

        }
        else{
            Intent intent= new Intent(this,RecipeInstructionsActivity.class);
            intent.putExtra("position",id);

            startActivity(intent);
        }

    }
}
