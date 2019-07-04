package com.example.bakingapp;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MasterListAdapter extends BaseAdapter {
    private Context mcontext;
    private List<RecipeCard.RecipeIngredients>mIngredients;
    private List<RecipeCard.RecipeStep>msteps;

    public MasterListAdapter (Context context ,List<RecipeCard.RecipeIngredients> mIngredients, List<RecipeCard.RecipeStep>msteps){
        this.mcontext=context;
        this.mIngredients=mIngredients;
        this.msteps=msteps;
    }
    @Override
    public int getCount() {
        return msteps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
       if (convertView ==null){
           textView = new TextView(mcontext);
           textView.setWidth(100);
           textView.setGravity(Gravity.CENTER_VERTICAL);
           textView.setHeight(200);
       }
       else{
           textView=(TextView)convertView;
       }
           textView.setText(msteps.get(position).getShortDescription());

       return textView;
    }
}
