package com.example.practical7_19012021093

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
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setStatusBarTransparent()

        val cvLogin = findViewById<CardView>(R.id.cv_register)
        cvLogin.setBackgroundResource(R.drawable.right_side_bg)

        val etUname = findViewById<TextInputEditText>(R.id.et_register_uname)
        val etPhone = findViewById<TextInputEditText>(R.id.et_register_phone)
        val etCity = findViewById<TextInputEditText>(R.id.et_register_city)
        val etEmail = findViewById<TextInputEditText>(R.id.et_register_email)
        val etPassword = findViewById<TextInputEditText>(R.id.et_register_password)
        val etCPassword = findViewById<TextInputEditText>(R.id.et_register_cpassword)

        val tvLogin = findViewById<TextView>(R.id.tv_login)

        val btnRegister = findViewById<AppCompatButton>(R.id.btn_register)
        btnRegister.setOnClickListener {
            val name = etUname.text.toString().trim()
            val phone = "+91 " + etPhone.text.toString().trim()
            val city = etCity.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val cPassword = etCPassword.text.toString().trim()

            when {
                name.isEmpty() or phone.isEmpty() or city.isEmpty() or email.isEmpty() or
                        password.isEmpty() or cPassword.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "All fields are required!\nPlease fill all details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                password != cPassword -> {
                    Toast.makeText(
                        this,
                        "Password and Confirm password must be same",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    LoginInfo.name = name
                    LoginInfo.phone = phone
                    LoginInfo.city = city
                    LoginInfo.email = email
                    LoginInfo.password = password
                    Intent(this, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
        }

        tvLogin.setOnClickListener {
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
}