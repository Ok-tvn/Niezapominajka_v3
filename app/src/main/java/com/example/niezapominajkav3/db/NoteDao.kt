package com.example.niezapominajkav3.db

import androidx.room.*
import com.example.niezapominajkav3.utils.Constants.REMINDER_TABLE

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(noteEntity: ReminderEntity)

    @Update
    fun updateReminder(noteEntity: ReminderEntity)

    @Delete
    fun deleteReminder(noteEntity: ReminderEntity)

    @Query("SELECT * FROM $REMINDER_TABLE ORDER BY reminderID DESC")
    fun getAllReminders() : MutableList<ReminderEntity>

    @Query("SELECT * FROM $REMINDER_TABLE WHERE reminderID LIKE :id")
    fun getReminder(id : Int) : ReminderEntity
}