import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun EphemeralLaboratoriesTheme(body: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color(233, 55, 84),
            secondary = Color(65, 152, 223)
        )
    ) {
        body()
    }
}
