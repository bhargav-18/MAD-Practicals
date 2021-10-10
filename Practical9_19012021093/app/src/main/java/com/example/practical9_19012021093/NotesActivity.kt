package com.example.practical9_19012021093

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)

        bottomNavBar.setOnItemSelectedListener {
            if(it.itemId == R.id.dashboard){
                Intent(this, DashboardActivity::class.java).apply {
                    startActivity(this)
                }
                return@setOnItemSelectedListener true
            }
            else{
                return@setOnItemSelectedListener true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }
}