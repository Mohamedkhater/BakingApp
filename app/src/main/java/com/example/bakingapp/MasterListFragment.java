package com.example.bakingapp;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;


public class MasterListFragment extends Fragment {
    GridView gridView;
    private getrecipeIdFromBundle mcallback;

    public interface getrecipeIdFromBundle{
         void onIdAssigned(int id);
    }
    public MasterListFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            mcallback=(getrecipeIdFromBundle)context;
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_master_list,container,false);
         gridView=view.findViewById(R.id.master_list_grid_view);
        Log.d(MasterListFragment.class.getSimpleName(),RecipeListAdapter.fragmentCard.getId());


        MasterListAdapter masterListGridviewAdapter= new MasterListAdapter(getContext(),
                    RecipeListAdapter.fragmentCard.getRecipeIngredients(),RecipeListAdapter.fragmentCard.getRecipeSteps());


            gridView.setAdapter(masterListGridviewAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   mcallback.onIdAssigned(position);
                }
            });

        return view;
    }


}
