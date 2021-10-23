package com.example.practical11_19012021093

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ivImg = findViewById<ImageView>(R.id.iv_img)
        ivImg.setBackgroundResource(R.drawable.uvpce_logo_animation)
        animationDrawable = ivImg.background as AnimationDrawable

        Handler().postDelayed({
            val animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
            ivImg.startAnimation(animationRotate)
        }, 2800)

        Handler().postDelayed({
            Intent(this, DashboardActivity::class.java).apply {
                startActivity(this)
            }
        }, 10000)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            animationDrawable.start()
        } else {
            animationDrawable.stop()
        }
    }

    override fun onAnimationStart(animation: Animation?) {
        TODO("Not yet implemented")
    }

    override fun onAnimationEnd(animation: Animation?) {
        TODO("Not yet implemented")
    }

    override fun onAnimationRepeat(animation: Animation?) {
        TODO("Not yet implemented")
    }
}