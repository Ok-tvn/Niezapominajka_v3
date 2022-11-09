package com.example.niezapominajkav3

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.niezapominajkav3.db.ReminderDatabase2
import com.example.niezapominajkav3.utils.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class glowna : AppCompatActivity() {
    private val noteDB : ReminderDatabase2 by lazy {
        Room.databaseBuilder(this, ReminderDatabase2::class.java, Constants.REMINDER_DATABASE2)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Deklaracja Przycisków
        var profil_btn = findViewById<ImageButton>(R.id.profil_btn);
        var stokrotka_btn = findViewById<ImageButton>(R.id.stokrotka_btn);
        var powiadomienia_btn = findViewById<ImageButton>(R.id.powiadomienia_btn);
        var wazne_linear = findViewById<LinearLayout>(R.id.wazne_linear)
        var dzisiejsze_linear = findViewById<LinearLayout>(R.id.dzisiejsze_linear)
        var figura_jeden = findViewById<LinearLayout>(R.id.figura_jeden)
        var figura_dwa = findViewById<LinearLayout>(R.id.figura_dwa)
        var figura_trzy = findViewById<LinearLayout>(R.id.figura_trzy)
        var figura_cztery = findViewById<LinearLayout>(R.id.figura_cztery)
        var data_glowny = findViewById<TextView>(R.id.data_glowny)
        var button_prawo = findViewById<ImageButton>(R.id.button_prawo)
        var button_lewo = findViewById<ImageButton>(R.id.button_lewo)
        var dzisiaj_txt = findViewById<TextView>(R.id.dzisiaj)
        var wazne_wszystkie_txt = findViewById<TextView>(R.id.ilosc_wazne)
        var wazne_niewykonane_txt = findViewById<TextView>(R.id.niewykonane_wazne)
        var dzisiaj_wszystkie_txt = findViewById<TextView>(R.id.ilosc_dzisiejsze)
        var dzisiaj_niewykonane_txt = findViewById<TextView>(R.id.niewykonane_dzisiejsze)
        var szkola_prc_txt = findViewById<TextView>(R.id.szkola_prc_txt)


        //Naciśnięcie na ważne
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
        var day=0;
        button_prawo.setOnClickListener{
            day++;
            val current = LocalDateTime.now().plusDays(day.toLong())
            val dzisiaj = LocalDateTime.now()
            val wczoraj = LocalDateTime.now().plusDays(-1)
            val jutro = LocalDateTime.now().plusDays(1)
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            val formatted = current.format(formatter)
            val aktualna = dzisiaj.format(formatter)
            val wczorajsza = wczoraj.format(formatter)
            val jutrzejsza = jutro.format(formatter)
            data_glowny.text=formatted
            if(aktualna.toString()==data_glowny.text){
                dzisiaj_txt.text="Dzisiaj"
            }else if(jutrzejsza.toString()==data_glowny.text){
                dzisiaj_txt.text="Jutro"
            }else if(wczorajsza.toString()==data_glowny.text){
                dzisiaj_txt.text="Wczoraj"
            }else{
                dzisiaj_txt.text=" "
            }
            if(noteDB.doa().getAllRemindersData(data_glowny.toString()).isNotEmpty() || noteDB.doa().getAllRemindersDataDone(data_glowny.toString(),"false").isNotEmpty()){
                val dzisiejsze_wszystkie=noteDB.doa().getNumberAll(data_glowny.toString()).size
                val dzisiejsze_niewykonane=noteDB.doa().getNumberNotDone(data_glowny.toString(),"false").size

                dzisiaj_wszystkie_txt.text="Ilość powiadomień: "+dzisiejsze_wszystkie.toString()
                dzisiaj_niewykonane_txt.text="W tym niewykonane: "+dzisiejsze_niewykonane.toString()
            }else{
                dzisiaj_wszystkie_txt.text="Ilość powiadomień: 0"
                dzisiaj_niewykonane_txt.text="W tym niewykonane: 0"
            }
        }
        button_lewo.setOnClickListener{
            day--;
            val current = LocalDateTime.now().plusDays(day.toLong())
            val dzisiaj = LocalDateTime.now()
            val wczoraj = LocalDateTime.now().plusDays(-1)
            val jutro = LocalDateTime.now().plusDays(1)
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            val formatted = current.format(formatter)
            val aktualna = dzisiaj.format(formatter)
            val wczorajsza = wczoraj.format(formatter)
            val jutrzejsza = jutro.format(formatter)
            data_glowny.text=formatted
            if(aktualna.toString()==data_glowny.text){
                dzisiaj_txt.text="Dzisiaj"
            }else if(jutrzejsza.toString()==data_glowny.text){
                dzisiaj_txt.text="Jutro"
            }else if(wczorajsza.toString()==data_glowny.text){
                dzisiaj_txt.text="Wczoraj"
            }else{
                dzisiaj_txt.text=" "
            }
            if(noteDB.doa().getAllRemindersData(data_glowny.toString()).isNotEmpty() || noteDB.doa().getAllRemindersDataDone(data_glowny.toString(),"false").isNotEmpty()){
                val dzisiejsze_wszystkie=noteDB.doa().getNumberAll(data_glowny.toString()).size
                val dzisiejsze_niewykonane=noteDB.doa().getNumberNotDone(data_glowny.toString(),"false").size

                dzisiaj_wszystkie_txt.text="Ilość powiadomień: "+dzisiejsze_wszystkie.toString()
                dzisiaj_niewykonane_txt.text="W tym niewykonane: "+dzisiejsze_niewykonane.toString()
            }else{
                dzisiaj_wszystkie_txt.text="Ilość powiadomień: 0"
                dzisiaj_niewykonane_txt.text="W tym niewykonane: 0"
            }
        }
        val current = LocalDateTime.now().plusDays(day.toLong())

        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val formatted = current.format(formatter)
        data_glowny.text=formatted

        //Naciśnięcie na dzisiejsze
        dzisiejsze_linear.setOnClickListener{
            val intent_dzisiejsze = Intent(this@glowna,dzisiejsze::class.java);
            intent_dzisiejsze.putExtra("data",data_glowny.text.toString())
            startActivity(intent_dzisiejsze);
        }

        if(noteDB.doa().getAllRemindersImpo("true").isNotEmpty() || noteDB.doa().getAllRemindersImpoDone("true","false").isNotEmpty()){
            val wazne_wszystkie=noteDB.doa().getNumberAllWazne("true").size
            val wazne_niewykonane=noteDB.doa().getNumberAllWazneNotDone("true","false").size
            wazne_wszystkie_txt.text="Ilość powiadomień: "+wazne_wszystkie.toString()
            wazne_niewykonane_txt.text="W tym niewykonane: "+wazne_niewykonane.toString()
        }else{
            wazne_wszystkie_txt.text="Ilość powiadomień: 0"
            wazne_niewykonane_txt.text="W tym niewykonane: 0"
        }

        if(noteDB.doa().getAllRemindersData(data_glowny.toString()).isNotEmpty() || noteDB.doa().getAllRemindersDataDone(data_glowny.toString(),"false").isNotEmpty()){
            val dzisiejsze_wszystkie=noteDB.doa().getNumberAll(data_glowny.toString()).size
            val dzisiejsze_niewykonane=noteDB.doa().getNumberNotDone(data_glowny.toString(),"false").size
            
            dzisiaj_wszystkie_txt.text="Ilość powiadomień: "+dzisiejsze_wszystkie.toString()
            dzisiaj_niewykonane_txt.text="W tym niewykonane: "+dzisiejsze_niewykonane.toString()
        }else{
            dzisiaj_wszystkie_txt.text="Ilość powiadomień: 0"
            dzisiaj_niewykonane_txt.text="W tym niewykonane: 0"
        }

        if(noteDB.doa().getAllRemindersWhere("3").isNotEmpty()){
            val szkola_wszystkie=noteDB.doa().getAllRemindersWhere("3").size
            val wszystkie_wszystkie=noteDB.doa().getAllReminders().size
            val procenty = (1/2)*100
            szkola_prc_txt.text=procenty.toString()+"%"
        }else{
            szkola_prc_txt.text = "0%"
        }
        //sa
    }
}