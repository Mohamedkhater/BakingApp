package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    Context mcontext;
    public static final String RECIPE_TEXT="recipe-name";
    public static RecipeCard fragmentCard;

    ArrayList<RecipeCard>mrecipes;
    public RecipeListAdapter(Context context, ArrayList<RecipeCard>recipes){
        this.mcontext=context;
        this.mrecipes=recipes;
    }
    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,parent,false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {

        String thumbUrl=mrecipes.get(position).getImagePath();
        Picasso.with(mcontext).load(thumbUrl).error(mcontext.getDrawable(R.drawable.ic_baking)).into(holder.recipeThumbnail);

        if (thumbUrl==null)
            holder.recipeThumbnail.setImageResource(R.drawable.photo);


        holder.recipeName.setText(mrecipes.get(position).getRecipeName());

           //holder.recipeThumbnail.setImageResource(R.drawable.ic_baking);

    }

    @Override
    public int getItemCount() {
        return mrecipes.size();
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder{
        ImageView recipeThumbnail;
        TextView recipeName;
        final RecyclerView.ViewHolder holder=this;



        public RecipeViewHolder( View itemView) {
            super(itemView);
            recipeThumbnail=itemView.findViewById(R.id.recipe_thumbnail);
            recipeName= itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(mcontext,RecipeDetails.class);
                    int position=holder.getAdapterPosition();

                    intent.putExtra(RECIPE_TEXT, mrecipes.get(position));
                    fragmentCard=mrecipes.get(position);
                    v.getContext().startActivity(intent);
                }
            });

        }


    }
}
