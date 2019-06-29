package com.example.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipeCard implements  Parcelable{
    private String id;
    private String recipeName;
    private ArrayList<ArrayList<String>> recipeIngredients;
    private ArrayList<RecipeStep> recipeSteps;


    protected RecipeCard(Parcel in) {
        id = in.readString();
        recipeName = in.readString();
        recipeSteps = in.createTypedArrayList(RecipeStep.CREATOR);
    }

    public static final Creator<RecipeCard> CREATOR = new Creator<RecipeCard>() {
        @Override
        public RecipeCard createFromParcel(Parcel in) {
            return new RecipeCard(in);
        }

        @Override
        public RecipeCard[] newArray(int size) {
            return new RecipeCard[size];
        }
    };

    public ArrayList<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(ArrayList<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public RecipeCard(){

    }
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public ArrayList<ArrayList<String>> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<ArrayList<String>> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }





    public RecipeCard(String id,String name ,ArrayList<ArrayList<String>> ingredinets,ArrayList<RecipeStep>steps){
        this.id=id;
        this.recipeName=name;
        this.recipeIngredients=ingredinets;
        this.recipeSteps=steps;



    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(recipeName);
        dest.writeTypedList(recipeSteps);
    }



    static class RecipeStep implements Parcelable{
        protected RecipeStep(Parcel in) {
            recipeStepId = in.readString();
            shortDescription = in.readString();
            description = in.readString();
            videoUrl = in.readString();
            thumbnailUrl = in.readString();
        }

        public static final Creator<RecipeStep> CREATOR = new Creator<RecipeStep>() {
            @Override
            public RecipeStep createFromParcel(Parcel in) {
                return new RecipeStep(in);
            }

            @Override
            public RecipeStep[] newArray(int size) {
                return new RecipeStep[size];
            }
        };

        public String getRecipeStepId() {
            return recipeStepId;
        }

        public void setRecipeStepId(String recipeStepId) {
            this.recipeStepId = recipeStepId;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        private String recipeStepId;
        private String shortDescription;
        private String description;
        private String videoUrl;
        private String thumbnailUrl;
        public RecipeStep(String id, String shortDescription,String description,String videoUrl,String thumbnailUrl){
            this.recipeStepId=id;
            this.shortDescription=shortDescription;
            this.description=description;
            this.videoUrl=videoUrl;
            this.thumbnailUrl=thumbnailUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(recipeStepId);
            dest.writeString(shortDescription);
            dest.writeString(description);
            dest.writeString(videoUrl);
            dest.writeString(thumbnailUrl);
        }
        public RecipeStep(){

        }
    }
}
