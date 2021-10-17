package com.example.practical9_19012021093

import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ListView
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import kotlin.collections.ArrayList


class NotesActivity : AppCompatActivity() {

    override fun onStart() {
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        bottomNavBar.menu.findItem(R.id.notes).isChecked = true
        super.onStart()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        createNotificationChannel()
        setStatusBarTransparent()

        val listItems = Notes.notesArray as ArrayList<Notes>

        val adapter = NotesAdapter(this, listItems)
        val lvNotes = findViewById<ListView>(R.id.lv_notes)
        lvNotes.adapter = adapter

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)

        bottomNavBar.setOnItemSelectedListener {
            if (it.itemId == R.id.dashboard) {
                Intent(this, DashboardActivity::class.java).apply {
                    startActivity(this)
                }
                return@setOnItemSelectedListener true
            } else {
                return@setOnItemSelectedListener true
            }
        }

        val fabAddNote = findViewById<FloatingActionButton>(R.id.fab_add_note)
        fabAddNote.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_edit_note_dialog)
            val timePicker = dialog.findViewById<TimePicker>(R.id.time_picker)
            val reminderSwitch = dialog.findViewById<SwitchMaterial>(R.id.switch_reminder)
            val noteTitle = dialog.findViewById<TextInputEditText>(R.id.et_note_title)
            val noteSubTitle = dialog.findViewById<TextInputEditText>(R.id.et_note_sub_title)
            val noteDescription = dialog.findViewById<TextInputEditText>(R.id.et_note_description)
            val btnOk = dialog.findViewById<TextView>(R.id.btn_ok)

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val date = cal.get(Calendar.DATE)

            btnOk.setOnClickListener {
                cal.set(year, month, date, timePicker.hour, timePicker.minute, 0)
                val note = Notes(
                    noteTitle.text.toString().trim(),
                    noteSubTitle.text.toString().trim(),
                    noteDescription.text.toString().trim(),
                    cal,
                    reminderSwitch.isChecked
                )
                listItems.add(note)
                Notes.setReminder(this, note)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val description = "Channel for sending notes notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = description
            channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT in 19..20) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val winParameters = window.attributes
        if (on) {
            winParameters.flags = winParameters.flags or bits
        } else {
            winParameters.flags = winParameters.flags and bits.inv()
        }
        window.attributes = winParameters
    }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }
}

const val channelId = "notesChannel"
const val channelName = "Notes Channel"