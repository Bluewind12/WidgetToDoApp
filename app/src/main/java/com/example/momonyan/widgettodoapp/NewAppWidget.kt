package com.example.momonyan.widgettodoapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.RemoteViews

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

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            var tasks = Array<String>(4,{""})
            var tasksBoolean = Array<Boolean>(4,{false})
            val preferences = AppCompatActivity().getSharedPreferences("Widget", AppCompatActivity.MODE_PRIVATE);
            tasks[0] = preferences.getString("list1", "a")
            tasks[1] = preferences.getString("list2", "b")
            tasks[2] = preferences.getString("list3", "c")
            tasks[3] = preferences.getString("list4", "d")

            tasksBoolean[0] = preferences.getBoolean("listBoolean1", false)
            tasksBoolean[1] = preferences.getBoolean("listBoolean2", false)
            tasksBoolean[2] = preferences.getBoolean("listBoolean3", false)
            tasksBoolean[3] = preferences.getBoolean("listBoolean4", false)

            views.setTextViewText(R.id.textView2,tasks[0])
            views.setTextViewText(R.id.textView3,tasks[1])
            views.setTextViewText(R.id.textView4,tasks[2])
            views.setTextViewText(R.id.textView5,tasks[3])

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)


        }
    }
}

