package com.example.bookmyshowdemo.ui.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.bookmyshowdemo.domain.model.data.Results
import com.example.bookmyshowdemo.ui.component.ProductHeader
import com.example.bookmyshowdemo.ui.theme.BookMyShowDemoTheme
import com.fasterxml.jackson.databind.ObjectMapper


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    data: Results? = null,
    title: String? = "",
    image: String? = "",
    overView: String? = "",
    date: String? = "",
) {
   // if(data == null) return
    val releaseDate =  ""
    val description = HtmlCompat
        .fromHtml( "", HtmlCompat.FROM_HTML_MODE_COMPACT)
        .toString()

    Column(
        modifier = modifier
    ) {
        ProductHeader(
            modifier = Modifier.padding(16.dp),
            imageUrl = image ?: "",
            name = title ?: "",
            releaseDate = date ?: "",
        )
        Text(
            text = overView ?: "",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    BookMyShowDemoTheme() {
        DetailScreen()
    }
}