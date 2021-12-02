package com.example.practical13_19012021093

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.telephony.SmsManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MessageBodyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_body)

        val body = intent.getStringExtra("body")
        val address = intent.getStringExtra("address")

        val btnSend = findViewById<FloatingActionButton>(R.id.btn_send_message)
        val etMessage = findViewById<TextInputEditText>(R.id.et_message)

        val number = findViewById<TextView>(R.id.tv_address)
        val messageBody = findViewById<TextView>(R.id.tv_message_body)

        number.text = address
        messageBody.text = body

        btnSend.setOnClickListener {
            if (etMessage.text!!.isBlank()) {
                Toast.makeText(this, "Message field cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (PhoneNumberUtils.isGlobalPhoneNumber(address)) {
                    val smsManager: SmsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(
                        address,
                        null,
                        etMessage.text.toString().trim(),
                        null,
                        null
                    )
                    Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please enter the correct number", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}