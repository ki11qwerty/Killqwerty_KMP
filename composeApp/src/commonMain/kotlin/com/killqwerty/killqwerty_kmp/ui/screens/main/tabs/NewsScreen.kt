package com.killqwerty.killqwerty_kmp.ui.screens.main.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsEvent
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsViewModel

@Composable
fun NewsScreen() {
    val viewModel = viewModel { NewsViewModel() }
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("News Tab")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Count: ${state.count}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onEvent(NewsEvent.Increment) }) {
            Text("Increase Counter")
        }
    }
}