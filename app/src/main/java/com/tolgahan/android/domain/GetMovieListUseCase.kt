package com.tolgahan.android.domain

import com.tolgahan.android.core.constants.AppConstants
import com.tolgahan.android.core.networkBoundResource
import com.tolgahan.android.data.repository.UserRepository
import com.tolgahan.android.domain.mapper.UserMapperImpl
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserMapperImpl,
) {
    fun invoke(query: String) = networkBoundResource(
        query = {
            repository.getMovieList()
        },
        fetch = {
            delay(AppConstants.DELAY)
            repository.fetchUsers(
                query
            )
        },
        saveFetchResult = { list ->
            list.items.map { movieResponseModel -> mapper.map(movieResponseModel) }.run {
                repository.deleteMovies()
                repository.saveMovies(this)
            }
        },
        /** Apiden dönen timeStamp değerine veya belirlenen bir süreye göre countDown yapılıp refreshlenebilir.

         **/
        //shouldFetch = {
        //
       // }
    )
}