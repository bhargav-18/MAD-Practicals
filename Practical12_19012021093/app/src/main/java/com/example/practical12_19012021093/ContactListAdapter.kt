package com.example.practical12_19012021093

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ContactListAdapter(private var context: Context, var items: ArrayList<Contact>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)

        val contactName: TextView = view.findViewById(R.id.tvName)
        val contactPhone: TextView = view.findViewById(R.id.tvPhone)

        contactName.text = items[position].name
        contactPhone.text = items[position].num

        return view
    }


}