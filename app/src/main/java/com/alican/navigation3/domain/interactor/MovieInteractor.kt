package com.alican.navigation3.domain.interactor

import com.alican.navigation3.data.service.MovieService
import com.alican.navigation3.domain.mapper.toUIModel
import com.alican.navigation3.domain.ui_model.MovieUIModel
import com.alican.navigation3.utils.DomainResult
import com.alican.navigation3.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface MovieInteractor {
    fun getPopularMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>>
    fun getNowPlayingMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>>
    fun getUpComingMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>>
    fun getTopRatedMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>>
    fun getMovieDetail(movieId: Int): Flow<DomainResult<MovieUIModel>>
}

class MovieInteractorImpl(
    private val service: MovieService
) : MovieInteractor {
    override fun getPopularMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>> {
        return flow {
            emit(DomainResult.Loading)
            when (val result =
                service.getPopularMovies(page)) {
                is NetworkResult.Success -> {
                    val uiModels = result.data.results.map { apiMovie ->
                        apiMovie.toUIModel()
                    }.shuffled()
                    emit(DomainResult.Success(uiModels))
                }

                is NetworkResult.Error -> {
                    emit(DomainResult.Error(result.error.errorMessage))
                }
            }
        }.catch { e ->
            emit(DomainResult.Error("Unexpected error occurred: ${e.localizedMessage}"))
        }
    }

    override fun getNowPlayingMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>> {
        return flow {
            emit(DomainResult.Loading)
            when (val result =
                service.getNowPlayingMovies(page)) {
                is NetworkResult.Success -> {
                    val uiModels = result.data.results.map { apiMovie ->
                        apiMovie.toUIModel()
                    }.shuffled()
                    emit(DomainResult.Success(uiModels))
                }

                is NetworkResult.Error -> {
                    emit(DomainResult.Error(result.error.errorMessage))
                }
            }
        }.catch { e ->
            emit(DomainResult.Error("Unexpected error occurred: ${e.localizedMessage}"))
        }
    }

    override fun getUpComingMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>> {
        return flow {
            emit(DomainResult.Loading)
            when (val result =
                service.getUpComingMovies(page)) {
                is NetworkResult.Success -> {
                    val uiModels = result.data.results.map { apiMovie ->
                        apiMovie.toUIModel()
                    }.shuffled()
                    emit(DomainResult.Success(uiModels))
                }

                is NetworkResult.Error -> {
                    emit(DomainResult.Error(result.error.errorMessage))
                }
            }
        }.catch { e ->
            emit(DomainResult.Error("Unexpected error occurred: ${e.localizedMessage}"))
        }
    }


    override fun getTopRatedMovies(page: Int): Flow<DomainResult<List<MovieUIModel>>> {
        return flow {
            emit(DomainResult.Loading)
            when (val result =
                service.getTopRatedMovies(page)) {
                is NetworkResult.Success -> {
                    val uiModels = result.data.results.map { apiMovie ->
                        apiMovie.toUIModel()
                    }.shuffled()
                    emit(DomainResult.Success(uiModels))
                }

                is NetworkResult.Error -> {
                    emit(DomainResult.Error(result.error.errorMessage))
                }
            }
        }.catch { e ->
            emit(DomainResult.Error("Unexpected error occurred: ${e.localizedMessage}"))
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<DomainResult<MovieUIModel>> {
        return flow {
            emit(DomainResult.Loading)
            when (val result =
                service.getMovieDetail(movieId)) {
                is NetworkResult.Success -> {
                    val uiModel = result.data.toUIModel()
                    emit(DomainResult.Success(uiModel))
                }

                is NetworkResult.Error -> {
                    emit(DomainResult.Error(result.error.errorMessage))
                }
            }
        }
    }
}