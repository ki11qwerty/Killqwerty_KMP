package com.killqwerty.killqwerty_kmp.ui.screens.main.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.killqwerty.killqwerty_kmp.data.news.NewsModel
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsEvent
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsViewModel

@Composable
fun NewsScreen() {
    val viewModel = viewModel { NewsViewModel() }
    val state by viewModel.state.collectAsState()
    LazyColumn {
        items(state.news.count()){ news ->
            NewsItem(state.news[news])
        }
    }
}

@Composable
fun NewsItem(news: NewsModel) {
        Card(
            modifier = Modifier.padding(4.dp),
            colors = CardDefaults.cardColors().copy(containerColor = Color.LightGray)
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(news.id.toString())
                Spacer(modifier = Modifier.width(16.dp))
                Text(news.text)
            }
        }
}



@Preview
@Composable
fun NewsScreenPrev(){
    NewsItem(NewsModel(1,"2"))
}