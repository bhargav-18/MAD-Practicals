package com.example.practical3_19012021093

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onCreate invoked")
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStart invoked")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onResume invoked")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onPause invoked")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStop invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onDestroy invoked")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart invoked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onRestart invoked")
    }
}