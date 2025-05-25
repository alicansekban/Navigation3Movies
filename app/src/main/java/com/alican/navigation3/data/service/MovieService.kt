package com.alican.navigation3.data.service

import com.alican.navigation3.data.response.BaseMoviesResponse
import com.alican.navigation3.utils.NetworkResult
import com.alican.navigation3.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.path

interface MovieService {
    suspend fun getPopularMovies(page: Int): NetworkResult<BaseMoviesResponse>
    suspend fun getNowPlayingMovies(page: Int): NetworkResult<BaseMoviesResponse>
    suspend fun getUpComingMovies(page: Int): NetworkResult<BaseMoviesResponse>
    suspend fun getTopRatedMovies(page: Int): NetworkResult<BaseMoviesResponse>
}

class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun getPopularMovies(page: Int): NetworkResult<BaseMoviesResponse> {
        return safeApiCall<BaseMoviesResponse>(client = client) {
            url {
                path("movie/popular")
                parameters.append("page", page.toString())
            }
            method = HttpMethod.Get
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): NetworkResult<BaseMoviesResponse> {
        return safeApiCall<BaseMoviesResponse>(client = client) {
            url {
                path("movie/now_playing")
                parameters.append("page", page.toString())
            }
            method = HttpMethod.Get
        }
    }

    override suspend fun getUpComingMovies(page: Int): NetworkResult<BaseMoviesResponse> {
        return safeApiCall<BaseMoviesResponse>(client = client) {
            url {
                path("movie/upcoming")
                parameters.append("page", page.toString())
            }
            method = HttpMethod.Get
        }
    }

    override suspend fun getTopRatedMovies(page: Int): NetworkResult<BaseMoviesResponse> {
        return safeApiCall<BaseMoviesResponse>(client = client) {
            url {
                path("movie/top_rated")
                parameters.append("page", page.toString())
            }
            method = HttpMethod.Get
        }
    }

}