package com.example.tugasproyek

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.tugasproyek.databinding.FragmentScheduleBinding
import com.example.tugasproyek.widget.WidgetIdsPref
import com.example.tugasproyek.widget.saveMassage
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class WidgetME : AppWidgetProvider() {
    var myPref: WidgetIdsPref? = null

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        if (myPref == null) {
            myPref = WidgetIdsPref(context)
        }
        myPref?.getIds()?.clear()
        var ids = myPref?.getIds()


        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            ids?.add(appWidgetId.toString())
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        if (ids != null) {
            myPref?.setIds(ids)
        }
    }

    override fun onEnabled(context: Context) {
        var alarmIntent = Intent(context, WidgetME::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, 1)

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, cal.timeInMillis, 60000L, alarmIntent)
    }

    override fun onDisabled(context: Context) {
        var alarmIntent = Intent(context, WidgetME::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(alarmIntent)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action!!.equals(ACTION_AUTO_UPDATE)) {
            if (myPref == null) {
                myPref = WidgetIdsPref(context!!)
            }
            for (appWidgetId in myPref?.getIds()!!) {
                updateAppWidget(
                    context!!,
                    AppWidgetManager.getInstance(context),
                    appWidgetId.toInt()
                )
            }
        }
        super.onReceive(context, intent)
    }

    companion object {
        var Message = saveMassage()
        var ACTION_AUTO_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText = Message.getMessage()
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_m_e)
            views.setTextViewText(R.id.TextBerjalan, widgetText)

            val intent = Intent(context,Home::class.java)

            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btnClick,pendingIntent)



            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)


        }

    }
}