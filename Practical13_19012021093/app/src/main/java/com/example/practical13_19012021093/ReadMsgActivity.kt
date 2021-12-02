package com.example.practical13_19012021093

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.health.PackageHealthStats
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.jar.Manifest

class ReadMsgActivity : AppCompatActivity() {

    var smsMessageList = Message.messageArray
    val adapter = MessageAdapter(this, smsMessageList)
    lateinit var lvMessages: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_msg)

        lvMessages = findViewById(R.id.lv_read_msg)

        val smsManager: SmsManager = SmsManager.getDefault()

        val btnSendMsg = findViewById<FloatingActionButton>(R.id.fab_add_msg)

        btnSendMsg.setOnClickListener {
            Intent(this, SendMsgActivity::class.java).apply {
                startActivity(this)
            }
        }

        lvMessages.setOnItemClickListener { adapterView, view, i, l ->

            val address: String = smsMessageList[i].address
            val body: String = smsMessageList[i].body

            Intent(this, MessageBodyActivity::class.java).apply {
                putExtra("body", body)
                putExtra("address", address)
                startActivity(this)
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            refreshInbox()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.READ_SMS),
                PERMISSION_REQUEST_READ_SMS
            )
        }

    }

    private fun refreshInbox() {
        val contentResolver = contentResolver
        val smsInboxCursor: Cursor? =
            contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)
        val indexBody: Int = smsInboxCursor!!.getColumnIndex("body")
        val indexAddress: Int = smsInboxCursor.getColumnIndex("address")

        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return
        smsMessageList.clear()
        do {
            val address = smsInboxCursor.getString(indexAddress).toString()
            val body = smsInboxCursor.getString(indexBody).toString()

            smsMessageList.add(Message(address = address, body = body))

            lvMessages.adapter = adapter
        } while (smsInboxCursor.moveToNext())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_READ_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                refreshInbox()
            } else {
                Toast.makeText(
                    this, "You don't have required permission to read messages",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}