package com.raywenderlich.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raywenderlich.findtime.TimeZoneHelper
import kotlinx.coroutines.delay
import org.koin.java.KoinJavaComponent.inject

@Composable
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>
) {
    val timezoneHelper: TimeZoneHelper by inject(TimeZoneHelper::class.java)
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var time by remember { mutableStateOf(timezoneHelper.currentTime()) }
        LaunchedEffect(0) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(1000 * 60L) // Every minute
            }
        }
        localTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time, date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )
//    Text(
//      modifier = Modifier
//        .padding(8.dp)
//        .fillMaxWidth(),
//      text = time,
//      style = MaterialTheme.typography.h2.copy(textAlign = TextAlign.Center)
//    )
        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            state = listState,
        ) {
            items(currentTimezoneStrings,
                key = { timezone ->
                    timezone
                }) { timezoneString ->
                AnimatedSwipeDismiss(
                    item = timezoneString,
                    background = { _ ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(50.dp)
                                .background(Color.Red)
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp
                                )
                        ) {
                            val alpha = 1f
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                tint = Color.White.copy(alpha = alpha)
                            )
                        }
                    },
                    content = {
                        timeCard(
                            city = timezoneString,
                            hours = timezoneHelper.hoursFromTimeZone(timezoneString),
                            time = timezoneHelper.currentTime(),
                            date = timezoneHelper.getDate(timezoneString)
                        )
/*
            Box(
              modifier = Modifier
                .fillMaxSize()
                .height(80.dp)
                .background(Color.White)
                .padding(8.dp)
            ) {
              Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp,
                modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .padding(8.dp)
              )
              {
                Box(modifier = Modifier.padding(8.dp)) {
                  Text(
                    text = timezoneString
                  )
                  Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = timezoneHelper.getTime(timezoneString)
                  )
                }
              }
            }
*/
                    },
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            println("Removing $zone")
                            currentTimezoneStrings.remove(zone)
                            println("Timezone strings ${currentTimezoneStrings.size}")
                        }
                    }
                )
            }
        }
    }
}