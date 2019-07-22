package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.lang.ref.PhantomReference;
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
        private Intent intent;
        private int appWidgetId;
        RemoteViews view;
        private List<String>mcollection;
        private ArrayList<RecipeCard.RecipeIngredients>ingredients;
        public BakingWidgetFactory(Context context, Intent intent){
            this.context=context;
            this.appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
            this.view=intent.getParcelableExtra("view");
            this.intent=intent;


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
        public void onDataSetChanged() { //AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId,view);

          //  NewAppWidget.updateAppWidget(context,AppWidgetManager.getInstance(getApplicationContext()),appWidgetId);
            SharedPreferences preferences= getSharedPreferences(RecipeDetails.WIDGET_ID_PREF,MODE_PRIVATE);
            String id=preferences.getString(RecipeDetails.WIDGET_ID,"0");
            ingredients= RetroHelper.getMrecipes().get(Integer.valueOf(id)-1).getRecipeIngredients();

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
            Bundle extras = new Bundle();
            extras.putInt("position", position);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            // Make it possible to distinguish the individual on-click
            // action of a given item
            remoteViews.setOnClickFillInIntent(R.id.widget_item_id, fillInIntent);

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
