package com.example.bookmyshowdemo.ui.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookmyshowdemo.domain.model.data.NowPlaying
import com.example.bookmyshowdemo.domain.model.data.Results
import com.example.bookmyshowdemo.ui.component.ProductCard
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.regex.Pattern


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying? = null,
    onClickToDetailScreen: (Results) -> Unit = {},
    searchText: String,
) {
    val WHITESPACE = Pattern.compile("\\s+")

    if (nowPlaying == null) return
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(nowPlaying.results.size) { index ->

            nowPlaying.results[index].let {item->
                val id = item.id
                val name = item.title ?: ""
                val imageUrl = item.posterPath ?: ""
                val releaseDate = item.releaseDate ?: ""
                val words = WHITESPACE.split(name.trim())
                for (word in words) {
                    if (word.startsWith(prefix = searchText, ignoreCase = true)) {
                        ProductCard(
                            modifier = modifier
                                .padding(4.dp),
                            name = name,
                            imageUrl = imageUrl,
                            releaseDate = releaseDate,
                            onClickProduct = {
                                item.let {
                                    val mapper = ObjectMapper()
                                    val json = mapper.writeValueAsString(it)

                                    onClickToDetailScreen.invoke(item)
                                }
                            }
                        )
                        break
                    }
                }

            }
        }
        /* nowPlaying.apply {
             item(
                 span = { GridItemSpan(maxLineSpan) }
             ) {
                 when {
                     loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                         LoadingCircular(
                             modifier = Modifier.fillMaxWidth(),
                         )
                     }
                     loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                         ErrorButton(
                             modifier = Modifier.fillMaxWidth(),
                             text = stringResource(id = R.string.error_message),
                             onClick = {
                                 retry()
                             }
                         )
                     }
                 }
             }
         }*/
    }
}
