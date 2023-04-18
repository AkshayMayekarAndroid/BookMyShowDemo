package com.example.bookmyshowdemo.domain.repository

import com.example.bookmyshowdemo.util.Response
import com.example.bookmyshowdemo.domain.model.data.NowPlaying
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {
    fun getNowPlaying(): Flow<Response<NowPlaying>>
}