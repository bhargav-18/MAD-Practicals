package com.example.practical13_19012021093

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import androidx.annotation.RequiresApi

class SmsBroadcastReceiver : BroadcastReceiver() {

    val SMS_BUNDLE = "pdus"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(p0: Context?, p1: Intent?) {

        val intentExtras = p1?.extras

        if (intentExtras != null) {

            val sms = intentExtras[SMS_BUNDLE] as Array<*>?
            var smsMessageStr = ""

            for (i in sms!!.indices) {

                val format = intentExtras.getString("format")
                val smsMessage: SmsMessage = SmsMessage.createFromPdu(sms[i] as ByteArray, format)
                val smsBody: String = smsMessage.messageBody.toString()
                val address: String? = smsMessage.originatingAddress
                smsMessageStr += "SMS From: $address\n"
                smsMessageStr += """ 
                    $smsBody 
                    
                    """.trimIndent()
            }
        }
    }
}