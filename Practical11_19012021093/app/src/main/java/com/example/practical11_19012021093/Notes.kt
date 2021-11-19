package com.example.practical11_19012021093

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Notes(
    var id: Int = 0,
    var title: String,
    var subTitle: String,
    var description: String,
    var modifiedTime: Calendar,
    var isReminder: Boolean = false,
    var timeStamp: Long = System.currentTimeMillis()
) : Serializable {

    companion object {

        fun setReminder(context: Context, notes: Notes) {

            val intent = Intent(context, NotificationReceiver::class.java)
            intent.putExtra("note", notes)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                notes.id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val alarmManager =
                context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager

            if (notes.isReminder) {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    notes.modifiedTime.timeInMillis,
                    pendingIntent
                )
            } else
                alarmManager.cancel(pendingIntent)
        }
    }
}