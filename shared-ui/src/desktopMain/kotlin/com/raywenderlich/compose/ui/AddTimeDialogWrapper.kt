package com.raywenderlich.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun AddTimeDialogWrapper(onDismiss: onDismissType, content: @Composable () -> Unit) {
    Dialog(onCloseRequest = { onDismiss() },
        state = rememberDialogState(position = WindowPosition(Alignment.Center)),
        title = "Add Timezones",
        content = {
            content()
        })

}
