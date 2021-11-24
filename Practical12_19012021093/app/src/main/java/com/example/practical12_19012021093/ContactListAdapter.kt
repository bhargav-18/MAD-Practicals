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

        val view = LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false)

        val contact_name: TextView = view.findViewById(R.id.name_textview)
        val contact_num: TextView = view.findViewById(R.id.number_textview)

        contact_name.text = items[position].name
        contact_num.text = items[position].num

        return view
    }


}