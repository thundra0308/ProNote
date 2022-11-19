package com.example.pronote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pronote.dao.NotesDao
import com.example.pronote.models.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun myNotesDao(): NotesDao
    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null
        fun getDataBaseInstance(context: Context): NotesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "notes-database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}