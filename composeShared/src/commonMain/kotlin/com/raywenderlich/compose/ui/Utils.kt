package com.raywenderlich.compose.ui

fun isSelected(selectedStates: Map<Int, Boolean>, index: Int): Boolean {
    return (selectedStates.containsKey(index) && (true == selectedStates[index]))
}
