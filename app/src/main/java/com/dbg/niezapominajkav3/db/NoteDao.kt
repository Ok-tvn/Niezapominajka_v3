package com.dbg.niezapominajkav3.db

import androidx.room.*
import com.dbg.niezapominajkav3.utils.Constants.REMINDER_TABLE2

@Dao
interface ReminderDao2 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(reminderEntity: ReminderEntity2)

    @Update
    fun updateReminder(reminderEntity: ReminderEntity2)

    @Delete
    fun deleteReminder(reminderEntity: ReminderEntity2)

    @Query("SELECT * FROM $REMINDER_TABLE2 ORDER BY reminderID DESC")
    fun getAllReminders() : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2  WHERE Kategoria LIKE :przyklad ")
    fun getAllRemindersWhere(przyklad : String) : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2  WHERE Wazne LIKE :wazne ")
    fun getAllRemindersImpo(wazne : String) : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2  WHERE Wazne LIKE :wazne AND Wykonane LIKE :done")
    fun getAllRemindersImpoDone(wazne : String, done: String) : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2  WHERE Data LIKE :data ")
    fun getAllRemindersData(data : String) : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2  WHERE Data LIKE :data AND Wykonane LIKE :done")
    fun getAllRemindersDataDone(data : String, done: String) : MutableList<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE reminderID LIKE :id")
    fun getReminder(id : Int) : ReminderEntity2

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE Data LIKE :dzisiaj")
    fun getNumberAll(dzisiaj: String) : List<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE Wazne LIKE :wazne")
    fun getNumberAllWazne(wazne : String) : List<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE Wazne LIKE :wazne AND Wykonane LIKE :wykonanie")
    fun getNumberAllWazneNotDone(wazne : String,wykonanie : String) : List<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE Data LIKE :dzisiaj AND Wykonane LIKE :wykonanie")
    fun getNumberNotDone(dzisiaj : String,wykonanie : String) : List<ReminderEntity2>

    @Query("SELECT * FROM $REMINDER_TABLE2 WHERE Data LIKE :dzisiaj AND Wykonane LIKE :wykonanie AND Kategoria LIKE :przyklad ")
    fun getWhereDataNotDone(dzisiaj : String,wykonanie : String, przyklad: String) : List<ReminderEntity2>
}