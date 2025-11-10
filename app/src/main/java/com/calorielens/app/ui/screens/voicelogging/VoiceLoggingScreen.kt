package com.calorielens.app.ui.screens.voicelogging

import android.Manifest
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.calorielens.app.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VoiceLoggingScreen(
    onBack: () -> Unit,
    onMealAnalyzed: (String) -> Unit
) {
    var isRecording by remember { mutableStateOf(false) }
    val permissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.voice_logging_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 48.dp)
                )

                if (permissionState.hasPermission) {
                    // Waveform visualization
                    if (isRecording) {
                        WaveformVisualization()
                    } else {
                        Spacer(modifier = Modifier.height(120.dp))
                    }

                    Spacer(modifier = Modifier.height(64.dp))

                    // Microphone button
                    FloatingActionButton(
                        onClick = {
                            isRecording = !isRecording
                            if (!isRecording) {
                                // Process voice input
                                onMealAnalyzed("voice_${System.currentTimeMillis()}")
                            }
                        },
                        modifier = Modifier.size(80.dp),
                        containerColor = if (isRecording) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                    ) {
                        Icon(
                            imageVector = if (isRecording) Icons.Default.Stop else Icons.Default.Mic,
                            contentDescription = "Record",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Control buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (isRecording) {
                            TextButton(onClick = { isRecording = false }) {
                                Text(stringResource(R.string.pause))
                            }
                            TextButton(onClick = {
                                isRecording = false
                                onMealAnalyzed("voice_${System.currentTimeMillis()}")
                            }) {
                                Text(stringResource(R.string.done))
                            }
                        }
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.permission_microphone_title),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.permission_microphone_message),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { permissionState.launchPermissionRequest() }) {
                            Text(stringResource(R.string.permission_grant))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WaveformVisualization() {
    val infiniteTransition = rememberInfiniteTransition(label = "waveform")
    
    val scale1 by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale1"
    )

    val scale2 by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale2"
    )

    val scale3 by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(550, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale3"
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WaveformBar(scale = scale1, height = 40.dp)
        WaveformBar(scale = scale2, height = 60.dp)
        WaveformBar(scale = scale3, height = 50.dp)
        WaveformBar(scale = scale2, height = 70.dp)
        WaveformBar(scale = scale1, height = 45.dp)
        WaveformBar(scale = scale3, height = 55.dp)
        WaveformBar(scale = scale2, height = 65.dp)
    }
}

@Composable
fun WaveformBar(scale: Float, height: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .width(8.dp)
            .height(height * scale)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
            )
    )
}

