package com.example.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BakingWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


        return new BakingWidgetFactory(getApplicationContext(),intent);
    }
    class BakingWidgetFactory implements RemoteViewsService.RemoteViewsFactory {
        private Context context;
        private int appWidgetId;
        private List<String>mcollection;
        private ArrayList<RecipeCard.RecipeIngredients>ingredients;
        public BakingWidgetFactory(Context context, Intent intent){
            this.context=context;
            this.appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);


        }

        @Override
        public void onCreate() {
            try{
                ingredients= RecipeListAdapter.fragmentCard.getRecipeIngredients();
                initData();


            }
            catch(Exception e){
                Log.w(BakingWidgetService.class.getSimpleName(),"no data in the widget");

            }


        }

        @Override
        public void onDataSetChanged() {
            initData();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.widget_item);
            CharSequence text=mcollection.get(position);
            if(ingredients!=null){
                 text=ingredients.get(position).getQuantity()+" "+ingredients.get(position)
                        .getMeasure()+" "+ingredients.get(position).getIngredient();
            }

            remoteViews.setTextViewText(R.id.widget_item_id,text);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
        private void initData(){
            mcollection=new ArrayList<>();
            mcollection.clear();
            for (int i = 1; i <= 10; i++) {
                mcollection.add("ListView item " + i);
            }
        }
    }
}
