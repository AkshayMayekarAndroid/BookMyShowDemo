package com.example.bookmyshowdemo.util

import com.example.bookmyshowdemo.util.Const.DETAIL_ARG_GAMES_ID
import com.example.bookmyshowdemo.util.Const.DETAIL_SCREEN
import com.example.bookmyshowdemo.util.Const.HOME_SCREEN
import com.example.bookmyshowdemo.util.Const.IMAGE
import com.example.bookmyshowdemo.util.Const.OVERVIEW
import com.example.bookmyshowdemo.util.Const.RELEASE_DATE
import com.example.bookmyshowdemo.util.Const.TITLE

sealed class Route(val route: String) {

    object Home: Route(HOME_SCREEN)
    /*object Detail: Route("$DETAIL_SCREEN/{$DETAIL_ARG_GAMES_ID}") {
        fun createRoute(gamesId: String) = "$DETAIL_SCREEN/$gamesId"
    }*/
    object Detail : Route("$DETAIL_SCREEN/{$DETAIL_ARG_GAMES_ID}/{$TITLE}/{$IMAGE}/{$OVERVIEW}/{$RELEASE_DATE}") {
        fun createRoute(gamesId: Int?, title: String?, image: String?, overView: String?, releaseDate: String?) = "$DETAIL_SCREEN/$gamesId/$title/$image/$overView/$releaseDate"
    }
}