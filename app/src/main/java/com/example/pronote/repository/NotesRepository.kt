package com.example.pronote.repository

import androidx.lifecycle.LiveData
import com.example.pronote.dao.NotesDao
import com.example.pronote.models.Notes

class NotesRepository(var dao: NotesDao) {
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }
    fun insertNotes(notes: Notes) {
        dao.insertNote(notes)
    }
    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}