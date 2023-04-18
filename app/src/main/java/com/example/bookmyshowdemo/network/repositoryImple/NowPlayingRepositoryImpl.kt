package com.example.bookmyshowdemo.network.repositoryImple

import com.example.bookmyshowdemo.util.Response
import com.example.bookmyshowdemo.domain.model.data.NowPlaying
import com.example.bookmyshowdemo.domain.repository.NowPlayingRepository
import com.example.bookmyshowdemo.network.service.NowPlayingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NowPlayingRepositoryImpl @Inject constructor(
    private val nowPlaying: NowPlayingService,
    private val apiKey: String,
) : NowPlayingRepository {

    override fun getNowPlaying(): Flow<Response<NowPlaying>> = flow {
        try {
            emit(Response.Loading)
            val responseApi = nowPlaying.getNowPlayingMovies(
                api_key = apiKey
            )
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}