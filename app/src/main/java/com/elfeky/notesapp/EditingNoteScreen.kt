package com.elfeky.notesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elfeky.notesapp.database.Note
import com.elfeky.notesapp.ui.NoteViewModel


@Composable
fun EditingNoteScreen(
    id: Int,
    noteDetails: String,
    noteTitle: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NoteViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        var details by remember { mutableStateOf(noteDetails) }
        var title by remember { mutableStateOf(noteTitle) }

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {
                Text(text = "Note Title")
            },
            modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = {
                Text(text = "Note Details")
            },
            modifier = modifier.fillMaxWidth()
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    viewModel.upsert(Note(id, details,title))
                    navController.popBackStack()
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = {
                    viewModel.delete(Note(id, noteDetails,title))
                    navController.popBackStack()
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditingNoteScreenPreview() {
    EditingNoteScreen(id = 2, noteDetails = "test details","test" ,navController = rememberNavController())
}