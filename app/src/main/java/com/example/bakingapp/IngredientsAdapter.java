package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private ArrayList<RecipeCard.RecipeIngredients>mIngredients;
    private Context context;
    public IngredientsAdapter(Context context, ArrayList<RecipeCard.RecipeIngredients> ingredients){
        this.context=context;
        this.mIngredients=ingredients;
    }

    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ingredient_item,parent,false);

        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder( IngredientsAdapter.IngredientsViewHolder holder, int position) {
        CharSequence ingredientText=mIngredients.get(position).getQuantity()+" "+mIngredients.get(position).getMeasure()+" "+mIngredients.get(position).getIngredient();
        holder.ingredients_tv.setText(ingredientText);


    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }
    class IngredientsViewHolder extends RecyclerView.ViewHolder{
        TextView ingredients_tv;

        public IngredientsViewHolder( View itemView) {
            super(itemView);
            ingredients_tv=itemView.findViewById(R.id.ingredient_name);

        }
    }
}
