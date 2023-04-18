package com.example.bookmyshowdemo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bookmyshowdemo.R
import com.example.bookmyshowdemo.ui.ui.theme.BookMyShowDemoTheme

@Composable
fun ProductHeader(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    name: String = "",
    releaseDate: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        val imagePainter = rememberAsyncImagePainter(
            model = "https://image.tmdb.org/t/p/w500/$imageUrl",
            error = painterResource(id = R.drawable.ic_launcher_background),
        )


        Image(contentScale = ContentScale.Crop,
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

        )
        Text(
            text = releaseDate,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductHeaderPreview() {
    BookMyShowDemoTheme() {
        ProductHeader()
    }
}