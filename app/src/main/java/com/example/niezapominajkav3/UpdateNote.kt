package com.example.niezapominajkav3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.niezapominajkav3.databinding.ActivityUpdateNoteBinding
import com.example.niezapominajkav3.db.ReminderDatabase2
import com.example.niezapominajkav3.db.ReminderEntity2
import com.example.niezapominajkav3.utils.Constants.BUNDLE_REMINDER_ID2
import com.example.niezapominajkav3.utils.Constants.REMINDER_DATABASE2
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class UpdateNote : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private val noteDB: ReminderDatabase2 by lazy {
        Room.databaseBuilder(this, ReminderDatabase2::class.java, REMINDER_DATABASE2)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var noteEntity: ReminderEntity2
    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDate = ""
    private var defaultTime = ""
    private var defaultImportant = ""
    private var defaultCategory = ""
    private var defaultDesc = ""

    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    lateinit var editTextNazwa: EditText
    lateinit var data_wynik: TextView
    lateinit var godzina_wynik: TextView
    lateinit var checkbox_wazne: CheckBox
    lateinit var spinner_dodawanie: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId = it.getInt(BUNDLE_REMINDER_ID2)
        }

        binding.apply {
            defaultTitle=noteDB.doa().getReminder(noteId).reminderTitle
            defaultDate=noteDB.doa().getReminder(noteId).reminderDate
            defaultTime=noteDB.doa().getReminder(noteId).reminderTime
            defaultImportant=noteDB.doa().getReminder(noteId).reminderImportant
            defaultCategory=noteDB.doa().getReminder(noteId).reminderCategory
            defaultDesc=noteDB.doa().getReminder(noteId).reminderDescription

            editTextNazwa.setText(defaultTitle);
            dataWynik.setText(defaultDate);
            godzinaWynik.setText(defaultTime);
            //spinner_dodawanie.setSelection(2);
            checkboxWazne.isChecked=defaultImportant.toBoolean();
            OpisTxt.setText(defaultDesc)

            usunBtn.setOnClickListener {
                noteEntity= ReminderEntity2(noteId,defaultTitle,defaultDate,defaultTime,defaultImportant,defaultCategory,defaultDesc,"false")
                noteDB.doa().deleteReminder(noteEntity)
                finish()
            }

            zapiszBtn.setOnClickListener {
                val title = editTextNazwa.text.toString()
                val date = dataWynik.text.toString()
                val time = godzinaWynik.text.toString()
                val important = checkboxWazne.isChecked().toString()
                val category = spinnerDodawanie.getSelectedItemPosition().toString();
                val desc =  OpisTxt.text.toString()

                if(title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity= ReminderEntity2(noteId,title,date,time,important,category,desc,"false")
                    noteDB.doa().updateReminder(noteEntity)
                    finish()
                }
                else{
                    Snackbar.make(it, "Nie może być pusto",Snackbar.LENGTH_LONG).show()
                }
            }
        }
        editTextNazwa = findViewById(R.id.editTextNazwa)
        data_wynik = findViewById(R.id.data_wynik)
        godzina_wynik = findViewById(R.id.godzina_wynik)
        checkbox_wazne = findViewById(R.id.checkbox_wazne)
        spinner_dodawanie = findViewById(R.id.spinner_dodawanie)

        val superHero = arrayOf<String?>("Praca", "Sport", "Codzienność", "Szkoła")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, superHero)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner_dodawanie.adapter = arrayAdapter

        textview_date = findViewById<TextView>(R.id.data_wynik);
        var wynik_godzina = findViewById<TextView>(R.id.godzina_wynik);

        spinner_dodawanie.setSelection(defaultCategory.toInt());

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
                DatePickerDialog(this@UpdateNote,
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
}