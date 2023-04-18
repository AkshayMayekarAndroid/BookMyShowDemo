package com.example.bookmyshowdemo.domain.model.data

import com.google.gson.annotations.SerializedName

data class NowPlaying(

    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("dates") var dates: Dates? = Dates(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null

)