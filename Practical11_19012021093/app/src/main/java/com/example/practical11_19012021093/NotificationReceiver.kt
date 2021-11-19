package com.example.practical11_19012021093

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val note = intent?.getSerializableExtra("note") as Notes
        val intentOpenActivity = Intent(context, NoteInfoActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("note", note)
        }

        val contentIntent = PendingIntent.getActivity(
            context,
            note.id,
            intentOpenActivity,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(note.title)
            .setContentText(note.description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)

        with(NotificationManagerCompat.from(context)) {
            notify(note.id, builder.build())
        }
    }
}