package com.example.practical13_19012021093

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MessageAdapter(var context: Context, var messageList: ArrayList<Message>) : BaseAdapter() {

    override fun getCount(): Int {
        return messageList.size
    }

    override fun getItem(p0: Int): Any {
        return messageList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, p2, false)

        val number: TextView = view.findViewById(R.id.tv_number)
        number.text = messageList[position].address
        val body: TextView = view.findViewById(R.id.tv_message)
        body.text = messageList[position].body

        return view
    }
}