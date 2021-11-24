package com.example.practical12_19012021093

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.URL

class MainActivity : AppCompatActivity() {


    val listitems = Contact.contactArray as ArrayList<Contact>
    val adapter = ContactListAdapter(this, listitems)
    lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.lv_contacts)

        contacts().execute()
    }

    inner class contacts() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.json-generator.com/templates/Voyo_eeyQQN4/data?access_token=bu5lpejbtrlwvtx09400gzls8uvl5ljcnwesb4li").readText(
                        Charsets.UTF_8
                    )

            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {

                /* Extracting JSON returns from the API */
                val jsonarr = JSONArray(result)

                for (i in 0..7) {

                    val jsonObj_0 = jsonarr.getJSONObject(i)
                    val name_json = jsonObj_0.getString("name")
                    val num_json = jsonObj_0.getString("phone")
                    listitems.add(Contact(name_json, num_json))
                    list.adapter = adapter
                    Toast.makeText(applicationContext, name_json, Toast.LENGTH_SHORT).show()
                }


            } catch (e: Exception) {


            }

        }
    }
}