package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText="choose a recipe";

        try{

            widgetText =RecipeListAdapter.fragmentCard.getRecipeName();
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            //  views.setTextViewText(R.id.appwidget_text, widgetText);

          ;

            Intent serviceIntent= new Intent(context,BakingWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter(R.id.stackview_id,serviceIntent);



            Intent intent= new Intent(context,RecipeDetails.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.stackview_id,pendingIntent);
           // views.setOnClickPendingIntent(R.id.widget_layout,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
          // appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stackview_id);


        }catch (Exception e){
            widgetText="choose a recipe";

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.empty_widget);
            //views.setTextViewText(R.id.appwidget_text, widgetText);
            Intent intent= new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
            views.setOnClickPendingIntent(R.id.empty_view,pendingIntent);

            views.setTextViewText(R.id.empty_view,widgetText);

            appWidgetManager.updateAppWidget(appWidgetId, views);
           // appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stackview_id);


        }
    }
    public static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
        // update list view with data
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stackview_id);
        //Now update all widgets
        for (int appWidgetId : appWidgetIds) {
            NewAppWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
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

