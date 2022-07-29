package com.example.niezapominajkav3

import ItemsViewModel
import RecycleAdaper
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class praca : AppCompatActivity() {

    private var et: EditText? = null
    private val file = "mytextfile.txt"
    var temp = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wazne)

        var wyswietlanie_btn = findViewById<Button>(R.id.wyswietl_btn);
        wyswietlanie_btn.isVisible=false;
        wyswietlanie_btn.performClick();

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recylerview_wazne)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val dane = ArrayList<ItemsViewModel>()

        var string = temp.toString();
        var lines = string.lines();
        var nazwa = " ";
        var data = " ";
        var godzina = " ";
        lines.forEach{
            if(it.contains("Nazwa")){
                nazwa = it.split("Nazwa:")[1]
            }else if(it.contains("Data")) {
                data = it.split("Data:")[1]
            }else if(it.contains("Godzina")) {
                godzina = it.split("Godzina:")[1]
            }else if(it.contains("Kategoria: Praca")){
                dane.add(ItemsViewModel(R.drawable.stokrotka, nazwa.toString(), data.toString(), godzina.toString()))
            }
            //dane.add(ItemsViewModel(R.drawable.bell, it, data.toString(), godzina.toString()))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = RecycleAdaper(dane)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }


    fun read(view: View?) {
        try {
            val fin = openFileInput(file)
            var c: Int
            temp = "";
            while (fin.read().also { c = it } != -1) {
                charset("UTF-8")
                temp = temp + Character.toString(c.toChar())
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