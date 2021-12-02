package com.example.practical13_19012021093

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class SendMsgActivity : AppCompatActivity() {

    private lateinit var etPhoneNo: TextInputEditText
    private lateinit var etMessage: TextInputEditText
    private lateinit var btnSendMsg: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_msg)

        etPhoneNo = findViewById(R.id.et_phone_no)
        etMessage = findViewById(R.id.et_message)
        btnSendMsg = findViewById(R.id.btn_send_message)

        btnSendMsg.setOnClickListener {
            permissionCheck()
        }

    }

    private fun permissionCheck() {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            val permissionCheck1 =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

            if (permissionCheck1 == PackageManager.PERMISSION_GRANTED) {
                sendMessage()
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_PHONE_STATE),
                    PERMISSION_REQUEST_SEND_SMS
                )
            }

        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.SEND_SMS),
                PERMISSION_REQUEST_SEND_SMS
            )
        }
    }

    private fun sendMessage() {
        val number: String = etPhoneNo.text.toString().trim()
        val msg: String = etMessage.text.toString().trim()
        if (number == "" || msg == "") {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (PhoneNumberUtils.isGlobalPhoneNumber(number)) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(number, null, msg, null, null)
                Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter the correct number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendMessage()
            } else {
                Toast.makeText(
                    this, "You don't have required permission to send a message",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (requestCode == PERMISSION_REQUEST_READ_PHONE_STATE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendMessage()
            } else {
                Toast.makeText(
                    this, "You don't have required permission to send a message",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

const val PERMISSION_REQUEST_SEND_SMS = 101
const val PERMISSION_REQUEST_READ_PHONE_STATE = 102
const val PERMISSION_REQUEST_READ_SMS = 103