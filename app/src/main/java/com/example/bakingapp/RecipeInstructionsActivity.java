package com.example.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


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
            fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getDescription());
            if (RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl()!=""){
                Bundle bundle= new Bundle();
                bundle.putString("video-url",RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl());
                Fragment videoFragment= new VideoFragment();
                videoFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.video_container,videoFragment).commit();

            }

            fragmentManager.beginTransaction().add(R.id.description_text_container,fragment).commit();
        }
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }




    }
}
