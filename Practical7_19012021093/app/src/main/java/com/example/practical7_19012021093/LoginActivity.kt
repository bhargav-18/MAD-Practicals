package com.example.practical7_19012021093

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (LoginInfo.status == "LoggedIn") {
            Intent(this, DashboardActivity::class.java).apply {
                startActivity(this)
            }
        }

        setStatusBarTransparent()

        val cvLogin = findViewById<CardView>(R.id.cv_login)
        cvLogin.setBackgroundResource(R.drawable.side_background)

        val etEmail = findViewById<TextInputEditText>(R.id.et_login_email)
        val etPassword = findViewById<TextInputEditText>(R.id.et_login_password)
        val tvSignUp = findViewById<TextView>(R.id.tv_sign_up)
        val btnSignUp = findViewById<TextView>(R.id.btn_sign_up)
        val btnLogin = findViewById<AppCompatButton>(R.id.btn_login)

        btnSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
            }
        }
        tvSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
            }
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                email.isEmpty() or password.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Email or password cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                email != LoginInfo.email || password != LoginInfo.password -> {
                    Toast.makeText(
                        this,
                        "Invalid Login Credentials\nPlease try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Intent(this, DashboardActivity::class.java).apply {
                        LoginInfo.status = "LoggedIn"
                        startActivity(this)
                    }
                }
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