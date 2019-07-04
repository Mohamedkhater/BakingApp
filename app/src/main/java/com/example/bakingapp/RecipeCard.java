package com.example.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipeCard implements  Parcelable{
    private String id;
    private String recipeName;
    private ArrayList<RecipeIngredients> recipeIngredients;
    private ArrayList<RecipeStep> recipeSteps;
    private String servings;
    private String imagePath;

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

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    protected RecipeCard(Parcel in) {
        id = in.readString();
        recipeName = in.readString();
        recipeIngredients = in.createTypedArrayList(RecipeIngredients.CREATOR);
        recipeSteps = in.createTypedArrayList(RecipeStep.CREATOR);
        servings=in.readString();
        imagePath=in.readString();

    }



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

    public ArrayList<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<RecipeCard.RecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }





    public RecipeCard(String id,String name ,ArrayList<RecipeIngredients> ingredinets,ArrayList<RecipeStep>steps,String servings,String imagePath){
        this.id=id;
        this.recipeName=name;
        this.recipeIngredients=ingredinets;
        this.recipeSteps=steps;
        this.servings=servings;
        this.imagePath=imagePath;



    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(recipeName);
        dest.writeTypedList(recipeIngredients);
        dest.writeTypedList(recipeSteps);
        dest.writeString(servings);
        dest.writeString(imagePath);
    }


    static class RecipeIngredients implements Parcelable {
        protected RecipeIngredients(Parcel in) {
            quantity = in.readString();
            measure = in.readString();
            ingredient = in.readString();
        }

        public static final Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
            @Override
            public RecipeIngredients createFromParcel(Parcel in) {
                return new RecipeIngredients(in);
            }

            @Override
            public RecipeIngredients[] newArray(int size) {
                return new RecipeIngredients[size];
            }
        };

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        private String quantity;
        private String measure;
        private String ingredient;
        public RecipeIngredients(String quantity, String measure,String ingredient){
            this.quantity=quantity;
            this.measure=measure;
            this.ingredient=ingredient;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(quantity);
            dest.writeString(measure);
            dest.writeString(ingredient);
        }
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
