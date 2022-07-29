package com.example.niezapominajkav3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.niezapominajkav3.utils.Constants.REMINDER_TABLE

@Entity(tableName = REMINDER_TABLE)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val reminderID : Int,
    @ColumnInfo(name = "Nazwa")
    val reminderTitle : String,
    @ColumnInfo(name = "Data")
    val reminderDate : String,
    @ColumnInfo(name = "Godzina")
    val reminderTime : String,
    @ColumnInfo(name = "Wazne")
    val reminderImportant : String,
    @ColumnInfo(name = "Kategoria")
    val reminderCategory : String,
    @ColumnInfo(name = "Opis")
    val reminderDescription : String
)
