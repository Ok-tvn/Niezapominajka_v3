package com.example.niezapominajkav3

import ItemsViewModel
import RecycleAdaper
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.niezapominajkav3.adapter.NoteAdapter
import com.example.niezapominajkav3.databinding.ActivityCodziennoscBinding
import com.example.niezapominajkav3.db.ReminderDatabase2
import com.example.niezapominajkav3.utils.Constants.REMINDER_DATABASE2
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class codziennosc : AppCompatActivity() {

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
            if(noteDB.doa().getAllRemindersWhere("2").isNotEmpty()){
                rvNoteList.visibility=View.VISIBLE
                tvEmptyText.visibility=View.GONE
                noteAdapter.differ.submitList(noteDB.doa().getAllRemindersWhere("2"))
                setupRecyclerView()
            }else{
                rvNoteList.visibility=View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvNoteList.apply {
            layoutManager=LinearLayoutManager(this@codziennosc)
            adapter=noteAdapter
        }
    }
}