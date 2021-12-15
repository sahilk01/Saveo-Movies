package com.example.saveomovies.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.util.INITIAL_PAGE_INDEX
import com.example.saveomovies.util.PER_PAGE_ITEMS
import javax.inject.Inject


class PopularMoviesPagingSource @Inject constructor(
    private val tmdbApi: TMDBApi
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: INITIAL_PAGE_INDEX

        return try {
            val response = tmdbApi.getPopularMovies(
                page = pageIndex
            )


            val photos = response.body()!!.results

            val nextKey =
                if (photos.isEmpty()) {
                    null
                } else {
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / PER_PAGE_ITEMS)
                }
            LoadResult.Page(
                data = photos,
                prevKey = if (pageIndex == INITIAL_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }

    }
}