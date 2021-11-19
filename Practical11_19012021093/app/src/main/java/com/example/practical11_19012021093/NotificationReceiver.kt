package com.example.practical11_19012021093

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.os.Bundle

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val id = intent?.getIntExtra("noteId",0)
        val title = intent?.getStringExtra("noteTitle")
        val subTitle = intent?.getStringExtra("noteSubtitle")
        val description = intent?.getStringExtra("noteDescription")
        val timeStamp = intent?.getLongExtra("noteTimeStamp", 0)
        val modifiedTime = intent?.getLongExtra("noteModifiedTime", 0)

        val intentOpenActivity = Intent(context, NoteInfoActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("noteTitle", title)
            putExtra("noteSubtitle", subTitle)
            putExtra("noteDescription", description)
            putExtra("noteTimeStamp", timeStamp)
            putExtra("noteModifiedTime", modifiedTime)
        }

        val contentIntent = PendingIntent.getActivity(
            context,
            id!!,
            intentOpenActivity,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)

        with(NotificationManagerCompat.from(context)) {
            notify(id, builder.build())
        }
    }
}