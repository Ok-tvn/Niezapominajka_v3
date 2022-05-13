package com.example.niezapominajkav3

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

class wazne_activity : AppCompatActivity() {

    private var et: EditText? = null
    private val file = "mytextfile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wazne)
        et = findViewById(R.id.editText1);
        charset("UTF-8")

        // creating TextView programmatically
        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically in Kotlin"

        // add TextView to LinearLayout
        ll_main_layout.addView(tv_dynamic)

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
                if(it=="imie"){
                    println(it.split(" ").filter { it != "imie:" }.joinToString(" "))

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