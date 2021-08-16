import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.raywenderlich.desktop.ui.MainView
import com.raywenderlich.findtime.di.initializeKoin

fun main() = singleWindowApplication(
    title = "TimeZone",
    state = WindowState(width = 1280.dp, height = 768.dp),
    icon = BitmapPainter(useResource("ic_launcher.png", ::loadImageBitmap)) // loadImageResource("ic_launcher.png"),
) {
    initializeKoin()
    MainView()
}
