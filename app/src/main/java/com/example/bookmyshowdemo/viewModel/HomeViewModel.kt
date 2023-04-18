package com.example.bookmyshowdemo.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmyshowdemo.util.Response
import com.example.bookmyshowdemo.domain.model.data.NowPlaying
import com.example.bookmyshowdemo.domain.model.data.Results
import com.example.bookmyshowdemo.domain.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val nowPlayingRepository : NowPlayingRepository
): ViewModel()  {
    private var movieLiveData = MutableLiveData<List<Results>>()

    private val _gamesState = mutableStateOf<Response<NowPlaying>>(Response.Success(null))
    val gamesState: State<Response<NowPlaying>> = _gamesState

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    var _list = mutableListOf<Results>()
    val list = _list.toList()


    fun getPopularMovies() {

        viewModelScope.launch {
         //   nowPlayingRepository = NowPlayingRepositoryImpl(nowPlaying = RetrofitInstance.api,"4b7db348e6448c71b48548ad54dfb40b")

            nowPlayingRepository.getNowPlaying().collect{ response ->
                _gamesState.value = response
                 Log.d("TAG akshay", "response$response")

            }

        }

       /* RetrofitInstance.api.getNowPlayingMovies("4b7db348e6448c71b48548ad54dfb40b").enqueue(object  :
            Callback<NowPlaying> {
            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!.results
                    Log.d("TAG", "response"+response.body()!!.results)
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })*/
    }
    fun observeMovieLiveData() : LiveData<List<Results>> {
        return movieLiveData
    }

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchTextChange(text : String) {
        _searchText.value = text
         val _gamesStateDummy = mutableStateOf<Response<NowPlaying>>(Response.Success(null))

        if(text.isEmpty().not()) {
            var temp = _gamesState.value
            _gamesState.value = _gamesStateDummy.value
            _gamesState.value = temp
        }

//         val _data = MutableStateFlow(gamesState)
//        val _dataaaa  = listOf(gamesState.value)
//         val _persons = MutableStateFlow(_dataaaa)
//
//        val dataa = searchText.debounce(1000L)
//            .onEach { _isSearching.update { true } }
//            .combine(_persons) { text, nowPlayingData ->
//                if(text.isBlank()) {
//                    nowPlayingData
//                } else {
//                    delay(2000L)
//                    val we = nowPlayingData.value
//
//                    we
//                }
//            }
//            .onEach { _isSearching.update { false } }
//            .stateIn(
//                viewModelScope,
//                SharingStarted.WhileSubscribed(5000),
//                _persons.value
//            )

    }
}