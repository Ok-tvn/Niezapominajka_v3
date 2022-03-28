package com.example.niezapominajkav3

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class glowna : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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
        var plaski_btn_pop_up_powiadomienia_trans = findViewById<LinearLayout>(R.id.plaski_btn_pop_up_powiadomienia_trans);

        //Widoczne przyciski deklaracja
        var pop_up_powiadomienia =findViewById<LinearLayout>(R.id.powiadomienia_pop_up);
        var pop_up =findViewById<LinearLayout>(R.id.pop_up);

        //Deklaracja Przycisków
        var profil_btn = findViewById<ImageButton>(R.id.profil_btn);
        var stokrotka_btn = findViewById<ImageButton>(R.id.stokrotka_btn);
        var plaski_button_pop_up = findViewById<ImageButton>(R.id.plaski_button_pop_up);
        var powiadomienia_btn = findViewById<ImageButton>(R.id.powiadomienia_btn);
        var plaski_btn_pop_up_powiadomienia = findViewById<ImageButton>(R.id.plaski_btn_pop_up_powiadomienia);

        //Naciśnięcie na ikone profilu
        profil_btn.setOnClickListener{
            val intent = Intent(this,Profil::class.java)
            startActivity(intent)
        }


        //Naciśnięcie na stokrotkę
        stokrotka_btn.setOnClickListener{
            val intent2 = Intent(this,ustawienia_pop_up::class.java)
            startActivity(intent2)
        }

        //Naciśnięcie na ikone powiadomień
        powiadomienia_btn.setOnClickListener{
            val intent3 = Intent(this,notyfikacje_pop_up::class.java)
            startActivity(intent3
            )
        }

    }
}