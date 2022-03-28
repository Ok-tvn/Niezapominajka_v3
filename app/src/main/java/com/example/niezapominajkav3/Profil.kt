package com.example.niezapominajkav3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        //Deklaracja Przycisków
        var cofnij_profil_btn = findViewById<ImageButton>(R.id.cofnij_profil_btn);

        //Naciśnięcie na przycisk cofnij
        cofnij_profil_btn.setOnClickListener{
            val intent = Intent(this,glowna::class.java)
            startActivity(intent)
        }
    }
}