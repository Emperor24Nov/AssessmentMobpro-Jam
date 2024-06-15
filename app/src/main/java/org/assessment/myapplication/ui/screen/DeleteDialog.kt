package org.assessment.myapplication.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.assessment.myapplication.R
import org.assessment.myapplication.model.Jam


@Composable
fun DeleteDialog(jam: Jam, onDismissRequest: () -> Unit, onConfirmation: (String) -> Unit, id: String) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = "Want to delete this ${jam.nama} item ?")
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmation(id)
            }) {
                Text(text = stringResource(id = R.string.hapus))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.batal))
            }
        }
    )
}