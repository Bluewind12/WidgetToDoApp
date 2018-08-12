package com.example.momonyan.widgettodoapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.widget.RemoteViews
import android.graphics.Color

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
    }
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        lateinit var preferences: SharedPreferences
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            preferences = context.getSharedPreferences("Widget", AppCompatActivity.MODE_PRIVATE)

            views.setTextViewText(R.id.textView2, preferences.getString("list1", "設定なし"))
            views.setTextColor(R.id.textView2, preferences.getInt("list1_Color", Color.rgb(0,0,0)))

            views.setTextViewText(R.id.textView3, preferences.getString("list2", "設定なし"))
            views.setTextColor(R.id.textView3, preferences.getInt("list2_Color",Color.rgb(0,0,0)))

            views.setTextViewText(R.id.textView4, preferences.getString("list3", "設定なし"))
            views.setTextColor(R.id.textView4, preferences.getInt("list3_Color",Color.rgb(0,0,0)))

            views.setTextViewText(R.id.textView5, preferences.getString("list4", "設定なし"))
            views.setTextColor(R.id.textView5, preferences.getInt("list4_Color",Color.rgb(0,0,0)))

            views.setInt(R.id.widgetLay,"setBackgroundColor", preferences.getInt("backGround_Color",Color.rgb(192,192,192)))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

