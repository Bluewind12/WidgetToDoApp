package com.example.momonyan.widgettodoapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RemoteViews
import android.content.IntentFilter
import android.os.IBinder
import android.content.ComponentName

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_TIME_TICK)
        context.registerReceiver(this, filter)
    }
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        lateinit var preferences: SharedPreferences
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            preferences = context.getSharedPreferences("Widget", AppCompatActivity.MODE_MULTI_PROCESS)
            views.setTextViewText(R.id.textView2, preferences.getString("list1", ""))
            views.setTextViewText(R.id.textView3, preferences.getString("list2", ""))
            views.setTextViewText(R.id.textView4, preferences.getString("list3", ""))
            views.setTextViewText(R.id.textView5, preferences.getString("list4", ""))
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

