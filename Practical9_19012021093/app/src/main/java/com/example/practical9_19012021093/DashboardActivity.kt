package com.example.practical9_19012021093

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {

    override fun onStart() {
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        bottomNavBar.menu.findItem(R.id.dashboard).isChecked = true
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setStatusBarTransparent()

        val tvUserName = findViewById<TextView>(R.id.tv_user_name)
        val tvUserEmail = findViewById<TextView>(R.id.tv_user_email)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvNumber = findViewById<TextView>(R.id.tv_number)
        val tvUserCity = findViewById<TextView>(R.id.tv_user_city)
        val tvEmailId = findViewById<TextView>(R.id.tv_email_id)
        val btnLogout = findViewById<TextView>(R.id.btn_logout)
        val tvSetAlarm = findViewById<TextView>(R.id.tv_set_alarm)
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)

        tvUserName.text = LoginInfo.name
        tvUserEmail.text = LoginInfo.email
        tvName.text = LoginInfo.name
        tvNumber.text = LoginInfo.phone
        tvUserCity.text = LoginInfo.city
        tvEmailId.text = LoginInfo.email

        btnLogout.setOnClickListener {
            LoginInfo.status = "LoggedOut"
            Intent(this, LoginActivity::class.java).apply {
                startActivity(this)
            }
        }

        tvSetAlarm.setOnClickListener {
            val mcurrentTime: Calendar = Calendar.getInstance()
            val hour: Int = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute: Int = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    val alarmCalendar = Calendar.getInstance()
                    val year: Int = alarmCalendar.get(Calendar.YEAR)
                    val month: Int = alarmCalendar.get(Calendar.MONTH)
                    val day: Int = alarmCalendar.get(Calendar.DATE)
                    alarmCalendar.set(year, month, day, selectedHour, selectedMinute, 0)
                    setAlarm(alarmCalendar.timeInMillis, "Start")
                    val time = SimpleDateFormat("hh:mm ss a", Locale.US).format(alarmCalendar.time)
                    Toast.makeText(
                        this,
                        "Alarm set for\nTime: $time",
                        Toast.LENGTH_SHORT
                    ).show()
                    tvSetAlarm.text = time

                },
                hour,
                minute,
                false
            )

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        bottomNavBar.setOnItemSelectedListener {
            if(it.itemId == R.id.notes){
                Intent(this, NotesActivity::class.java).apply {
                    startActivity(this)
                }
                return@setOnItemSelectedListener true
            }
            else{
                return@setOnItemSelectedListener true
            }
        }

    }


    private fun setAlarm(millisTime: Long, str: String) {
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra("Service1", str)
        val pendingIntent =
            PendingIntent.getBroadcast(applicationContext, 234324243, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        if (str == "Start") {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                millisTime,
                pendingIntent
            )
            Log.d("DashboardTag", "setAlarm: Set Alarm")
        } else if (str == "Stop") {
            alarmManager.cancel(pendingIntent)
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

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}