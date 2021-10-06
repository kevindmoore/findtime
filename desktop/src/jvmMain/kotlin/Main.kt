import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.raywenderlich.compose.theme.AppTheme
import com.raywenderlich.compose.ui.MainView
import com.raywenderlich.findtime.di.initializeKoin

fun main() {

    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "TimeZone"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                AppTheme {
                    initializeKoin()
                    MainView()
                }
            }
        }
    }
}

