package com.elfeky.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("_id")
    val id: Int = 0,
    @ColumnInfo("note_details")
    val noteDetails: String,
    @ColumnInfo("note_title")
    val noteTitle: String
)