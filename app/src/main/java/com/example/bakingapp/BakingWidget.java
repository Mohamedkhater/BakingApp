package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText="choose a recipe";

        try{

            widgetText =RecipeListAdapter.fragmentCard.getRecipeName();
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            views.setTextViewText(R.id.appwidget_text, widgetText);
            Intent intent= new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_layout,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }catch (Exception e){
            widgetText="choose a recipe";

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            views.setTextViewText(R.id.appwidget_text, widgetText);
            Intent intent= new Intent(context,RecipeDetails.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_layout,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

      /* Intent intent = new Intent(context,RecipeDetails.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(appWidgetId,pendingIntent);*/

        // Instruct the widget manager to update the widget

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

