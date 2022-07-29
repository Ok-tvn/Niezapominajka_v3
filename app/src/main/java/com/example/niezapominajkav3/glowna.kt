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

        //Deklaracja Przycisków
        var profil_btn = findViewById<ImageButton>(R.id.profil_btn);
        var stokrotka_btn = findViewById<ImageButton>(R.id.stokrotka_btn);
        var powiadomienia_btn = findViewById<ImageButton>(R.id.powiadomienia_btn);
        var wazne_linear = findViewById<LinearLayout>(R.id.wazne_linear)
        var figura_jeden = findViewById<LinearLayout>(R.id.figura_jeden)
        var figura_dwa = findViewById<LinearLayout>(R.id.figura_dwa)
        var figura_trzy = findViewById<LinearLayout>(R.id.figura_trzy)
        var figura_cztery = findViewById<LinearLayout>(R.id.figura_cztery)

        wazne_linear.setOnClickListener{
            val intent = Intent(this,wazne_activity::class.java);
            startActivity(intent);
        }

        //Naciśnięcie na ikone profilu
        profil_btn.setOnClickListener{
            val intent = Intent(this,Profil::class.java);
            startActivity(intent);
        }

        //Naciśnięcie na stokrotkę
        stokrotka_btn.setOnClickListener{
            val intent2 = Intent(this,ustawienia_pop_up::class.java);
            startActivity(intent2);
        }

        //Naciśnięcie na ikone powiadomień
        powiadomienia_btn.setOnClickListener{
            val intent3 = Intent(this,notyfikacje_wyswietlanie::class.java);
                //Intent(this,notyfikacje_pop_up::class.java);
            startActivity(intent3);
        }

        //Naciśnięcie na figurę jeden
        figura_jeden.setOnClickListener{
            val intent3 = Intent(this,szkola::class.java);
            //Intent(this,notyfikacje_pop_up::class.java);
            startActivity(intent3);
        }

        //Naciśnięcie na figurę dwa
        figura_dwa.setOnClickListener{
            val intent3 = Intent(this,sport::class.java);
            //Intent(this,notyfikacje_pop_up::class.java);
            startActivity(intent3);
        }

        //Naciśnięcie na figurę trzy
        figura_trzy.setOnClickListener{
            val intent3 = Intent(this,codziennosc::class.java);
            //Intent(this,notyfikacje_pop_up::class.java);
            startActivity(intent3);
        }

        //Naciśnięcie na figurę cztery
        figura_cztery.setOnClickListener{
            val intent3 = Intent(this,praca::class.java);
            //Intent(this,notyfikacje_pop_up::class.java);
            startActivity(intent3);
        }
    }
}