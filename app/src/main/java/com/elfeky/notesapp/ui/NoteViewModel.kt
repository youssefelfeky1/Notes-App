package com.elfeky.notesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.elfeky.notesapp.database.Note
import com.elfeky.notesapp.database.RoomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val db = RoomDBHelper.getInstance(app)

    fun upsert(n: Note) {
        viewModelScope.launch(Dispatchers.IO) { db.dao.upsertNote(n) }
    }

    fun delete(n: Note) {
        viewModelScope.launch(Dispatchers.IO) { db.dao.deleteNote(n) }
    }

    fun getNotes(): Flow<List<Note>> = db.dao.getAllNotes()

}