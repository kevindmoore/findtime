package com.raywenderlich.findtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.raywenderlich.findtime.android.ui.AddTimeZoneDialog
import com.raywenderlich.findtime.android.ui.AppTheme
import com.raywenderlich.findtime.android.ui.TimeZoneCalculator
import com.raywenderlich.findtime.android.ui.TimeZoneScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout()
        }
    }
}
sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object TimezoneCalcScreen : Screen("Calculator")
}

data class BottomNavigationitem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationitem(
        Screen.TimeZonesScreen.title,
        Icons.Filled.Language,
        "Timezones"
    ),
    BottomNavigationitem(
        Screen.TimezoneCalcScreen.title,
        Icons.Filled.Place,
        "Calculator"
    )
)


@Composable
fun MainLayout() {
    val navController = rememberNavController()
    val showAddDialog = remember { mutableStateOf(false) }
    val timezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember { mutableStateOf(0)}

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    when (selectedIndex.value) {
                        0 -> Text(text = stringResource(R.string.world_clocks))
                        else -> Text(text = stringResource(R.string.calculator))
                    }
                })
            },
            floatingActionButton = {
                if (selectedIndex.value == 0) {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .navigationBarsWithImePadding(),
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary),
                            contentDescription = null
                        )
                    }
                }
            },

            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route    //arguments?.getString(KEY_ROUTE)

                    bottomNavigationItems.forEachIndexed { i, bottomNavigationitem ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    bottomNavigationitem.icon,
                                    contentDescription = bottomNavigationitem.iconContentDescription
                                )
                            },
                            selected = currentRoute == bottomNavigationitem.route,
                            onClick = {
                                navController.navigate(bottomNavigationitem.route) {
                                    selectedIndex.value = i
                                }
                            }
                        )
                    }
                }
            }
        ) { _ ->
            NavHost(navController, startDestination = Screen.TimeZonesScreen.title) {
                composable(Screen.TimeZonesScreen.title) {
                    if (showAddDialog.value) {
                        AddTimeZoneDialog(
                            onAdd = {
                                showAddDialog.value = false
                                for (zone in it) {
                                    if (!timezoneStrings.contains(zone)) {
                                        timezoneStrings.add(zone)
                                    }
                                }
                                println("Timezone strings ${timezoneStrings.size}")
                            },
                            onDismiss = {
                                showAddDialog.value = false
                            }
                        )
                    }
                    TimeZoneScreen(timezoneStrings)
                }
                composable(Screen.TimezoneCalcScreen.title) { _ ->
                    TimeZoneCalculator(timezoneStrings)
                }
             }
        }
    }
}