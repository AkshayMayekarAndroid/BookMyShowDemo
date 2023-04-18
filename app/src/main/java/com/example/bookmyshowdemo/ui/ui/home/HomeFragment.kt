package com.example.bookmyshowdemo.ui.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookmyshowdemo.util.Response
import com.example.bookmyshowdemo.ui.theme.BookMyShowDemoTheme
import com.example.bookmyshowdemo.viewModel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmyshowdemo.domain.model.data.Results
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFragment(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickToDetailScreen: (Results) -> Unit = {},
) {

    var searchText = ""
    homeViewModel.getPopularMovies()
    homeViewModel.viewModelScope.launch {
        homeViewModel.searchText.collect {
            searchText = it

            // Log.d("TAG akshay", "response$response")

        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        when (val nowPlayingResponse = homeViewModel.gamesState.value) {
            is Response.Loading -> {
                /*LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )*/
            }
            is Response.Success -> {

                nowPlayingResponse.data?.let {
                    for (nowP in nowPlayingResponse.data.results){
                        homeViewModel._list.add(nowP)
                    }
                }


                Column() {
                    var text = remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = text.value,

                        singleLine = true,
                        onValueChange = { newText ->
                            text.value = newText
                            homeViewModel.onSearchTextChange(newText.text)
                        },
                        placeholder = { Text("Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            Icon(Icons.Filled.Search, "", tint = Blue)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent, //hide the indicator
                            unfocusedIndicatorColor = Color.Blue
                        )
                    )
                    Spacer()
                    HomeScreen(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 20.dp
                            ),
                        nowPlaying = nowPlayingResponse.data,
                        searchText = searchText,
                        onClickToDetailScreen = onClickToDetailScreen,
                    )
                }

            }
            is Response.Failure -> {
                /* ErrorButton(
                     modifier = Modifier.fillMaxWidth(),
                     text = stringResource(id = R.string.error_message),
                     onClick = {
                         launch()
                     }
                 )*/
            }
            else -> {}
        }

    }
}

@Composable
fun Spacer() {
    Spacer(modifier = Modifier.height(6.dp))
}


@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    BookMyShowDemoTheme {
        //HomeFragment()
    }
}