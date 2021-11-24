package com.example.practical12_19012021093

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.json.JSONArray
import java.net.URL

class MainActivity : AppCompatActivity() {


    val listItems = Contact.contactArray as ArrayList<Contact>
    lateinit var list: ListView
    private lateinit var adapter: ContactListAdapter
    private lateinit var progress: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.lv_contacts)
        adapter = ContactListAdapter(this, listItems)

        progress = findViewById(R.id.progress_bar)

        contacts().execute()
    }

    inner class contacts() : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progress.isVisible = true
        }

        override fun doInBackground(vararg params: String?): String? {
            val response: String? = try {
                URL("https://api.json-generator.com/templates/Voyo_eeyQQN4/data?access_token=bu5lpejbtrlwvtx09400gzls8uvl5ljcnwesb4li").readText(
                    Charsets.UTF_8
                )

            } catch (e: Exception) {
                null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            progress.isVisible = false

            try {

                /* Extracting JSON returns from the API */
                val jsonarr = JSONArray(result)

                for (i in 0 until jsonarr.length()) {

                    val jsonObj = jsonarr.getJSONObject(i)
                    val jsonName = jsonObj.getString("name")
                    val jsonPhone = jsonObj.getString("phone")
                    listItems.add(Contact(jsonName, jsonPhone))

                }
                list.adapter = adapter

            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}