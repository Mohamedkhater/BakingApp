package com.example.bakingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainGridViewAdapter extends BaseAdapter {
    private ArrayList<RecipeCard> recipeCardList;
    private Context mcontext;
    public MainGridViewAdapter (Context context,ArrayList<RecipeCard>recipeList){
        this.mcontext=context;
        recipeCardList =recipeList;
    }
    @Override
    public int getCount() {
        return recipeCardList.size();
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
        if(convertView==null){
            textView = new TextView(mcontext);
            textView.setWidth(200);
            textView.setHeight(150);
            textView.setTextSize(20);

        }
        else{
            textView=(TextView)convertView;
        }
        textView.setText(recipeCardList.get(position).getRecipeName());
        return textView;
    }
}
