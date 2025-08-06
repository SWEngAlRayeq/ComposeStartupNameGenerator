package app.startup_name_generator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.startup_name_generator.presentation.viewmodel.StartupViewModel

@Composable
fun StartupScreen(viewModel: StartupViewModel = hiltViewModel()) {
    val idea = viewModel.idea
    val isLoading = viewModel.isLoading
    val clipboardManager = LocalClipboardManager.current

    val backgroundColors = listOf(
        Color(0xFF1DB954),
        Color(0xFF121212),
        Color(0xFFBB86FC),
        Color(0xFFF06292),
        Color(0xFF64B5F6)
    )
    val colorIndex = remember { mutableStateOf(0) }
    val bgColor = backgroundColors[colorIndex.value % backgroundColors.size]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(bgColor)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🚀 Startup Pitch",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    text = idea,
                    modifier = Modifier.padding(24.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(
                    onClick = {
                        viewModel.getIdea()
                        colorIndex.value += 1
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("🔁 Generate New", color = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(idea))
                    },
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text("📋 Copy", color = Color.Black)
                }
            }
        }
    }
}
