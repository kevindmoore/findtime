package com.raywenderlich.compose.ui

import androidx.compose.runtime.Composable

@Composable
expect fun MeetingDialog(hours: List<Int>,
                         onDismiss: () -> Unit
)