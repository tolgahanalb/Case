package com.tolgahan.android.domain

import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.repository.UserRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun invoke(id: String) = repository.getMovieDetail(id)

    suspend fun saveMovie(entity : FollowersItem) = repository.saveMovie(entity)
}