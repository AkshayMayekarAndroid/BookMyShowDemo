package com.example.bookmyshowdemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyshowdemo.domain.model.data.Results

class MainViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Results>>()
   /* fun getPopularMovies() {
        RetrofitInstance.api.getNowPlayingMovies("4b7db348e6448c71b48548ad54dfb40b").enqueue(object  :
            Callback<NowPlaying> {
            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!.results
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }*/
    fun observeMovieLiveData() : LiveData<List<Results>> {
        return movieLiveData
    }
}