package com.example.niezapominajkav3

import android.os.Bundle
import android.view.View
import android.widget.EditText
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
            Toast.makeText(
                baseContext, "file read",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
        }
    }
}