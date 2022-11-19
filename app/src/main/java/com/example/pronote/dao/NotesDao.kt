package com.example.pronote.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pronote.models.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(note: Notes)
}