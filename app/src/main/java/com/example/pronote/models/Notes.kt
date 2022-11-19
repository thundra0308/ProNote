package com.example.pronote.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String?,
    var subTitle: String?,
    var note: String?,
    var data: String?,
    var priority: String?
)