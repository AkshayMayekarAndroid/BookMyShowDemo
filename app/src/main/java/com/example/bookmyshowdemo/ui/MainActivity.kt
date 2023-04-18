package com.example.bookmyshowdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookmyshowdemo.ui.ui.details.DetailFragment
import com.example.bookmyshowdemo.ui.ui.home.HomeFragment
import com.example.bookmyshowdemo.ui.ui.theme.BookMyShowDemoTheme
import com.example.bookmyshowdemo.util.Const.DETAIL_ARG_GAMES_ID
import com.example.bookmyshowdemo.util.Const.IMAGE
import com.example.bookmyshowdemo.util.Const.OVERVIEW
import com.example.bookmyshowdemo.util.Const.RELEASE_DATE
import com.example.bookmyshowdemo.util.Const.TITLE
import com.example.bookmyshowdemo.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookMyShowDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetpackComposeAppScreen()
                }
            }
        }
    }
}
@Composable
fun JetpackComposeAppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(
                onClickToDetailScreen = { data ->
                    navController.navigate(
                        Route.Detail.createRoute(data.id,data.title,data.posterPath?.replace("/",""),data.overview,data.releaseDate)
                    )
                }
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_GAMES_ID){
                    type = NavType.StringType
                },
                navArgument(TITLE){
                    type = NavType.StringType
                },
                navArgument(IMAGE){
                    type = NavType.StringType
                },
                navArgument(OVERVIEW){
                    type = NavType.StringType
                },
                navArgument(RELEASE_DATE){
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val data = backStackEntry.arguments?.getString(DETAIL_ARG_GAMES_ID)
            val title = backStackEntry.arguments?.getString(TITLE)
            val image = backStackEntry.arguments?.getString(IMAGE)
            val overview = backStackEntry.arguments?.getString(OVERVIEW)
            val date = backStackEntry.arguments?.getString(RELEASE_DATE)
            requireNotNull(data) { "gamesId parameter wasn't found. Please make sure it's set!" }
            DetailFragment(title = title, image = image, overView = overview, date=date)
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello On Details Fragment $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BookMyShowDemoTheme {
        JetpackComposeAppScreen()
    }
}