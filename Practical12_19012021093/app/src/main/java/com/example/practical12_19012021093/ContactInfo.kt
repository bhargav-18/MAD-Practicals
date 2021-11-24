package com.example.practical12_19012021093

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactInfo : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)

        val tvId = findViewById<TextView>(R.id.tv_id)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvPhone = findViewById<TextView>(R.id.tv_phone)
        val tvAddress = findViewById<TextView>(R.id.tv_address)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")

        tvId.text = "_id = $id"
        tvName.text = "name = $name"
        tvPhone.text = "phone = $phone"
        tvAddress.text = "address = $address"

    }
}