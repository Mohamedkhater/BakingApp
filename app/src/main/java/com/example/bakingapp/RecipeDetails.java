package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


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
        setTitle(RecipeListAdapter.fragmentCard.getRecipeName());

        if (findViewById(R.id.tablet_linear_layout)!=null){
            twoPaneMode=true;
            if (savedInstanceState==null){
                FragmentManager ft= getSupportFragmentManager();
                DescriptionFragment fragment= new DescriptionFragment();
                VideoFragment videoFragment=new VideoFragment();
                fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(0).getDescription());
                videoFragment.setVideoUrl(RecipeListAdapter.fragmentCard.getRecipeSteps().get(0).getVideoUrl());
                videoFragment.setImagePath(RecipeListAdapter.fragmentCard.getRecipeSteps().get(0).getThumbnailUrl());


                ft.beginTransaction().add(R.id.description_text_container,fragment).commit();
                ft.beginTransaction().add(R.id.video_container,videoFragment).commit();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ingredients,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.ingredints)
        {
           ArrayList<RecipeCard.RecipeIngredients> ingredients= RecipeListAdapter.fragmentCard.getRecipeIngredients();
           Intent intent = new Intent(this,IngredientsActivity.class);
           intent.putParcelableArrayListExtra("ingredients",ingredients);
           startActivity(intent);
        }
        return true;
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
            fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(id).getDescription());
            VideoFragment videoFragment=new VideoFragment();
            videoFragment.setVideoUrl(RecipeListAdapter.fragmentCard.getRecipeSteps().get(id).getVideoUrl());
            String imagePath=RecipeListAdapter.fragmentCard.getRecipeSteps().get(id).getThumbnailUrl();
            if (imagePath!=null)
                videoFragment.setImagePath(imagePath);

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
