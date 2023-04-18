package com.example.bookmyshowdemo.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bookmyshowdemo.ui.theme.BookMyShowDemoTheme

@SuppressLint("ShowToast")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCardBig(
    modifier: Modifier = Modifier,
    name: String = "",
    imageUrl: String = "",
    releaseDate: String = "",
    onClickProduct: () -> Unit = {},
) {
   /* val imagePainter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = R.drawable.ic_launcher_foreground),
    )*/
    val imagePainter = rememberAsyncImagePainter(
        model = "https://image.tmdb.org/t/p/w500$imageUrl",

    )
    Card(
        onClick = onClickProduct,
        elevation = 5.dp,

    ) {
        Image(contentScale = ContentScale.Crop,
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .blur(radius = 16.dp)
                .border(1.dp, MaterialTheme.colors.onPrimary, CircleShape)
        )
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {

            Column(
                modifier = Modifier
                    .wrapContentHeight(
                        align = Alignment.CenterVertically
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
           ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = releaseDate,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
                Button(onClick = {
                    //your onclick code here
                }) {
                    Text(text = "Book")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardBigPreview() {
    BookMyShowDemoTheme {
        ProductCardBig()
    }
}