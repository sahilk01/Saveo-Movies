package com.example.saveomovies.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Movie
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.network.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

//    val trendingMovies: StateFlow<Movie> = homeRepository.getTrendingMovies().stateIn(
//        viewModelScope,
//        SharingStarted.WhileSubscribed(),
//        Movie(
//            0,
//            emptyList(),
//            0,
//            0
//        )
//    )

    private val _trendingMovies = MutableStateFlow<Outcome<List<Result>>>(Outcome.Loading())
    val trendingMovies: StateFlow<Outcome<List<Result>>> get() = _trendingMovies


    val popularMoviesFlow = flow {
        emitAll(homeRepository.getPopularMovies().cachedIn(viewModelScope))
    }

    init {
        getTrendingMovies()
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            homeRepository.getTrendingMovies()
                .catch { cause ->
                    _trendingMovies.value = Outcome.Failure(Throwable("Some Error"))
                }
                .collect {
                    _trendingMovies.value = Outcome.Success(it.results)
                }
        }
    }

}