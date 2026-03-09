package com.killqwerty.killqwerty_kmp.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import killqwerty_kmp.composeapp.generated.resources.Res
import killqwerty_kmp.composeapp.generated.resources.compose_multiplatform

@Composable
fun SplashScreen(onNavigateToMain: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "By KillQwerty",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToMain()
    }
}