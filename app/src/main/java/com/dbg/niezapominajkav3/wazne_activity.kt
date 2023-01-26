package com.dbg.niezapominajkav3

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.dbg.niezapominajkav3.adapter.NoteAdapter
import com.dbg.niezapominajkav3.databinding.ActivityCodziennoscBinding
import com.dbg.niezapominajkav3.db.ReminderDatabase2
import com.dbg.niezapominajkav3.utils.Constants.REMINDER_DATABASE2
import java.io.*
import java.util.*


class wazne_activity : AppCompatActivity() {

    lateinit var binding: ActivityCodziennoscBinding
    private val noteDB : ReminderDatabase2 by lazy {
        Room.databaseBuilder(this,ReminderDatabase2::class.java,REMINDER_DATABASE2)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCodziennoscBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume(){
        super.onResume()
        checkItem()
    }

    private fun checkItem(){
        binding.apply {
            if(noteDB.doa().getAllRemindersImpo("true").isNotEmpty()){
                rvNoteList.visibility=View.VISIBLE
                tvEmptyText.visibility=View.GONE
                noteAdapter.differ.submitList(noteDB.doa().getAllRemindersImpo("true"))
                setupRecyclerView()
            }else{
                rvNoteList.visibility=View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvNoteList.apply {
            layoutManager=LinearLayoutManager(this@wazne_activity)
            adapter=noteAdapter
        }
    }
}