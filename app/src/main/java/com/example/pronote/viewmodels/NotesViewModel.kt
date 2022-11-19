package com.example.pronote.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pronote.app.NotesApp
import com.example.pronote.database.NotesDatabase
import com.example.pronote.models.Notes
import com.example.pronote.repository.NotesRepository
import com.example.pronote.ui.fragments.CreateNotesFragment

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository: NotesRepository
    init {
        val dao = NotesDatabase.getDataBaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }
    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()
    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }
}