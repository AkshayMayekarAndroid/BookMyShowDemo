package com.example.bookmyshowdemo.network.service

import com.example.bookmyshowdemo.domain.model.data.NowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {

      @GET("movie/now_playing?")
      suspend fun getNowPlayingMovies(@Query("api_key") api_key : String) : NowPlaying
}