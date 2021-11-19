package com.example.practical11_19012021093

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_info)

        val customToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.custom_toolbar)
        setSupportActionBar(customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        customToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val tvNoteTitle = findViewById<TextView>(R.id.tv_note_title)
        val tvNoteSubTitle = findViewById<TextView>(R.id.tv_note_sub_title)
        val tvNoteDescription = findViewById<TextView>(R.id.tv_note_description)
        val tvNoteTimeStamp = findViewById<TextView>(R.id.tv_note_time_stamp)
        val tvNoteReminderTime = findViewById<TextView>(R.id.tv_notes_reminder_time)

        val title = intent?.getStringExtra("noteTitle")
        val subTitle = intent?.getStringExtra("noteSubtitle")
        val description = intent?.getStringExtra("noteDescription")
        val timeStamp = intent?.getLongExtra("noteTimeStamp", 0)
        val modifiedTime = intent?.getLongExtra("noteModifiedTime", 0)

        tvNoteTitle.text = title
        tvNoteSubTitle.text = subTitle
        tvNoteDescription.text = description
        val timeFormat = SimpleDateFormat("MMM, dd yyyy hh:mm:ss a", Locale.ENGLISH)
        tvNoteTimeStamp.text = timeFormat.format(timeStamp)
        val time =
            "Reminder at " + timeFormat.format(modifiedTime)
        tvNoteReminderTime.text = time
    }

    override fun onBackPressed() {
        Intent(this, NotesActivity::class.java).apply {
            startActivity(this)
        }
        super.onBackPressed()
    }
}