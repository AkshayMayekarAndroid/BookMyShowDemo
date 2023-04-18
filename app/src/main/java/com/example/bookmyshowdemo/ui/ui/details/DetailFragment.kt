package com.example.bookmyshowdemo.ui.ui.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookmyshowdemo.ui.Greeting
import com.example.bookmyshowdemo.ui.ui.theme.BookMyShowDemoTheme
import com.example.bookmyshowdemo.viewModel.DetailViewModel
import com.example.bookmyshowdemo.viewModel.HomeViewModel


@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    data: String = "",
    title: String? = "",
    image: String? = "",
    overView: String? = "",
    date: String? = "",
) {
//    fun setData() {
//        detailViewModel._data.value = data
//    }
//
//    val objectMapper = ObjectMapper()
//    val replaced = data.replaceFirst("/details", "")
//    val json = objectMapper.writeValueAsString(replaced)
//    val results: Results =
//        objectMapper.readValue(json, Results::class.java)
//    setData()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        DetailScreen(
            modifier = modifier.fillMaxSize(),
            title = title,
            image = image,
            overView = overView,
            date = date,
        )

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    BookMyShowDemoTheme {
        DetailFragment()
    }
}