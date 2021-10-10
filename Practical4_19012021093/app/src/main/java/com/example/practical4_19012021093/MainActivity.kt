package com.example.practical4_19012021093

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.edit_text_input)
        val call = findViewById<TextView>(R.id.button_call)
        val openUrl = findViewById<TextView>(R.id.button_url)
        val pickContact = findViewById<TextView>(R.id.button_pick_contact)
        val callLog = findViewById<TextView>(R.id.button_open_call_log)
        val gallery = findViewById<TextView>(R.id.button_open_gallery)
        val alarm = findViewById<TextView>(R.id.button_set_alarm)
        val camera = findViewById<TextView>(R.id.button_open_camera)


        call.setOnClickListener {
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:${input.text.trim()}")).apply {
                startActivity(this)
            }
        }

        openUrl.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://${input.text.trim()}")).apply {
                startActivity(this)
            }
        }

        pickContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER)
            }
        }

        callLog.setOnClickListener {
            Intent(Intent.ACTION_VIEW).apply {
                type = CallLog.Calls.CONTENT_TYPE
                startActivity(this)
            }
        }

        gallery.setOnClickListener {
            Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                startActivity(this)
            }
        }

        alarm.setOnClickListener {
            Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm")
                putExtra(AlarmClock.EXTRA_HOUR, 12)
                putExtra(AlarmClock.EXTRA_MINUTES, 0)
                startActivity(this)
            }
        }

        camera.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                startActivity(this)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == Activity.RESULT_OK) {
            val contactUri: Uri = data!!.data!!
            val projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
            contentResolver.query(contactUri, projection, null, null, null).use { cursor ->
                if (cursor!!.moveToFirst()) {
                    val numberIndex =
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val number = cursor.getString(numberIndex)
                    Toast.makeText(
                        this,
                        "Selected number is: $number",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

const val REQUEST_SELECT_PHONE_NUMBER = 1