package com.raywenderlich.desktop.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.raywenderlich.compose.ui.TimeZoneCalculator

sealed class Screen(val title: String) {
  object TimeZonesScreen : Screen("Timezones")
  object TimezoneCalcScreen : Screen("Calculator")
}

data class BottomNavigationItem(
  val route: String,
  val icon: ImageVector,
  val iconContentDescription: String
)

val bottomNavigationItems = listOf(
  BottomNavigationItem(
    Screen.TimeZonesScreen.title,
    Icons.Filled.Language,
    "Timezones"
  ),
  BottomNavigationItem(
    Screen.TimezoneCalcScreen.title,
    Icons.Filled.Place,
    "Calculator"
  )
)
@Composable
fun MainView() {
  val showAddDialog = remember { mutableStateOf(false) }
  val timezoneStrings = remember { SnapshotStateList<String>() }
  val selectedIndex = remember { mutableStateOf(0)}

  MaterialTheme {
    Scaffold(
      floatingActionButton = {
        if (selectedIndex.value == 0) {
          FloatingActionButton(
            modifier = Modifier
              .padding(16.dp),
            onClick = {
              showAddDialog.value = true
            }
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = null
            )
          }
        }
      },
      bottomBar = {
        BottomNavigation {
          bottomNavigationItems.forEachIndexed { i, bottomNavigationitem ->
            BottomNavigationItem(
              icon = {
                Icon(
                  bottomNavigationitem.icon,
                  contentDescription = bottomNavigationitem.iconContentDescription
                )
              },
              selected = selectedIndex.value == i,
              onClick = {
                  selectedIndex.value = i
              }
            )
          }
        }
      }
      ) {
      if (showAddDialog.value) {
        AddTimeZoneDialog(
          onAdd = {
            showAddDialog.value = false
            for (zone in it) {
              if (!timezoneStrings.contains(zone)) {
                timezoneStrings.add(zone)
              }
            }
          },
          onDismiss = {
            showAddDialog.value = false
          },
        )
      }

      when (selectedIndex.value) {
        0 -> TimeZoneScreen(timezoneStrings)
        1 -> TimeZoneCalculator(timezoneStrings)
      }
    }
  }
}