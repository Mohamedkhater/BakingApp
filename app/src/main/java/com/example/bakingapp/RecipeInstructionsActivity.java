package com.example.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class RecipeInstructionsActivity extends AppCompatActivity {
    Button mNextButton;
    Button mPrevButton;
    Fragment videoFragment;
    DescriptionFragment fragment;
    Bundle bundle;
    int position;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
        mNextButton=findViewById(R.id.button_next);
        mPrevButton=findViewById(R.id.button_prev);
        Intent intent = getIntent();

        position=intent.getIntExtra("position",0);
        bundle= new Bundle();
        if (savedInstanceState==null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragment= new DescriptionFragment();
            fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getDescription());
            if (!RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getThumbnailUrl().equals(""))
                bundle.putString("image-path",RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getThumbnailUrl());
            if (!RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl().equals("")){

                bundle.putString("video-url",RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl());
                videoFragment= new VideoFragment();
                videoFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.video_container,videoFragment).commit();

            }

            fragmentManager.beginTransaction().add(R.id.description_text_container,fragment).commit();
        }
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>0&& position<RecipeListAdapter.fragmentCard.getRecipeSteps().size()){
                    position--;
                    if (position==0)
                        v.setVisibility(View.INVISIBLE);



                       fragment=new DescriptionFragment();
                   fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getDescription());


                   // if (!RecipeListAdapter.fragmentCard.getRecipeSteps().get(mposition).getVideoUrl().equals("")){
                       bundle.putString("video-url",RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl());
                           videoFragment=new VideoFragment();
                       videoFragment.setArguments(bundle);
                       FragmentManager fragmentManager=getSupportFragmentManager();


                       fragmentManager.beginTransaction().replace(R.id.video_container,videoFragment).commit();
                       fragmentManager.beginTransaction().replace(R.id.description_text_container,fragment).commit();
                    if(position==RecipeListAdapter.fragmentCard.getRecipeSteps().size()-2)
                        mNextButton.setVisibility(View.VISIBLE);




                }
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>=0&& position<RecipeListAdapter.fragmentCard.getRecipeSteps().size()-1){
                    position++;

                    if(position==RecipeListAdapter.fragmentCard.getRecipeSteps().size()-1)
                        v.setVisibility(View.INVISIBLE);

                    fragment=new DescriptionFragment();
                    fragment.setDescription(RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getDescription());


                    // if (!RecipeListAdapter.fragmentCard.getRecipeSteps().get(mposition).getVideoUrl().equals("")){
                    bundle.putString("video-url",RecipeListAdapter.fragmentCard.getRecipeSteps().get(position).getVideoUrl());
                    videoFragment=new VideoFragment();
                    videoFragment.setArguments(bundle);
                    FragmentManager fragmentManager=getSupportFragmentManager();


                    fragmentManager.beginTransaction().replace(R.id.video_container,videoFragment).commit();
                    fragmentManager.beginTransaction().replace(R.id.description_text_container,fragment).commit();
                    if(position>0)
                        mPrevButton.setVisibility(View.VISIBLE);




                }

            }
        });
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }




    }
}
