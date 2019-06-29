package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroHelper {
    public Context mcontext;
    private ArrayList<RecipeCard> mrecipes;
    GridView gridView;
    public static final String RECIPE_TEXT="recipe-name";


    public ArrayList<RecipeCard> getMrecipes() {
        return mrecipes;
    }

    public RetroHelper(Context context,GridView gridView){
        this.mcontext=context;
        this.gridView=gridView;
    }
    public  void getResponce(){


        Retrofit retrofit= new Retrofit.Builder().baseUrl(RetroInterface.JSONURL).addConverterFactory(ScalarsConverterFactory.create()).build();
        RetroInterface retroApi=retrofit.create(RetroInterface.class);

        Call<String> call=retroApi.getString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Response-string: ",response.body().toString());
                if (response.isSuccessful()){
                    if(response.body()!=null){
                        String jsonResponce=response.body().toString();
                       mrecipes = formatResponse(jsonResponce);
                       MainGridViewAdapter mainGridViewAdapter= new MainGridViewAdapter(mcontext, mrecipes);
                       gridView.setAdapter(mainGridViewAdapter);
                       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               Intent intent= new Intent(mcontext,RecipeDetails.class);
                               intent.putExtra(RECIPE_TEXT, mrecipes.get(position));
                               gridView.getContext().startActivity(intent);
                           }
                       });




                    }
                    else{
                        Log.i("EmptyResponse","returned Empty responce");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    private ArrayList<RecipeCard> formatResponse(String responce){
        try {
            JSONArray recipeArrayJson=new JSONArray(responce);
            ArrayList<RecipeCard>recipeCards= new ArrayList<>();
            for (int i=0;i< recipeArrayJson.length();i++){
                JSONObject obj=recipeArrayJson.getJSONObject(i);

                RecipeCard card= new RecipeCard();
                card.setRecipeName(obj.getString("name"));
                card.setId(obj.getString("id"));
                JSONArray ingredientsJson=obj.getJSONArray("ingredients");

              //  card.setRecipeIngredients(ingredientsJson);
                ArrayList<ArrayList<String>> ingredients=new ArrayList<ArrayList<String>>();
                for (int j=0;j<ingredientsJson.length();j++){
                    JSONObject ingredientObject=ingredientsJson.getJSONObject(j);
                    final String quantity=ingredientObject.getString("quantity");
                    final String measure=ingredientObject.getString("measure");
                    final String mainIngredient=ingredientObject.getString("ingredient");
                    ingredients.add(new ArrayList<String>(){{add(quantity);add(measure);add(mainIngredient);}});


                }
                ArrayList<RecipeCard.RecipeStep> steps = new ArrayList<>();
                JSONArray JsonSteps= obj.getJSONArray("steps");
                for (int k=0;k<JsonSteps.length();k++){
                    JSONObject stepObj=JsonSteps.getJSONObject(k);
                    String id=stepObj.optString("id");
                    String shortDescription=stepObj.optString("shortDescription");
                    String description= stepObj.optString("description");
                    String videoUrl=stepObj.optString("videoURL");
                    String thumbnail=stepObj.optString("thumbnailURL");
                    steps.add(new RecipeCard.RecipeStep(id,shortDescription,description,videoUrl,thumbnail));
                }

                card.setRecipeIngredients(ingredients);
                card.setRecipeSteps(steps);
                recipeCards.add(card);




            }
            Log.d(RetroHelper.class.getSimpleName(),recipeCards.get(2).getRecipeSteps().get(1).getDescription());

            return recipeCards;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}
