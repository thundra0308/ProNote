package com.example.pronote.app

import android.app.Application
import com.example.pronote.database.NotesDatabase

class NotesApp: Application() {
    val db by lazy {
        NotesDatabase.getDataBaseInstance(this)
    }
}