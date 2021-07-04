package io.codeherb.composegithubfinder.data.source.remote

import io.codeherb.composegithubfinder.data.model.GithubUsers
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface GithubService {
    @GET("/search/users")
    fun getUsers(@Query("q") q: String, @Query("page") page: Int = 1, @Query("per_page") perPage: Int = 30): Flow<GithubUsers>
}