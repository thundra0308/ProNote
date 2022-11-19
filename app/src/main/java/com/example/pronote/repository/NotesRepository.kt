package com.example.pronote.repository

import androidx.lifecycle.LiveData
import com.example.pronote.dao.NotesDao
import com.example.pronote.models.Notes

class NotesRepository(var dao: NotesDao) {
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }
    fun getAllHighNotes(): LiveData<List<Notes>> {
        return dao.getHighNotes()
    }
    fun getAllMediumNotes(): LiveData<List<Notes>> {
        return dao.getMediumNotes()
    }
    fun getAllLowNotes(): LiveData<List<Notes>> {
        return dao.getLowNotes()
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