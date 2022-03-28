package com.example.niezapominajkav3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout

class notyfikacje_pop_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notyfikacje_pop_up)

        //Niewidoczne przyciski deklaracja
        var plaski_btn_pop_up_powiadomienia_trans = findViewById<LinearLayout>(R.id.plaski_btn_pop_up_powiadomienia_trans);

        //Deklaracja Przycisków
        var plaski_btn_pop_up_powiadomienia = findViewById<ImageButton>(R.id.plaski_btn_pop_up_powiadomienia);



        //Naciśnięcie na widoczny płaski przycisk
        plaski_btn_pop_up_powiadomienia.setOnClickListener{
            val intent = Intent(this,glowna::class.java)
            startActivity(intent)
        }

        //Naciśnięcie na nie widoczny płaski przycisk
        plaski_btn_pop_up_powiadomienia_trans.setOnClickListener{
            val intent = Intent(this,glowna::class.java)
            startActivity(intent)
        }

        plaski_btn_pop_up_powiadomienia_trans.getBackground().setAlpha(0);
    }
}