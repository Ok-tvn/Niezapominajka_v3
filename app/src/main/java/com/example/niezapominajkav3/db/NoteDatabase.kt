package com.example.niezapominajkav3.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReminderEntity2::class], version = 1)
abstract class ReminderDatabase2 : RoomDatabase(){
    abstract fun doa():ReminderDao2
}