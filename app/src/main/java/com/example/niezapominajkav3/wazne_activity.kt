package com.example.niezapominajkav3

import ItemsViewModel
import RecycleAdaper
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class wazne_activity : AppCompatActivity() {

    private var et: EditText? = null
    private val file = "mytextfile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wazne)


        // creating TextView programmatically
        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically in Kotlin"

        // add TextView to LinearLayout
        //ll_main_layout.addView(tv_dynamic)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recylerview_wazne)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.bell, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = RecycleAdaper(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }


    fun read(view: View?) {
        try {
            val fin = openFileInput(file)
            var c: Int
            var temp = ""
            while (fin.read().also { c = it } != -1) {
                charset("UTF-8")
                temp = temp + Character.toString(c.toChar())
            }
            et!!.setText(temp)
            File(file).forEachLine {
                if(it=="nazwa"){
                    println(it.split(" ").filter { it != "nazwa:" }.joinToString(" "))

                }
            }
            Toast.makeText(
                baseContext, "file read",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
        }
    }
    fun clear(view: View) {
        try {
            charset("UTF-8")
            val fileOutputStream: FileOutputStream = openFileOutput("mytextfile.txt", Context.MODE_PRIVATE)
            val outputWriter = OutputStreamWriter(fileOutputStream)
            outputWriter.write("")
            outputWriter.close()
            //display file saved message
            Toast.makeText(baseContext, "File clear successfully!", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}