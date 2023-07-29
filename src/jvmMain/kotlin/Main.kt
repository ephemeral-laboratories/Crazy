
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {

    // "Source of all truth"
    var adjectiveText by remember { mutableStateOf(TextFieldValue("crazy")) }
    var roomAdjectiveText by remember { mutableStateOf(TextFieldValue("rubber")) }
    var roomItemText by remember { mutableStateOf(TextFieldValue("rats")) }

    val adjective = adjectiveText.text
    val adjectiveTitleCase = adjective.replaceFirstChar(Char::uppercase)
    val roomAdjective = roomAdjectiveText.text
    val roomAdjectiveTitleCase = roomAdjective.replaceFirstChar(Char::uppercase)
    val roomItem = roomItemText.text

    Window(
        title = adjectiveTitleCase,
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize.Unspecified,
        )
    ) {
        EphemeralLaboratoriesTheme {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)
            ) {
                Column {
                    // Inputs
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = adjectiveText,
                            label = { Text("Adjective") },
                            singleLine = true,
                            onValueChange = { adjectiveText = it },
                            modifier = Modifier.width(IntrinsicSize.Min)
                        )
                        OutlinedTextField(
                            value = roomAdjectiveText,
                            label = { Text("Room adjective") },
                            singleLine = true,
                            onValueChange = { roomAdjectiveText = it },
                            modifier = Modifier.width(IntrinsicSize.Min)
                        )
                        OutlinedTextField(
                            value = roomItemText,
                            label = { Text("Room item") },
                            singleLine = true,
                            onValueChange = { roomItemText = it },
                            modifier = Modifier.width(IntrinsicSize.Min)
                        )
                    }

                    // Padding area
                    Box(modifier = Modifier.height(24.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(40.dp),
                        ) {
                            repeat(5) {
                                Icon(imageVector = Icons.Filled.KeyboardDoubleArrowDown, contentDescription = "", tint = MaterialTheme.colors.secondary)
                            }
                        }
                    }

                    // Outputs
                    val pasta by remember(adjectiveText, roomAdjectiveText, roomItemText) {
                        derivedStateOf {
                            """
                                $adjectiveTitleCase? I was $adjective once.
                                They put me in a room.
                                A $roomAdjective room.
                                A $roomAdjective room with $roomItem.
                                They put me in a $roomAdjective room with $roomAdjective $roomItem.
                                $roomAdjectiveTitleCase $roomItem? I hate $roomAdjective $roomItem.
                                They make me $adjective.
                            """.trimIndent()
                        }
                    }

                    SelectionContainer {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = pasta
                        )
                    }

                    val clipboardManager = LocalClipboardManager.current

                    Button(onClick = {
                        clipboardManager.setText(
                            AnnotatedString(
                                text = pasta, paragraphStyle = ParagraphStyle(
                                    TextAlign.Left
                                )
                            )
                        )
                    }) {
                        Text("Copy to Clipboard")
                    }
                }
            }
        }
    }
}
