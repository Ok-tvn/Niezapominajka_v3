package com.dbg.niezapominajkav3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dbg.niezapominajkav3.utils.Constants.REMINDER_TABLE2

@Entity(tableName = REMINDER_TABLE2)
data class ReminderEntity2(
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
    val reminderDescription : String,
    @ColumnInfo(name = "Wykonane")
    val reminderDone : String
)
