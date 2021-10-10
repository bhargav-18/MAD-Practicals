package com.example.practical7_19012021093

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView

class DashboardActivity : AppCompatActivity() {
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