package com.killqwerty.killqwerty_kmp.ui.screens.main.tabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import com.killqwerty.killqwerty_kmp.domain.data.mock.Mock
import com.killqwerty.killqwerty_kmp.domain.data.mock.toModel
import com.killqwerty.killqwerty_kmp.domain.interactor.news.NewsInteractor
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsEvent
import com.killqwerty.killqwerty_kmp.ui.viewmodel.NewsViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@Composable
fun NewsScreen() {
    val newsInteractor = koinInject<NewsInteractor>()
    val viewModel = viewModel { NewsViewModel(newsInteractor) }
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(NewsEvent.OnStart)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            state.error?.let { Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp)) }
            NewsList(state.news, modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.onEvent(NewsEvent.LoadNextPage) },
                enabled = !state.isLoading && state.hasMore,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Text(if (state.hasMore) "Load next page" else "No more news")
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun NewsList(list : List<NewsModel>, modifier: Modifier){
    val alpha by animateFloatAsState(
        targetValue = if (list.isNotEmpty()) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "listAlpha"
    )

    LazyColumn(modifier = modifier.alpha(alpha),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list){ new -> //добавить кей
            NewsItem(new)
        }
    }
}
@Composable
fun NewsItem(news: NewsModel) {
    var isShowDesc by rememberSaveable { mutableStateOf(false) }

        Card(
            modifier = Modifier.padding(4.dp),
            colors = CardDefaults.cardColors().copy(containerColor = Color.LightGray),
            onClick = {isShowDesc = !isShowDesc}
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.defaultMinSize(minHeight = 50.dp).padding(vertical = 12.dp).fillMaxWidth(),
                        text = news.title,
                        textAlign = TextAlign.Center)
                AnimatedVisibility(
                    visible = isShowDesc,
                    enter = expandVertically(tween(200))
                            + fadeIn(tween(200)) + slideInHorizontally(
                        tween(300)
                    ),
                    exit = shrinkVertically(tween(200))
                            + fadeOut(tween(200)) + slideOutHorizontally(
                        tween(300)
                    )
                ) {
                    Column {
                        news.urlToImage?.takeIf { it.isNotBlank() }?.let { imageUrl ->
                            KamelImage(
                                resource = { asyncPainterResource(data = imageUrl) },
                                contentDescription = news.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                                contentScale = ContentScale.Crop,
                                onLoading = { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) },
                                onFailure = { Text("Image error: ${it.message ?: "unknown"}") }
                            )
                        }
                        Text(
                            text = news.description,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
}



@Preview
@Composable
fun NewsScreenPrev(){
    NewsItem(Mock().toModel())
}