package com.example.niezapominajkav3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*


class dodawanie : AppCompatActivity() {


    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    lateinit var spinner: Spinner
    lateinit var editTextNazwa: EditText
    lateinit var data_wynik: TextView
    lateinit var godzina_wynik: TextView
    lateinit var checkbox_wazne: CheckBox
    lateinit var spinner_dodawanie: Spinner
    lateinit var Opis_txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dodawanie)

        editTextNazwa = findViewById(R.id.editTextNazwa)
        data_wynik = findViewById(R.id.data_wynik)
        godzina_wynik = findViewById(R.id.godzina_wynik)
        checkbox_wazne = findViewById(R.id.checkbox_wazne)
        spinner_dodawanie = findViewById(R.id.spinner_dodawanie)
        Opis_txt = findViewById(R.id.Opis_txt)

        spinner = findViewById(R.id.spinner_dodawanie)
        val superHero = arrayOf<String?>("Praca", "Sport", "Codzienność", "Szkoła")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, superHero)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter

        textview_date = findViewById<TextView>(R.id.data_wynik);
        var wynik_godzina = findViewById<TextView>(R.id.godzina_wynik);

        textview_date!!.text = "--/--/----"


        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textview_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@dodawanie,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        wynik_godzina.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                wynik_godzina.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat  (myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }
    fun saveTextFile(view: View) {
        try {
            charset("UTF-8")
            val fileOutputStream: FileOutputStream = openFileOutput("mytextfile.txt", Context.MODE_PRIVATE)
            val outputWriter = OutputStreamWriter(fileOutputStream)
            outputWriter.write("Nazwa: "+editTextNazwa.text.toString()+"\r\n"+
                                    "Data: "+data_wynik.text.toString()+"\r\n"+
                                    "Godzina: "+godzina_wynik.text.toString()+"\r\n"+
                                    "Wazne: "+checkbox_wazne.isChecked().toString()+"\r\n"+
                                    "Kategoria: "+spinner_dodawanie.getSelectedItem().toString()+"\r\n"+
                                    "Opis: "+Opis_txt.text.toString())
            outputWriter.close()
            //display file saved message
            Toast.makeText(baseContext, "File saved successfully!", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}