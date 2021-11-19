package com.example.practical11_19012021093

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var animationDrawable: AnimationDrawable
    lateinit var rotateAnimation: Animation
    private lateinit var ivImg: ImageView
    lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ivImg = findViewById(R.id.iv_img)
        ivImg.setBackgroundResource(R.drawable.uvpce_logo_animation)
        animationDrawable = ivImg.background as AnimationDrawable

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        rotateAnimation.setAnimationListener(this)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            animationDrawable.start()
            ivImg.startAnimation(rotateAnimation)
        } else {
            animationDrawable.stop()
        }
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val sharedStatus = pref.getBoolean("status", false)
        if (sharedStatus) {
            Intent(this, DashboardActivity::class.java).also {
                startActivity(it)
            }
        } else {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    override fun onAnimationRepeat(animation: Animation?) {
    }
}