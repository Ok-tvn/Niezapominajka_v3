package com.example.niezapominajkav3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout

class ustawienia_pop_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ustawienia_pop_up)

        //Pobieranie wielkości ekranu
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        //Zmienianie wielkosci imagebuttons
        val view = ArrayList<ImageButton>()
        for (i in 1..ArrayList<ImageView>().size) {
            val idString = "img_button$i"
            val viewID = resources.getIdentifier(idString, "id", packageName)
            view.add(findViewById(viewID))
            view[i].layoutParams.width=width/6
            view[i].layoutParams.height=width/6
        }

        //Niewidoczne przyciski deklaracja
        var plaski_btn_pop_up_trans = findViewById<LinearLayout>(R.id.plaski_btn_pop_up_trans);


        //Deklaracja Przycisków
        var plaski_button_pop_up = findViewById<ImageButton>(R.id.plaski_button_pop_up);
        var dodaj = findViewById<LinearLayout>(R.id.dodaj);


        //Naciśnięcie na widoczny płaski przycisk
        dodaj.setOnClickListener{
            val intent = Intent(this,dodawanie::class.java)
            startActivity(intent)
        }

        //Naciśnięcie na widoczny płaski przycisk
        plaski_button_pop_up.setOnClickListener{
            val intent = Intent(this,glowna::class.java)
            startActivity(intent)
        }

        //Naciśnięcie na nie widoczny płaski przycisk
        plaski_btn_pop_up_trans.setOnClickListener{
            val intent = Intent(this,glowna::class.java)
            startActivity(intent)
        }

        plaski_btn_pop_up_trans.getBackground().setAlpha(0);
    }
}