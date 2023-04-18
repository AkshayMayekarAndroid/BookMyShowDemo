package com.example.bookmyshowdemo.di

import com.example.bookmyshowdemo.util.Const.KEY_API
import com.example.bookmyshowdemo.util.Const.WEB_API
import com.example.bookmyshowdemo.domain.repository.NowPlayingRepository
import com.example.bookmyshowdemo.network.repositoryImple.NowPlayingRepositoryImpl
import com.example.bookmyshowdemo.network.service.NowPlayingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("WEB_API")
    fun provideWebAPI(): String = WEB_API

    @Provides
    @Named("KEY_API")
    fun provideKeyAPI(): String = KEY_API


    @Provides
    fun provideRetrofit(
        @Named("WEB_API") webAPI: String,
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideNowPlayingService(
        retrofit: Retrofit
    ): NowPlayingService = retrofit.create(NowPlayingService::class.java)


    @Provides
    fun provideNowPlayingRepository(
        nowPlayingService: NowPlayingService,
        @Named("KEY_API") apiKey : String
    ): NowPlayingRepository = NowPlayingRepositoryImpl(
        nowPlaying = nowPlayingService,
        apiKey = apiKey
    )
}